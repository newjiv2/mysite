package com.poscodx.mysite03.controller;

import com.poscodx.mysite03.service.UserService;
import com.poscodx.mysite03.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String join() {
        return "user/join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(@Validated UserVo vo) { //Bean Valuedation
        userService.join(vo);
        return "redirect:/user/joinsuccess";
    }

    @RequestMapping(value = "/joinsuccess", method = RequestMethod.GET)
    public String joinsuccess() {
        return "user/join";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "user/login";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String auth(
            HttpSession session, //고치려고 노력하기
            @RequestParam(value = "email", required = true, defaultValue = "") String email,
            @RequestParam(value = "password", required = true, defaultValue = "") String password,
            Model model) {

        UserVo authUser = userService.getUser(email, password);
        //인증 실패
        if (authUser == null) {
            model.addAttribute("email", email);
            return "user/login";
        }
        //인증 성공
        session.setAttribute("authUser", authUser);

        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("authUser");
        session.invalidate();

        return "redirect:/";
    }

    @RequestMapping("/update")
    public String update(HttpSession session) {
        //나중에 고칠 코드
        //Access Control(접근 제어)
        UserVo authUser = (UserVo) session.getAttribute("authUser");
        if (authUser == null) {
            return "redirect:/user/login";
        }
        ////////////////////////////////////////////////////////////////////

        return "/user/update";
    }

}

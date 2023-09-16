package com.poscodx.web.mvc.guestbook;

import com.poscodx.web.mvc.Action;
import com.poscodx.web.utils.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteFormAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        WebUtil.forward("guestbook/deleteform", request, response);

    }
}

package com.poscodx.mysite03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.mysite03.repository.GuestbookRepository;
import com.poscodx.mysite03.vo.GuestbookVo;

@Service
public class GuestbookService {
    @Autowired
    private GuestbookRepository guestbookRepository;

    public List<GuestbookVo> getContentsList() {
        return guestbookRepository.findAll();
    }

    public Boolean deleteContents(Long no, String password) {
        return guestbookRepository.deleteByNoAndPassword(no, password);
    }

    public Boolean addContents(GuestbookVo vo) {
        return guestbookRepository.insert(vo);
    }
}
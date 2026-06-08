package com.example.controller;

import com.example.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ShowBbsController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("show-bbs")
    public String ShowBbs(){
        articleRepository.findAll();
        return "show-bbs";
    }
}

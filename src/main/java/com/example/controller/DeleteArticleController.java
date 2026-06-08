package com.example.controller;

import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/article")
public class DeleteArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/delete-article")
    public String deleteArticle(String articleId) {
        commentRepository.deleteByArticleId(Integer.valueOf(articleId));
        articleRepository.deleteById(Integer.valueOf(articleId));
        return "redirect:/article";
    }
}

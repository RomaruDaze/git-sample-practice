package com.example.controller;

import com.example.domain.Article;
import com.example.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/article")

public class InsertArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    /**
     * 記事を投稿する.
     *
     * @param article 記事情報
     * @return 表示画面
     */
    @PostMapping("/insert-article")
    public String insertArticle(Article article) {
        articleRepository.insert(article);
        return "redirect:/index";
    }
}

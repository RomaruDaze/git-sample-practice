package com.example.controller;

import com.example.domain.Comment;
import com.example.form.CommentForm;
import com.example.repository.CommentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("comment")
public class InsertCommentController {

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("insert")
    public String addComment(CommentForm commentForm) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentForm, comment);
        System.out.println(comment);
        commentRepository.insert(comment);
        return "redirect:/show-bbs";
    }
}

package com.example.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentForm {
    private String articleId;
    private String name;
    private String content;
}

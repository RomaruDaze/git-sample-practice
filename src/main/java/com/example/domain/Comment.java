package com.example.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Comment {
    private Integer id;
    private String name;
    private String content;
    private Integer articleId;
}

package com.example.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private Integer id;
    private String name;
    private String content;
    private List<Comment> commentList;
}

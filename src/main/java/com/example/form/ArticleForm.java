package com.example.form;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleForm {
    private String name;
    private String content;
}

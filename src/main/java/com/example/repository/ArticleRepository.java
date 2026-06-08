package com.example.repository;

import com.example.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Articleテーブルを操作するリポジトリ.
 */
@Repository
public class ArticleRepository {
    private static final RowMapper<Article> ARTICLE_ROW_MAPPER =
            new BeanPropertyRowMapper<>(Article.class);

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * 記事情報を全件取得.
     *
     * @return 記事情報
     */
    public List<Article> findAll() {
        String sql = "SELECT id,name,content FROM articles ORDER BY id DESC;";

        List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);

//        if (articleList.isEmpty()) {
//            return null;
//        }

        return articleList;
    }

    /**
     * 記事の情報を挿入する.
     *
     * @param article 記事
     */
    public void insert(Article article) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(article);

        String sql = "INSERT INTO articles(name,content) " +
                "VALUES (:name,:content);";

        template.update(sql, param);
    }

    /**
     * idに基づき記事の情報を削除する.
     *
     * @param articleId id
     */
    public void deleteById(Integer articleId) {
        SqlParameterSource param = new MapSqlParameterSource("articleId", articleId);

        String sql = "DELETE FROM articles WHERE id = :articleId;";

        template.update(sql, param);
    }
}

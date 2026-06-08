package com.example.repository;

import com.example.domain.Article;
import com.example.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Articleテーブルを操作するリポジトリ.
 */
@Repository
public class ArticleRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final ResultSetExtractor<List<Article>> ARTICLE_EXTRACTOR = rs -> {
        Map<Integer, Article> articleMap = new LinkedHashMap<>();

        while (rs.next()) {
            Integer articleId = rs.getInt("article_id");
            Article article = articleMap.get(articleId);

            if (article == null) {
                article = new Article();
                article.setId(articleId);
                article.setName(rs.getString("article_name"));
                article.setContent(rs.getString("article_content"));
                article.setCommentList(new ArrayList<>());
                articleMap.put(articleId, article);
            }

            Integer commentId = rs.getInt("comment_id");
            if (commentId != 0) {
                Comment comment = new Comment();
                comment.setId(commentId);
                comment.setName(rs.getString("comment_name"));
                comment.setContent(rs.getString("comment_content"));
                comment.setArticleId(rs.getInt("comment_article_id"));

                article.getCommentList().add(comment);
            }
        }
        return new ArrayList<>(articleMap.values());
    };



    /**
     * 記事情報を全件取得.
     *
     * @return 記事情報
     */
    public List<Article> findAll() {
        String sql = "SELECT " +
                "a.id as article_id," +
                "a.name as article_name," +
                "a.content as article_content," +
                "c.id as comment_id," +
                "c.name as comment_name," +
                "c.content as comment_content," +
                "c.article_id as comment_article_id" +
                " FROM articles a " +
                " LEFT OUTER JOIN comments c " +
                " ON c.article_id = a.id " +
                " ORDER BY a.id DESC, c.id DESC";

        return template.query(sql, ARTICLE_EXTRACTOR);
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

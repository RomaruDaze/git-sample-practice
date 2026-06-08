package com.example.repository;

import com.example.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;


@Repository
public class CommentRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Comment> ROW_MAPPER = (rs, i) -> {
        Comment comment = new Comment();
        comment.setId(rs.getInt("id"));
        comment.setName(rs.getString("name"));
        comment.setContent(rs.getString("content"));
        comment.setArticleId(rs.getInt("article_id"));
        return comment;
    };

    public List<Comment> findByArticleId(Integer id) {
        String sql = "SELECT * FROM  WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        return template.query(sql, param, ROW_MAPPER);
    }

    public void insert(Comment comment) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(comment);

        if (comment.getId() == null) {
            String insertSql = "INSERT INTO comments (name,content,article_id) " +
                    "VALUES (:name,:content,:articleId)";

            KeyHolder keyHolder = new GeneratedKeyHolder();
            String[] keyColumnsNames = {"id"};
            template.update(insertSql, param, keyHolder, keyColumnsNames);
            comment.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        } else {
            String updateSql = "UPDATE  " +
                    "SET name=:name,content=:content,article_id=:articleId " +
                    "WHERE id = :id";

            template.update(updateSql, param);
        }
    }

    public void deleteByArticleId(Integer articleId) {
        String sql = "DELETE FROM comments WHERE article_id = :articleId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
        template.update(sql, param);
    }
}

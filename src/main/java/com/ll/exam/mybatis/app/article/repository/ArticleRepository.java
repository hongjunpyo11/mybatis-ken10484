package com.ll.exam.mybatis.app.article.repository;

import com.ll.exam.mybatis.app.article.dto.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleRepository {

    @Select("""
            <script>
            SELECT *
            FROM article
            </script>
            """)
    List<Article> getArticles();

    @Insert("""
            <script>
            INSERT INTO article
            SET createDate = NOW(),
            modifyDate = NOW(),
            subject = #{subject},
            content = #{content}
            </script>
            """)
    void write(@Param("subject") String subject, @Param("content") String content);

    @Select("""
            SELECT LAST_INSERT_ID()
            """)
    public long getLastInsertId();

    @Select("""
            <script>
            SELECT *
            FROM article
            WHERE id = #{id}
            </script>
            """)
    Article getArticleById(@Param("id") long id); // 오류 : 'Parameter not found. available parameters are ...' 해결볍
//    Article getArticleById(long id);

    @Select("""
			<script>
            SELECT A.*
            FROM article AS A
            WHERE 1
            <if test="kw != ''">
                <choose>
                    <when test="kwType == 'subject'">
                        AND A.subject LIKE CONCAT('%', #{kw}, '%')
                    </when>
                    <when test="kwType == 'content'">
                        AND A.content LIKE CONCAT('%', #{kw}, '%')
                    </when>
                    <otherwise>
                        AND (
                             A.subject LIKE CONCAT('%', #{kw}, '%')
                             OR
                             A.content LIKE CONCAT('%', #{kw}, '%')
                         )
                    </otherwise>
                </choose>
            </if>
            </script>
            """)
    List<Article> search(@Param("kwType") String kwType, @Param("kw") String kw);

    @Select("""
            <script>
            SELECT A.*,
            M.username AS extra_member_username,
            M.name AS extra_member_name
            FROM article AS A
            LEFT JOIN member AS M
            ON A.memberId = M.id
            </script>
            """)
    List<Article> getForPrintArticles();
}

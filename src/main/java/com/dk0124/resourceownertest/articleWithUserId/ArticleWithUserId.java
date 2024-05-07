package com.dk0124.resourceownertest.articleWithUserId;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(indexes = @Index(columnList = "userId"))
public class ArticleWithUserId {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    @NonNull
    private String title;
    @NonNull
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    public ArticleWithUserId(Long userId, @NonNull String title, @NonNull String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}

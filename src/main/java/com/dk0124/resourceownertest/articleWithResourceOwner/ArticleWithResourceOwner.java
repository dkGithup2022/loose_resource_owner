package com.dk0124.resourceownertest.articleWithResourceOwner;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ArticleWithResourceOwner {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "resource_owner_id")
    private ResourceOwner resourceOwner;
    @NonNull
    private String title;
    @NonNull
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;


    public ArticleWithResourceOwner(ResourceOwner resourceOwner, @NonNull String title, @NonNull String content) {
        this.resourceOwner = resourceOwner;
        this.title = title;
        this.content = content;
    }
}

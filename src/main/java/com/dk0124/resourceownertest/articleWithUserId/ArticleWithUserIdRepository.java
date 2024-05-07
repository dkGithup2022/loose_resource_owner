package com.dk0124.resourceownertest.articleWithUserId;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleWithUserIdRepository extends JpaRepository<ArticleWithUserId, Long> {
    public List<ArticleWithUserId> findByUserId(Long userId);
}

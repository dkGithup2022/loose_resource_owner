package com.dk0124.resourceownertest.articleWithResourceOwner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArticleWithResourceOwnerRepository extends JpaRepository<ArticleWithResourceOwner, Long> {
    @Query("SELECT a FROM ArticleWithResourceOwner a JOIN FETCH a.resourceOwner WHERE a.resourceOwner.ipv4 = :ipv4")
    List<ArticleWithResourceOwner> findByResourceOwnerIpv4(String ipv4);

    @Query("SELECT a FROM ArticleWithResourceOwner a JOIN FETCH a.resourceOwner WHERE a.resourceOwner.userId = :userId")
    List<ArticleWithResourceOwner> findByResourceOwnerUserId(Long userId);
}

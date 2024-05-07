package com.dk0124.resourceownertest.articleWithUserId;


import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ArticleWithUserRepositoryController {

    private final ArticleWithUserIdRepository repo;

    @PostMapping("/userId/save")
    public ResponseEntity<Long> doSave(@RequestBody SaveReq saveReq) {
        var article = new ArticleWithUserId(saveReq.userId(), saveReq.title(), saveReq.content());
        var created = repo.save(article);
        return ResponseEntity.ok(created.getId());
    }

    @GetMapping("/userId/read")
    public ResponseEntity<List<ArticleWithUserId>> doRead(@RequestBody ReadReq readReq) {
        var list = repo.findByUserId(readReq.userId);
        log.info("article: {}", list);
        return ResponseEntity.ok(list);
    }


    private static record SaveReq(Long userId, String title, String content) {
    }

    private static record ReadReq(Long userId) {
    }
}

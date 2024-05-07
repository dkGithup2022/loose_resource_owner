package com.dk0124.resourceownertest.articleWithResourceOwner;


import com.dk0124.resourceownertest.articleWithUserId.ArticleWithUserId;
import com.dk0124.resourceownertest.articleWithUserId.ArticleWithUserRepositoryController;
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
public class ResourceOwnerController {

    private final ArticleWithResourceOwnerRepository repo;


    @PostMapping("/resourceOwner/save")
    public ResponseEntity<Long> doSave(@RequestBody SaveReq saveReq) {
        var article = saveReq.ownerType == OwnerType.USER ?
                new ArticleWithResourceOwner(ResourceOwner.ofUserId(saveReq.userId()), saveReq.title(), saveReq.content()) :
                new ArticleWithResourceOwner(ResourceOwner.ofIpv4(saveReq.ipv4()),saveReq.title(), saveReq.content());

        var created = repo.save(article);
        return ResponseEntity.ok(created.getId());
    }

    @GetMapping("/resourceOwner/read")
    public ResponseEntity<List<ArticleWithResourceOwner>> doRead(@RequestBody ReadReq readReq) {
        var list = readReq.ownerType == OwnerType.USER ?
                repo.findByResourceOwnerUserId(readReq.userId()) :
                repo.findByResourceOwnerIpv4(readReq.ipv4());

        log.info("article: {}", list);
        return ResponseEntity.ok(list);
    }

    private static record SaveReq(OwnerType ownerType, Long userId, String ipv4, String title, String content) {
    }

    private static record ReadReq(OwnerType ownerType, Long userId, String ipv4) {
    }
}

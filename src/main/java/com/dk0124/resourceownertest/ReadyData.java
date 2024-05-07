package com.dk0124.resourceownertest;

import com.dk0124.resourceownertest.articleWithResourceOwner.ArticleWithResourceOwner;
import com.dk0124.resourceownertest.articleWithResourceOwner.ArticleWithResourceOwnerRepository;
import com.dk0124.resourceownertest.articleWithResourceOwner.ResourceOwner;
import com.dk0124.resourceownertest.articleWithUserId.ArticleWithUserId;
import com.dk0124.resourceownertest.articleWithUserId.ArticleWithUserIdRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReadyData implements ApplicationRunner {

    private final ArticleWithUserIdRepository repo;

    private final ArticleWithResourceOwnerRepository repo2;

    private static Random random = new Random();
    private static String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static int ITER = 1;
    private static int SIZE = 100;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // ready article with userId
        readyArticleWithUserId();

       //readyArticleWithResourceOwner();
    }

    public void readyArticleWithUserId() {
        IntStream.range(0, ITER)
                .forEach(e -> repo.saveAll(randArticleWithUserId()));
    }

    public void readyArticleWithResourceOwner(){
        // with userId
        IntStream.range(0,ITER)
                .forEach(e->repo2.saveAll(randArticleWithResourceOwner()));

        // with ip
        IntStream.range(0,ITER)
                .forEach(e->repo2.saveAll(randArticleWithIP()));
    }

    private Iterable<ArticleWithResourceOwner> randArticleWithIP() {
        List<ArticleWithResourceOwner> list = new ArrayList<>();
        IntStream.range(0,SIZE)
                .forEach(
                        e->list.add(new ArticleWithResourceOwner(
                                ResourceOwner.ofIpv4("192.168."+random.nextInt(100)+"."+random.nextInt(100)),
                                randomString(100),
                                randomString(2000)
                        ))
                );
        return list;
    }

    private List<ArticleWithResourceOwner> randArticleWithResourceOwner() {
        List<ArticleWithResourceOwner> list = new ArrayList<>();
        IntStream.range(0,SIZE)
                .forEach(
                        e->list.add(new ArticleWithResourceOwner(
                                ResourceOwner.ofUserId(random.nextLong(0, 10000)),
                                randomString(100),
                                randomString(2000)
                        ))
                );
        return list;
    }


    private List<ArticleWithUserId> randArticleWithUserId() {
        List<ArticleWithUserId> list = new ArrayList<>();
        IntStream.range(0, SIZE)
                .forEach(e -> list.add(new ArticleWithUserId(
                        random.nextLong(0, 10000),
                        randomString(100),
                        randomString(2000)
                )));
        return list;
    }

    private String randomString(int i) {
        StringBuffer buffer = new StringBuffer();
        IntStream.range(0, i)
                .forEach(e -> buffer.append(
                        characters.charAt(random.nextInt(characters.length()))
                ));
        return buffer.toString();
    }
}

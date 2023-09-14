package com.smsoft.aws.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void postSave_Load() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                        .title(title)
                        .content(content)
                        .author("tnals1569@gmail.com")
                        .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void baseTimeEntity_test() {
        //given
        LocalDateTime now = LocalDateTime.of(2023,9,14,0,0,0);
        postsRepository.save(Posts.builder()
                        .title("this is java")
                        .content("this is java content")
                        .author("sumin kim")
                        .build());
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        System.out.println(">> createDate = " + postsList.get(0).getCreateDate() + ", modifiedDate = " + postsList.get(0).getModifiedDate());
        assertThat(postsList.get(0).getCreateDate()).isAfter(now);
        assertThat(postsList.get(0).getModifiedDate()).isAfter(now);

    }

}

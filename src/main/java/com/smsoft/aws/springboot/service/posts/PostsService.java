package com.smsoft.aws.springboot.service.posts;

import com.smsoft.aws.springboot.domain.posts.Posts;
import com.smsoft.aws.springboot.domain.posts.PostsRepository;
import com.smsoft.aws.springboot.web.dto.PostsResponseDto;
import com.smsoft.aws.springboot.web.dto.PostsSaveRequestDto;
import com.smsoft.aws.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not post. id = " + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not post id = " + id));

        return new PostsResponseDto(entity);
    }
}

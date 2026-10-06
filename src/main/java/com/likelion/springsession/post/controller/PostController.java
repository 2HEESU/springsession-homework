package com.likelion.springsession.post.controller;

import com.likelion.springsession.post.dto.PostCreateRequest;
import com.likelion.springsession.post.dto.PostUpdateRequest;
import com.likelion.springsession.post.entity.Post;
import com.likelion.springsession.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.likelion.springsession.post.dto.PostDetailResponse;
import com.likelion.springsession.post.dto.PostSummaryResponse;
import com.likelion.springsession.post.service.PostService;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    public PostController(PostService postService, PostRepository postRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
    }

    @GetMapping
    public List<PostSummaryResponse> getPosts() {
        return postService.getPostSummaries();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDetailResponse createPost(
            @Valid @RequestBody PostCreateRequest request
            ) {
        return postService.createPost(request);
    }

    @GetMapping("/{postId}")
    public PostDetailResponse getPost(
            @PathVariable("postId") Long postId
    ) {
        return postService.getPost(postId);
    }

    @PutMapping("/{postId}")
    public PostDetailResponse updatePost(
            @PathVariable("postId") Long postId,
            @Valid @RequestBody PostUpdateRequest request
            ) {
        return postService.updatePost(postId, request);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
    }
}
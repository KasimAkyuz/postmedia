package com.akyuz.postmedia.controllers;

import com.akyuz.postmedia.entities.Post;
import com.akyuz.postmedia.request.PostCreateRequest;
import com.akyuz.postmedia.request.PostUpdateRequest;
import com.akyuz.postmedia.services.PostService;
import com.akyuz.postmedia.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;

    }

    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId){
        return postService.getAllPost(userId);
    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }
    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostRequest){
        return postService.createaOnePost(newPostRequest);

    }
    @PutMapping("{postId}")
    public Post updateOnePost(@PathVariable Long postId,
                              @RequestBody PostUpdateRequest postUpdateRequest){
        return postService.updateOnePostById(postId,postUpdateRequest);

    }
    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId){
        postService.deleteOnePostById(postId);
    }




}

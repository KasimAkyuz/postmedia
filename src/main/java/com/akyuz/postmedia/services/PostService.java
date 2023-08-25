package com.akyuz.postmedia.services;

import com.akyuz.postmedia.entities.Post;
import com.akyuz.postmedia.entities.User;
import com.akyuz.postmedia.repos.PostRepository;
import com.akyuz.postmedia.repos.UserRepository;
import com.akyuz.postmedia.request.PostCreateRequest;
import com.akyuz.postmedia.request.PostUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository  userRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserRepository userRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public List<Post> getAllPost(Optional<Long> userId){
        if(userId.isPresent()){
           return postRepository.findByUserId(userId.get());
        }

        return postRepository.findAll();
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createaOnePost(PostCreateRequest newCreateRequest) {
        User user = userService.getOneUser(newCreateRequest.getUserId());
        if(user==null) return null;
        Post toSave= new Post();
        toSave.setId(newCreateRequest.getId());
        toSave.setText(newCreateRequest.getText());
        toSave.setTitle(newCreateRequest.getTitle());
        toSave.setUser(user);

        return postRepository.save(toSave);
    }

    public Post updateOnePostById(Long postId,PostUpdateRequest postUpdateRequest) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()){
            Post toUpdate= post.get();
            toUpdate.setTitle(postUpdateRequest.getTitle());
            toUpdate.setText(postUpdateRequest.getText());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        return null;




    }

    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }


    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }
}

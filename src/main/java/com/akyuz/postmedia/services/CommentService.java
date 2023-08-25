package com.akyuz.postmedia.services;

import com.akyuz.postmedia.entities.Comment;
import com.akyuz.postmedia.entities.Post;
import com.akyuz.postmedia.entities.User;
import com.akyuz.postmedia.repos.CommentRepository;
import com.akyuz.postmedia.request.CommentCreateRequest;
import com.akyuz.postmedia.request.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> getAllCommentsList(Optional<Long> postId, Optional<Long> userId) {
        if(postId.isPresent() && userId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }
        else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        }
        else if (postId.isPresent()) {
            return  commentRepository.findByPostId(postId.get());
        }
        else return commentRepository.findAll();

    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest commentCreateRequest) {
        User user = userService.getOneUser(commentCreateRequest.getUserId());
        Post post = postService.getPostById(commentCreateRequest.getPostId());

        if(user!= null && post!=null){
            Comment commentToSave= new Comment();
            commentToSave.setId(commentCreateRequest.getId());
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            commentToSave.setText(commentCreateRequest.getText());

            return commentRepository.save(commentToSave);
        }else  return null;
    }

    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest commentToUpdate) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()){
            comment.get().setText(commentToUpdate.getText());
            commentRepository.save(comment.get());
            return comment.get();
        }else return null;
    }

    public void deleteOneComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}

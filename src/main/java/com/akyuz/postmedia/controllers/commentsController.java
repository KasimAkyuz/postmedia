package com.akyuz.postmedia.controllers;

import com.akyuz.postmedia.entities.Comment;
import com.akyuz.postmedia.request.CommentCreateRequest;
import com.akyuz.postmedia.request.CommentUpdateRequest;
import com.akyuz.postmedia.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class commentsController {

    private final CommentService commentService;

    public commentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam("postId" ) Optional<Long> postId
                                        , @RequestParam("userId") Optional<Long> userId){
        return commentService.getAllCommentsList(postId,userId);
    }
    @GetMapping("/{commentId}")
    public Comment getOneCommen(@PathVariable Long commentId){
        return commentService.getOneCommentById(commentId);

    }
    @PostMapping
    public Comment createComment(@RequestBody CommentCreateRequest commentCreateRequest){
        return commentService.createOneComment(commentCreateRequest);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId
                                    , @RequestBody CommentUpdateRequest commentUpdateRequest){
        return  commentService.updateOneCommentById(commentId,commentUpdateRequest);
    }
    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
        commentService.deleteOneComment(commentId);

    }


}

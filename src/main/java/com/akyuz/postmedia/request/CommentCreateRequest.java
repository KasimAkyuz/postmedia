package com.akyuz.postmedia.request;

import com.akyuz.postmedia.entities.User;
import lombok.Data;

@Data
public class CommentCreateRequest {
    Long id;
    Long userId;
    Long postId;
    String text;
}

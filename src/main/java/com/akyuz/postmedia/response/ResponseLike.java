package com.akyuz.postmedia.response;

import com.akyuz.postmedia.entities.Like;

public class ResponseLike {
    Long id;
    Long userId;
    Long postId;

    public ResponseLike(Like entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.postId = entity.getPost().getId();
    }
}

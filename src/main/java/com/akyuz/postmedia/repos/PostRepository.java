package com.akyuz.postmedia.repos;

import com.akyuz.postmedia.entities.Post;
import com.akyuz.postmedia.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByUserId(Long userId);
}

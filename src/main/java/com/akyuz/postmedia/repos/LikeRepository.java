package com.akyuz.postmedia.repos;

import com.akyuz.postmedia.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {
    List<Like> findByPostId(Long aLong);

    List<Like> findByUserId(Long aLong);

    List<Like> findByUserIdAndPostId(Long aLong, Long aLong1);
}

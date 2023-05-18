package com.smapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smapi.models.Like;
import com.smapi.models.Post;
import com.smapi.models.User;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

	boolean existsByPostAndUser(Post post, User user);

	Like findByPostAndUser(Post post, User user);

	void deleteByPostAndUser(Post post, User user);

	Like findByPost(Post post);

}

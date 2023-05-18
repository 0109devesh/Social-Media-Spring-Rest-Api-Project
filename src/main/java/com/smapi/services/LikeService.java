package com.smapi.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smapi.models.Like;
import com.smapi.models.Post;
import com.smapi.models.User;
import com.smapi.repositories.LikeRepository;
import com.smapi.repositories.PostRepository;
import com.smapi.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class LikeService {

	
	@Autowired
	private LikeRepository likeRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HttpSession httpSession;

	public Iterable<Like> getAllLikes() {
		return likeRepository.findAll();
	}

	public Like createLike(Like like) {
		like.setCreatedAt(LocalDateTime.now());
		return likeRepository.save(like);
	}

	public Like getLikeById(Long likeId) {
		return likeRepository.findById(likeId).orElse(null);
	}

	public Like updateLike(Long likeId, Like updatedLike) {
		Optional<Like> existingLike = likeRepository.findById(likeId);
		if (existingLike.isPresent()) {
			Like like = existingLike.get();
			like.setUpdatedAt(LocalDateTime.now());
			return likeRepository.save(like);
		} else {
			return null;
		}
	}

	public boolean deleteLike(Long likeId) {
		Optional<Like> existingLike = likeRepository.findById(likeId);
		if (existingLike.isPresent()) {
			likeRepository.deleteById(likeId);
			return true;
		} else {
			return false;
		}
	}

	// for like increment logic

	public boolean toggleLike(Long postId, Long userId) {
		Post post = postRepository.findById(postId).orElse(null);
		User user = userRepository.findById(userId).orElse(null);
		if (post == null || user == null) {
			return false;
		}

		Like like = likeRepository.findByPostAndUser(post, user);
		if (like == null) {
			like = new Like();
			like.setPost(post);
			like.setUser(user);
			likeRepository.save(like);
			post.setLikeCount(post.getLikeCount() + 1);
			postRepository.save(post);
			return true;
		} else {
			likeRepository.delete(like);
			post.setLikeCount(post.getLikeCount() - 1);
			postRepository.save(post);
			return false;
		}
	}

	
//for httpSession code 

//	// Method to get the current user's ID
//	private Long getUserId() {
//		User user = (User) httpSession.getAttribute("user");
//		if (user != null) {
//			return user.getUserId();
//		}
//		return null;
//	}
//
//	// Method to get the current user
//	private User getCurrentUser() {
//		return (User) httpSession.getAttribute("user");
//	}

}

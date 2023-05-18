package com.smapi.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smapi.models.Like;
import com.smapi.models.LikeRequest;
import com.smapi.services.LikeService;

@RestController
@RequestMapping("/likes")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class LikeController {

	@Autowired
	private LikeService likeService;

	@GetMapping
	public Iterable<Like> getAllLikes() {
		return likeService.getAllLikes();
	}

	@PostMapping
	public ResponseEntity<?> createLike(@RequestBody LikeRequest likeRequest) {

		boolean liked = likeService.toggleLike(likeRequest.getPostId(), likeRequest.getUserId());

		return ResponseEntity.ok().body(Map.of("liked", liked));

	}

	@GetMapping("/{likeId}")
	public ResponseEntity<Like> getLikeById(@PathVariable Long likeId) {
		Like like = likeService.getLikeById(likeId);
		if (like != null) {
			return ResponseEntity.ok(like);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{likeId}")
	public ResponseEntity<Like> updateLike(@PathVariable Long likeId, @RequestBody Like like) {
		Like updatedLike = likeService.updateLike(likeId, like);
		if (updatedLike != null) {
			return ResponseEntity.ok(updatedLike);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{likeId}")
	public ResponseEntity<Void> deleteLike(@PathVariable Long likeId) {
		boolean deleted = likeService.deleteLike(likeId);
		if (deleted) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}

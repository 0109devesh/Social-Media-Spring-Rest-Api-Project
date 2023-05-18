package com.smapi.controllers;

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

import com.smapi.models.Post;
import com.smapi.services.PostService;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping
	public Iterable<Post> getAllPosts() {
		return postService.getAllPosts();
	}

	@PostMapping
	public Post createPost(@RequestBody Post post) {
		return postService.createPost(post);
	}

	@GetMapping("/{postId}")
	public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
		Post post = postService.getPostById(postId);
		if (post != null) {
			return ResponseEntity.ok(post);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{postId}")
	public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody Post post) {
		Post updatedPost = postService.updatePost(postId, post);
		if (updatedPost != null) {
			return ResponseEntity.ok(updatedPost);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{postId}")
	public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
		boolean deleted = postService.deletePost(postId);
		if (deleted) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}

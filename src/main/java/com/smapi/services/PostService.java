package com.smapi.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smapi.models.Post;
import com.smapi.repositories.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post createPost(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post updatePost(Long postId, Post updatedPost) {
        Optional<Post> existingPost = postRepository.findById(postId);
        if (existingPost.isPresent()) {
            Post post = existingPost.get();
            post.setContent(updatedPost.getContent());
            post.setUpdatedAt(LocalDateTime.now());
            return postRepository.save(post);
        } else {
            return null;
        }
    }

    public boolean deletePost(Long postId) {
        Optional<Post> existingPost = postRepository.findById(postId);
        if (existingPost.isPresent()) {
            postRepository.deleteById(postId);
            return true;
        } else {
            return false;
        }
    }

	
}


package com.smapi.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long postId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "content", columnDefinition = "TEXT")
	private String content;
	
	@Column(name = "like_count", columnDefinition = "INT DEFAULT 0")
	private Integer likeCount;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shared_from_post_id")
	private Post sharedFromPost;

	@Column(name = "share_count")
	private Integer shareCount;

	// Constructors, getters, and setters

	public Post() {

		this.likeCount = 0;
	}

	public Post(Long postId, User user, String content, Integer likeCount, LocalDateTime createdAt,
			LocalDateTime updatedAt, Post sharedFromPost, Integer shareCount) {
		super();
		this.postId = postId;
		this.user = user;
		this.content = content;
		this.likeCount = likeCount;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.sharedFromPost = sharedFromPost;
		this.shareCount = shareCount;
	}

	// as per like requests 
	
	 public void setId(Long postId) {
	        this.postId = postId;
	    }
	
	
	
	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Post getSharedFromPost() {
		return sharedFromPost;
	}

	public void setSharedFromPost(Post sharedFromPost) {
		this.sharedFromPost = sharedFromPost;
	}

	public Integer getShareCount() {
		return shareCount;
	}

	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}

	
	
}

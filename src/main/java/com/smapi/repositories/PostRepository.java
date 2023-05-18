package com.smapi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.smapi.models.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

}

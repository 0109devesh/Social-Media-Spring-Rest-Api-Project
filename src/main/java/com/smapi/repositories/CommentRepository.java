package com.smapi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.smapi.models.Comment;

public interface CommentRepository extends CrudRepository<Comment,Long> {

}

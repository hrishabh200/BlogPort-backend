package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Comment;
import com.example.demo.Entity.User;
import com.example.demo.Entity.Post;

@Repository
	public interface CommentRepo extends JpaRepository<Comment, Long> {
	    List<Comment> findByPost(Post post);

	    List<Comment> findAllByUser(User user);
	}

package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Post;
import com.example.demo.Entity.Community;
import com.example.demo.Entity.User;
@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Community subreddit);

    List<Post> findByUser(User user);
}

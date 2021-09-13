package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Post;
import com.example.demo.Entity.User;
import com.example.demo.Entity.Vote;

@Repository
public interface VoteRepo extends JpaRepository<Vote, Long> 
	{
	    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
	}

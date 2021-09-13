package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Community;

@Repository
public interface CommunityRepo extends JpaRepository<Community, Long> {

   Optional<Community> findByCommunityName(String communityName);
}


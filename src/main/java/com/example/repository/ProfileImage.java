package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Memberprofileimage;

@Repository
public interface ProfileImage extends JpaRepository<Memberprofileimage, Long> {
    
}

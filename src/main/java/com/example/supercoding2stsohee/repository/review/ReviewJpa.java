package com.example.supercoding2stsohee.repository.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewJpa extends JpaRepository<Review, Integer> {
}

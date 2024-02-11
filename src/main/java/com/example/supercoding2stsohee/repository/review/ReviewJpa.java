package com.example.supercoding2stsohee.repository.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewJpa extends JpaRepository<Review, Integer> {

    @Query(
            "SELECT r " +
                    "FROM Review r " +
                    "JOIN FETCH r.user u " +
                    "WHERE u.userId = ?1"
    )
    List<Review> findByUserId(Integer userId);
}

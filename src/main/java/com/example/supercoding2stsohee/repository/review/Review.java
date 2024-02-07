package com.example.supercoding2stsohee.repository.review;

import com.example.supercoding2stsohee.repository.product.Product;
import com.example.supercoding2stsohee.repository.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer reviewId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "review_contents")
    private String reviewContents;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createdAt;
}

package com.example.supercoding2stsohee.repository.product;

import com.example.supercoding2stsohee.repository.productOption.ProductOption;
import com.example.supercoding2stsohee.repository.productPhoto.ProductPhoto;
import com.example.supercoding2stsohee.repository.review.Review;
import com.example.supercoding2stsohee.repository.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "product")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_price", nullable = false)
    private Integer productPrice;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "product_status", nullable = false)
    private String productStatus;

    @Column(name = "rating", nullable = false)
    private Float rating;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "finish_at")
    private LocalDateTime finishedAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductPhoto> productPhotos;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductOption> productOptions;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Review> reviews;



}

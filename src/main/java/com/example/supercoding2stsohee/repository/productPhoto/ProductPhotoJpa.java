package com.example.supercoding2stsohee.repository.productPhoto;

import com.example.supercoding2stsohee.repository.product.Product;
import com.example.supercoding2stsohee.repository.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPhotoJpa extends JpaRepository<ProductPhoto, Integer> {

    List<ProductPhoto> findByProduct(Product product);
}

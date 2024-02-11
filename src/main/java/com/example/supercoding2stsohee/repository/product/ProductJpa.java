package com.example.supercoding2stsohee.repository.product;

import com.example.supercoding2stsohee.web.dto.product.ProductMainResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductJpa extends JpaRepository<Product, Integer> {


    @Query(
        "SELECT p, pp, AVG(r.score) " +
                "FROM Product p " +
                "LEFT JOIN p.productPhotos pp " +
                "LEFT JOIN Review r ON p.productId = r.product.productId " +
                "WHERE pp.photoType = true " +
                "GROUP BY p.productId"
    )
    List<ProductMainResponse> findAllProducts();


//    @Query(
//            "SELECT p, pp, po " +
//                    "FROM Product p " +
//                    "JOIN FETCH p.productPhotos pp " +
//                    "JOIN FETCH p.productOptions po "
//    )
}

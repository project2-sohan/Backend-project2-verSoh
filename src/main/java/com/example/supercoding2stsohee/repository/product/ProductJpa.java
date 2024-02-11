package com.example.supercoding2stsohee.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductJpa extends JpaRepository<Product, Integer> {
    @Query(
            "SELECT p, pp, po " +
                    "FROM Product p " +
                    "JOIN FETCH p.productPhotos pp " +
                    "JOIN FETCH p.productOptions po "
    )
    //dto만들기
    //JPQL다시
    List<Product> findAllProducts();
}

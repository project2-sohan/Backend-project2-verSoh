package com.example.supercoding2stsohee.repository.productOption;

import com.example.supercoding2stsohee.repository.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOptionJpa extends JpaRepository<ProductOption, Integer> {

    List<ProductOption> findByProduct(Product product);
}

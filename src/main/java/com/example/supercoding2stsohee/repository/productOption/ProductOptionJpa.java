package com.example.supercoding2stsohee.repository.productOption;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOptionJpa extends JpaRepository<ProductOption, Integer> {
}

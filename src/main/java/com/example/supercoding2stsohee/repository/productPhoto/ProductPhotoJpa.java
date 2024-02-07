package com.example.supercoding2stsohee.repository.productPhoto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPhotoJpa extends JpaRepository<ProductPhoto, Integer> {
}

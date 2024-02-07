package com.example.supercoding2stsohee.repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartJpa extends JpaRepository<Cart,Integer> {
}

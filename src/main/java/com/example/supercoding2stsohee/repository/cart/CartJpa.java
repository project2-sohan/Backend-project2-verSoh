package com.example.supercoding2stsohee.repository.cart;

import com.example.supercoding2stsohee.repository.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartJpa extends JpaRepository<Cart,Integer> {
    List<Cart> findByUser(User user);
}

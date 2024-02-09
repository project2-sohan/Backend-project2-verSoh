package com.example.supercoding2stsohee.web.controller;


import com.example.supercoding2stsohee.repository.cart.CartJpa;
import com.example.supercoding2stsohee.repository.orderItem.OrderItemJpa;
import com.example.supercoding2stsohee.repository.orderTable.OrderTableJpa;
import com.example.supercoding2stsohee.repository.product.ProductJpa;
import com.example.supercoding2stsohee.repository.productOption.ProductOptionJpa;
import com.example.supercoding2stsohee.repository.productPhoto.ProductPhotoJpa;
import com.example.supercoding2stsohee.repository.review.ReviewJpa;
import com.example.supercoding2stsohee.repository.roles.RolesJpa;
import com.example.supercoding2stsohee.repository.userRoles.UserRolesJpa;
import com.example.supercoding2stsohee.repository.user.User;
import com.example.supercoding2stsohee.repository.user.UserJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

@RestController
@RequiredArgsConstructor
public class test {

    private final UserJpa userJpa;
    private final RolesJpa rolesJpa;
    private final UserRolesJpa userRolesJpa;
    private final ProductJpa productJpa;
    private final ProductOptionJpa productOptionJpa;
    private final ProductPhotoJpa productPhotoJpa;
    private final ReviewJpa reviewJpa;
    private final CartJpa cartJpa;
    private final OrderTableJpa orderTableJpa;
    private final OrderItemJpa orderItemJpa;

    @GetMapping("/test")
    public String test() {
        User user = userJpa.findById(1).orElseThrow(() -> new NotFoundException("ss"));

        Integer userId = user.getUserId();

        return "test success: " + userId;
    }
}

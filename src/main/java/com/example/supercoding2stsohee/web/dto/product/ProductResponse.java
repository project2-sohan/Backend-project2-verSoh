package com.example.supercoding2stsohee.web.dto.product;

import lombok.*;
import org.mapstruct.Mapper;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Integer productId;
    private String userName;
    private String productName;
    private Integer productPrice;
    private String category;
    private String productStatus;
    private List<PhotoRequestDto> photoRequestDtoList;
    private List<ProductOptionRequestDto> productOptionRequestList;

}

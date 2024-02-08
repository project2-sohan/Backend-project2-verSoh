package com.example.supercoding2stsohee.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPhotoResponse {
    private Integer productPhotoId;
    private Integer productId;
    private String photoUrl;
    private Boolean photoType;

}

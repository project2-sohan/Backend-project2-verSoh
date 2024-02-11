package com.example.supercoding2stsohee.web.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.A;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhotoRequestDto {
    private Integer photoId;
    private String photoUrl;
    private Boolean photoType; //true: main pic, false: product pic

    public PhotoRequestDto(String photoUrl, Boolean photoType) {
        this.photoUrl = photoUrl;
        this.photoType = photoType;
    }
}

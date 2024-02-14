package com.example.supercoding2stsohee.web.dto.sign;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SocialAccountDto {
    private Long socialId;
    private String provider;
    private String nickName;
    private String email;
    private String imageUrl;
    private String gender;
    private String dateOfBirth;
}

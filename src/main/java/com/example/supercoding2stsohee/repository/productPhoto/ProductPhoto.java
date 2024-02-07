package com.example.supercoding2stsohee.repository.productPhoto;

import com.example.supercoding2stsohee.repository.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_photo")
public class ProductPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_photo_id")
    private Integer productPhotoId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "photo_url", nullable = false)
    private String photoUrl;

    @Column(name = "photo_type", nullable = false)
    private Boolean photoType;
}

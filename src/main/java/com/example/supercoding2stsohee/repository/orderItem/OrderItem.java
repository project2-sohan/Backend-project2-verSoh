package com.example.supercoding2stsohee.repository.orderItem;

import com.example.supercoding2stsohee.repository.orderTable.OrderTable;
import com.example.supercoding2stsohee.repository.productOption.ProductOption;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Integer orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderTable orderTable;

    @ManyToOne
    @JoinColumn(name = "product_option_id")
    private ProductOption productOption;

    @Column(name = "item_amount", nullable = false)
    private Integer itemAmount;

    @Column(name = "order_item_price", nullable = false)
    private Integer orderItemPrice;

}

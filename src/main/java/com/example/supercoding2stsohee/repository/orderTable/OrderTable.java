package com.example.supercoding2stsohee.repository.orderTable;

import com.example.supercoding2stsohee.repository.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class OrderTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "ship", nullable = false)
    private String ship;

    @Column(name = "order_status", nullable = false)
    private String orderStatus;

    @Column(name = "order_request_contents", nullable = false)
    private String orderRequestContents;

    @Column(name = "order_price",nullable = false)
    private Integer order_price;

    @Column(name = "order_at", nullable = false)
    private LocalDateTime orderdAt;
}

package com.example.shoeshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "oderDetail")
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailEntity {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private Float detail_total_price;

     private int detail_number;

     @ManyToOne()
     @JoinColumn(name = "oder_id")
     private OrderEntity orderEntityId;

     @ManyToOne()
     @JoinColumn(name = "product_id")
     private ProductEntity productEntityId;

//     public OrderDetailEntity(Long productEntityId, Long orderId, int quantity, Float price) {
//     }
}

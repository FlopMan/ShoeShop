package com.example.shoeshop.Service;

import com.example.shoeshop.entity.OrderEntity;


public interface IOrderService {
   Long getOrderID(OrderEntity orderEntity);
   OrderEntity getOrderById(Long id);
}

package com.example.shoeshop.Service;

import com.example.shoeshop.Repository.OrderDetailRepository;
import com.example.shoeshop.entity.OrderDetailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService implements IOrderDetailService{
     @Autowired
     private OrderDetailRepository orderDetailRepository;

     @Override
     public void add(OrderDetailEntity entity) {
          orderDetailRepository.save(entity);
     }
}

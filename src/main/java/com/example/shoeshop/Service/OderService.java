package com.example.shoeshop.Service;

import com.example.shoeshop.Repository.IOderRepository;
import com.example.shoeshop.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OderService implements IOrderService{
    @Autowired
    private IOderRepository iOderRepository;

    @Override
    public Long getOrderID(OrderEntity orderEntity) {
       OrderEntity entity = iOderRepository.save(orderEntity);
        return entity.getId();
    }

     @Override
     public OrderEntity getOrderById(Long id) {
          OrderEntity  orderEntity = iOderRepository.findById(id).get();
          return orderEntity;
     }
}

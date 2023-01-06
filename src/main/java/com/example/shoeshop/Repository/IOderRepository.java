package com.example.shoeshop.Repository;

import com.example.shoeshop.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOderRepository extends JpaRepository<OrderEntity,Long> {
}

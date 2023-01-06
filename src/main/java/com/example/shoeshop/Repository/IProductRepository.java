package com.example.shoeshop.Repository;

import com.example.shoeshop.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity,Long>{
    List<ProductEntity> findProductEntityByNameContainingIgnoreCase(String name);

//    @Query("from ProductEntity where price between :min and :max")
//    List<ProductEntity> search(@Param("min")double min, @Param("max")double max);
    List<ProductEntity> findByPriceBetween(double min, double max);

}

package com.example.shoeshop.Service;

import com.example.shoeshop.Dto.CategoryDTO;
import com.example.shoeshop.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ICategoryService {
    List<CategoryDTO> getAllCategory();
    List<Long> getListIdByCateId(Long id);
    void deleteCategory(Long id);
    void updateIdCateOfProdWhenDelete(Long id);

    void updateDisplayCategory(Long id);
}

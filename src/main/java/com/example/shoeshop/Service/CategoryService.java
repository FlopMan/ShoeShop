package com.example.shoeshop.Service;

import com.example.shoeshop.Dto.CategoryDTO;
import com.example.shoeshop.Repository.CategoryRepository;
import com.example.shoeshop.Repository.IProductRepository;
import com.example.shoeshop.entity.CategoryEntity;
import com.example.shoeshop.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private IProductRepository productRepository;
    @Override
    public List<CategoryDTO> getAllCategory() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (CategoryEntity entity : categoryEntityList){
            if (entity.getStatusDisplay()==1){
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(entity.getId());
            categoryDTO.setName(entity.getName());
            categoryDTOS.add(categoryDTO);
            }
        }
        return categoryDTOS;
    }

    @Override
    public List<Long> getListIdByCateId(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).get();
        List<Long> listId = new ArrayList<>();
        categoryEntity.getProductEntityList().stream().forEach(e->listId.add(e.getId()));
        return listId;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void updateIdCateOfProdWhenDelete(Long id) {
        List<ProductEntity> list = new ArrayList<>();
        CategoryEntity categoryEntity = categoryRepository.findById(id).get();
        List<ProductEntity> productEntityList = categoryEntity.getProductEntityList();
        for (ProductEntity entity : productEntityList){
            ProductEntity productEntity = productRepository.findById(entity.getId()).get();
            productEntity.setId(null);
            list.add(productEntity);
        }
        productRepository.saveAll(list);
    }

    @Override
    public void updateDisplayCategory(Long id) {
        CategoryEntity categoryEntity= categoryRepository.findById(id).get();
        categoryEntity.setStatusDisplay(0l);
        categoryRepository.save(categoryEntity);

    }
}

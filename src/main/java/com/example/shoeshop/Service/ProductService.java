package com.example.shoeshop.Service;

import com.example.shoeshop.Dto.ProductDTO;
import com.example.shoeshop.Repository.CategoryRepository;
import com.example.shoeshop.Repository.IProductRepository;
import com.example.shoeshop.entity.CategoryEntity;
import com.example.shoeshop.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements  IProductService{

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<ProductDTO> getAllProduct() {
        List<ProductEntity> productEntityList = productRepository.findAll();
        List<ProductDTO> dtoList = new ArrayList<>();
        for (ProductEntity entity : productEntityList){
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(entity.getId());
            productDTO.setName(entity.getName());
            productDTO.setPrice(entity.getPrice());
            productDTO.setImageSp(entity.getImageSp());
            productDTO.setDescription(entity.getDescription());
            dtoList.add(productDTO);
        }
        return dtoList;
    }

    @Override
    public ProductDTO getOneProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id).get();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productEntity.getId());
        productDTO.setName(productEntity.getName());
        productDTO.setImageSp(productEntity.getImageSp());
        productDTO.setPrice(productEntity.getPrice());
        productDTO.setDescription(productEntity.getDescription());
        return productDTO;
    }

    @Override
    public List<ProductDTO> getProductByListId(List<Long> id) {
        return null;
    }

    @Override
    public List<ProductDTO> getProductBySearch(String name) {
        List<ProductEntity> productEntityList =  productRepository.findProductEntityByNameContainingIgnoreCase(name);
        List<ProductDTO> dtoList = new ArrayList<>() ;
        for (ProductEntity entity : productEntityList){
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(entity.getId());
            productDTO.setName(entity.getName());
            productDTO.setPrice(entity.getPrice());
            productDTO.setImageSp(entity.getImageSp());
            productDTO.setDescription(entity.getDescription());
            dtoList.add(productDTO);
        }
        return dtoList;

    }

    @Override
    public List<ProductDTO> getListProductByListId(List<Long> id) {
        List<ProductEntity> entityList = productRepository.findAllById(id);
        List<ProductDTO> dtoList = new ArrayList<>();
        for (ProductEntity entity : entityList){
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(entity.getId());
            productDTO.setName(entity.getName());
            productDTO.setImageSp(entity.getImageSp());
            productDTO.setPrice(entity.getPrice());
            productDTO.setDescription(entity.getDescription());
            dtoList.add(productDTO);
        }
        return dtoList;
    }


    @Override
    public List<Long> getListIdProductByIdCate(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).get();
        List<Long> listIdProduct = new ArrayList<>();
        categoryEntity.getProductEntityList().stream().forEach(e->listIdProduct.add(e.getId()));
        return listIdProduct;
    }

    @Override
    public void editProduct(ProductDTO productDTO) {
        ProductEntity productEntity = productRepository.findById(productDTO.getId()).get();
        productEntity.setId(productDTO.getId());
        productEntity.setName(productDTO.getName());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setImageSp(productDTO.getImageSp());
        productEntity.setCategoryEntity(categoryRepository.findById(productDTO.getIdCategory()).get());
        productRepository.save(productEntity);
    }

    @Override
    public void saveNewProduct(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productDTO.getId());
        productEntity.setName(productDTO.getName());
        productEntity.setImageSp(productDTO.getImageSp());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setCategoryEntity(categoryRepository.findById(productDTO.getIdCategory()).get());
        productRepository.save(productEntity);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> searchByPrice(double min, double max) {
        List<ProductEntity> productEntityList = productRepository.findByPriceBetween(min,max);
        List<ProductDTO> list = new ArrayList<>();
        for (ProductEntity entity : productEntityList){
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(entity.getId());
            productDTO.setName(entity.getName());
            productDTO.setPrice(entity.getPrice());
            productDTO.setImageSp(entity.getImageSp());
            productDTO.setDescription(entity.getDescription());
            list.add(productDTO);
        }
        return list;
    }

    @Override
    public Page<ProductDTO> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        Page<ProductDTO> dtoPage = productRepository.findAll(pageable).map(ProductDTO::toDto);
        return  dtoPage;
    }

}

package com.example.shoeshop.Dto;

import com.example.shoeshop.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private String imageSp;
    private String description;
    private String color;
    private Long idCategory;
//    private Long productId;
    private List<CategoryDTO> categoryDTOList = new ArrayList<>();
    public static ProductDTO toDto(ProductEntity productEntity){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productEntity.getId());
        productDTO.setName(productEntity.getName());
        productDTO.setPrice(productEntity.getPrice());
        productDTO.setImageSp(productEntity.getImageSp());
        productDTO.setDescription(productEntity.getDescription());

        return productDTO;

    };
}

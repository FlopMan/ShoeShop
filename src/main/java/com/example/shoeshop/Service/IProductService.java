package com.example.shoeshop.Service;


import com.example.shoeshop.Dto.ProductDTO;
import org.springframework.data.domain.Page;


import java.util.List;

public interface IProductService {
    List<ProductDTO> getAllProduct();
    ProductDTO getOneProductById(Long id);
    List<ProductDTO> getProductByListId(List<Long> id);
    List<ProductDTO> getProductBySearch(String name);
    List<ProductDTO> getListProductByListId(List<Long> id);
    List<Long> getListIdProductByIdCate(Long id);

    void editProduct(ProductDTO productDTO);
    void saveNewProduct(ProductDTO productDTO);
    void deleteProduct(Long id);
    List<ProductDTO> searchByPrice(double min,double max);
    Page<ProductDTO> findPaginated(int pageNo, int pageSize);


}

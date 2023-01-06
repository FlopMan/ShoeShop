package com.example.shoeshop.Controller;

import com.example.shoeshop.Dto.ProductDTO;
import com.example.shoeshop.Service.ICategoryService;
import com.example.shoeshop.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.http.HttpResponse;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private ICategoryService iCategoryService;
    @GetMapping(value = "/detailCategory/{id}")
    public ResponseEntity<List<ProductDTO>> showProductByCate(@PathVariable("id")Long id){
        List<Long> listId = iCategoryService.getListIdByCateId(id); // lấy ra danh sách id product theo id category
        List<ProductDTO> productDTOList = iProductService.getListProductByListId(listId); // lấy ra danh sách prodcut theo id đc truyền
        return new ResponseEntity<List<ProductDTO>>(productDTOList,HttpStatus.OK); // trả ra list product ( result trong ajax)
    }
}

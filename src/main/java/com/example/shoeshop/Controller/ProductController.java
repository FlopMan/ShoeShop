package com.example.shoeshop.Controller;

import com.example.shoeshop.Dto.CategoryDTO;
import com.example.shoeshop.Dto.ProductDTO;
import com.example.shoeshop.Dto.SearchDTO;
import com.example.shoeshop.Service.ICategoryService;
import com.example.shoeshop.Service.IProductService;
import com.example.shoeshop.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private ICategoryService iCategoryService;
    @GetMapping(value = "/shop")
    public String productView(Model model){
        int pageSize = 9;
        int pageNo = 1;
        Page<ProductDTO> page = iProductService.findPaginated(pageNo,pageSize); // page chứa số phần tủ , danh sách các phần tủ
        List<ProductDTO> list = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        List<CategoryDTO> listCate = iCategoryService.getAllCategory();
        model.addAttribute("listCategory",listCate);
        model.addAttribute("listProduct",list);
        return "shop_view";

    }

    @GetMapping(value = "/detail/{id}")
    public String detailProduct(@PathVariable("id")Long id,Model model){
        ProductDTO productDTO = iProductService.getOneProductById(id);
        List<ProductDTO> dtoList = iProductService.getAllProduct();
        model.addAttribute("listProduct",dtoList);
        model.addAttribute("detailProd",productDTO);
        return "product_detail";
    }

    @PostMapping(value = "/add")
    public String addProduct(@ModelAttribute("newProductDto")ProductDTO productDTO, Model model){
        iProductService.saveNewProduct(productDTO);
        List<ProductDTO> productDTOList = iProductService.getAllProduct();
        List<CategoryDTO> list = iCategoryService.getAllCategory();
        model.addAttribute("listCategory",list);
        model.addAttribute("listPro",productDTOList);
        return "admin_view";
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<List<ProductDTO>> deleteProduct(@PathVariable("id")Long id){
        iProductService.deleteProduct(id);
        List<ProductDTO> dtoList = iProductService.getAllProduct();
        return new ResponseEntity<List<ProductDTO>>(dtoList,HttpStatus.OK);
    }
    @GetMapping(value = "/searchForm")
    public String searchProduct(Model model, @RequestParam("textSearch")String name) {
        int pageSize = 9;
        int pageNo = 1;
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setTextSearch(name);
        List<ProductDTO> listBySearch = iProductService.getProductBySearch(searchDTO.getTextSearch());
        Page<ProductDTO> page = iProductService.findPaginated(pageNo, pageSize); // page chứa số phần tủ , danh sách các phần tủ
        List<ProductDTO> list = page.getContent();
        listBySearch = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        List<CategoryDTO> listCate = iCategoryService.getAllCategory();
        model.addAttribute("listCategory", listCate);
        if (listBySearch.isEmpty()) {
            model.addAttribute("listProduct", list);
        } else {
            model.addAttribute("listProduct", listBySearch);
        }
        return "shop_view";
    }

    @GetMapping(value = "/filterByPrice/{min}/{max}")
    public ResponseEntity<List<ProductDTO>> filterByPrice(@PathVariable("min")String min, @PathVariable("max")String max){
        try{
            List<ProductDTO> entityList = iProductService.searchByPrice(Double.parseDouble(min),Double.parseDouble(max));
            return new ResponseEntity<List<ProductDTO>>(entityList,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<ProductDTO>>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/page/{pageNo}")
    public ResponseEntity<List<ProductDTO>> findPagination(@PathVariable("pageNo")int pageNo){
        int pageSize = 9;
        Page<ProductDTO> page = iProductService.findPaginated(pageNo,pageSize); // page chứa số phần tủ , danh sách các phần tủ
        List<ProductDTO> list = page.getContent();
        return new ResponseEntity<List<ProductDTO>>(list,HttpStatus.OK);
    }
}

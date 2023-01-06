package com.example.shoeshop.Controller;

import com.example.shoeshop.Dto.CategoryDTO;
import com.example.shoeshop.Dto.ProductDTO;
import com.example.shoeshop.Service.ICategoryService;
import com.example.shoeshop.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private IProductService iProductService;

    @Autowired
    private ICategoryService iCategoryService;
    @GetMapping(value = "/admin")
    public String showProduct(Model model){
        List<ProductDTO> productDTOList = iProductService.getAllProduct();
        List<CategoryDTO> list = iCategoryService.getAllCategory();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategoryDTOList(iCategoryService.getAllCategory());
        model.addAttribute("newProductDto",productDTO);
        model.addAttribute("listCategory",list);
        model.addAttribute("listPro",productDTOList);
        return "admin_view";
    }

    @GetMapping(value = "/edit/{id}")
    public String editProduct(Model model, @PathVariable("id")Long id){
        List<CategoryDTO> dtoList = iCategoryService.getAllCategory();
        ProductDTO productDTO = iProductService.getOneProductById(id);
        productDTO.setCategoryDTOList(iCategoryService.getAllCategory());
        model.addAttribute("detailPro",productDTO);
        model.addAttribute("listCate",dtoList);
        return "editProduct_view";

    }

    @PostMapping(value = "/acceptUpdate")
    public String update(@ModelAttribute("detailPro") ProductDTO productDTO,Model model){
        iProductService.editProduct(productDTO);
        List<ProductDTO> dtoList = iProductService.getAllProduct();
        List<CategoryDTO> list = iCategoryService.getAllCategory();
        model.addAttribute("listCategory",list);
        model.addAttribute("listProduct",dtoList);
        return "shop_view";
    }

    @GetMapping(value = "/category")
    public String showCategory(Model model){

        List<CategoryDTO> list = iCategoryService.getAllCategory();
        model.addAttribute("listCate",list);
        return "category_view";
    }

    @GetMapping(value = "/detailCate/{id}")
    public String detailCategrory(Model model, @PathVariable("id")Long id){
        List<Long> listId = iProductService.getListIdProductByIdCate(id);
        List<ProductDTO> dtoList = iProductService.getListProductByListId(listId);
        model.addAttribute("listProductDto",dtoList);
        return "detail_category";
    }

   // @Transactional(readOnly = true)
    @GetMapping(value = "/deleteCate/{id}")
    public String deleteCategory(Model model,@PathVariable("id")Long id){
        iCategoryService.updateDisplayCategory(id);
        return "redirect:/category";
    }
}

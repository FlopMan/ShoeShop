package com.example.shoeshop.Controller;

import com.example.shoeshop.Dto.CategoryDTO;
import com.example.shoeshop.Dto.ProductDTO;
import com.example.shoeshop.Service.ICategoryService;
import com.example.shoeshop.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ICategoryService iCategoryService;
    @Autowired
    private IProductService iProductService;
    @GetMapping(value = "/home")
    public String home_page(Model model){
        List<ProductDTO> dtoList = iProductService.getAllProduct();
        model.addAttribute("listProduct",dtoList);
         return "home_view";
    }

    // post -> body

}

package com.example.shoeshop.Controller;

import com.example.shoeshop.Dto.Item;
import com.example.shoeshop.Dto.ProductDTO;
import com.example.shoeshop.Service.IOrderDetailService;
import com.example.shoeshop.Service.IOrderService;
import com.example.shoeshop.Service.IProductService;
import com.example.shoeshop.entity.Customer;
import com.example.shoeshop.entity.OrderEntity;
import com.example.shoeshop.entity.OrderDetailEntity;
import com.example.shoeshop.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CheckOutController {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private IOrderDetailService iOrderDetailService;
    @Autowired
    private IOrderService iOrderService;

    @GetMapping(value = "/checkout")
    public String checkOutView(Model model){
        Customer customer = new Customer();
        model.addAttribute("newUser",customer);
//        model.put("listItemBuy", iProductService.getAllProduct());
        return "Checkout";
    }

    @RequestMapping(value = "/saveNewUser" , method = RequestMethod.POST)
    public String checkOut(HttpSession session){
        OrderEntity orderEntity = new OrderEntity();
        Long orderId = iOrderService.getOrderID(orderEntity);
        List<Item> cart = (List<Item>) session.getAttribute("cart");
       for(Item o : cart){
           OrderDetailEntity productEntity = new OrderDetailEntity();
           ProductDTO productDTO = iProductService.getOneProductById(o.getProductDTO().getId());
           ProductEntity entity = new ProductEntity();
           entity.setId(productDTO.getId());
           entity.setName(productDTO.getName());
           productEntity.setProductEntityId(entity);
           productEntity.setOrderEntityId(iOrderService.getOrderById(orderId));
           productEntity.setDetail_number(o.getQuantity());
           productEntity.setDetail_total_price((float) (o.getQuantity()*o.getProductDTO().getPrice()));
           iOrderDetailService.add(productEntity);
       }
       session.removeAttribute("cart");
        return "redirect:/shop";
    }

}

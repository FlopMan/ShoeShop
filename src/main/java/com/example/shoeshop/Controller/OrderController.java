//package com.example.shoeshop.Controller;
//
//import com.example.shoeshop.Service.IOrderService;
//import com.example.shoeshop.entity.OrderEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//
//import java.util.List;
//
//@Controller
//public class OrderController {
//    @Autowired
//    private IOrderService iOrderService;
//
//    public String billView(Model model){
//        List<OrderEntity> orderEntityList = iOrderService.getAllBill();
//        model.addAttribute("listOders",orderEntityList);
//        return "billView";
//    }
//}



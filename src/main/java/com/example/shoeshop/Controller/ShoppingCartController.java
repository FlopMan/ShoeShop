package com.example.shoeshop.Controller;

import com.example.shoeshop.Dto.ProductDTO;
import com.example.shoeshop.Service.IProductService;
import com.example.shoeshop.Dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping(value = "/shoppingCart")
public class ShoppingCartController{
    @Autowired
    private IProductService iProductService;


    @RequestMapping(value = "/cart",method = RequestMethod.GET)
    public String viewCart(Model model){
//        model.put("cart",iProductService.getAllProduct());
//        model.addAttribute("cartt",iProductService.getAllProduct());
        return "CartView";
    }
    @RequestMapping(value = "addToCart/{id}", method = RequestMethod.GET)
    public String buyProduct(@PathVariable("id")Long id,Model model,HttpSession session) {

        ProductDTO productDTO = iProductService.getOneProductById(id);
        int amount = (int) (productDTO.getPrice() * 1);
        if (session.getAttribute("cart") == null) {
            List<Item> cart = new ArrayList<>();

            cart.add(new Item(iProductService.getOneProductById(id),1,amount));

            session.setAttribute("cart", cart);
        } else {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            int index = isExist(id, cart);
            if(index == -1){
                cart.add(new Item(iProductService.getOneProductById(id),1,amount));
            }else {
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
                cart.get(index).setAmount((float) (quantity*productDTO.getPrice()));
            }
            session.setAttribute("cart",cart);
        }
        return "redirect:/shop";
    }

    private int isExist(Long id,List<Item> cart){
        for (int i = 0; i < cart.size(); i++){
            if(cart.get(i).getProductDTO().getId() == id){
                return i;
            }
        }
        return -1;
    }

    @RequestMapping(value = "remove/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<Item>> removeProduct(@PathVariable("id") Long id, HttpSession session){
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int index = isExist(id,cart);
        cart.remove(index);
        session.setAttribute("cart",cart);
        return new ResponseEntity<List<Item>>(cart, HttpStatus.OK);
    }
}

package in.sp.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;// Assuming you have a service for bill generation

import in.sp.project.entity.Billing_Product;
import in.sp.project.entity.CardAdd;
import in.sp.project.service.BillService;
import in.sp.project.service.CardService;

import java.util.List;

@Controller
@RequestMapping("/cart") public class CardController {

    @Autowired
    private CardService cartService;

    @Autowired
    private BillService billService;

    // Endpoint to add an item to the cart
    @PostMapping("/add")
    @ResponseBody
    public String addToCart(@RequestParam("productId") Long productId, 
                            @RequestParam("quantity") Integer quantity) {
        cartService.addToCart(productId, quantity);
        return "Item added to cart";
    }

    // Endpoint to retrieve all items in the cart
    @GetMapping("/items")
    @ResponseBody
    public List<CardAdd> getCartItems() {
        return cartService.getAllItems();
    }

    // Endpoint to calculate the total price of items in the cart
    @GetMapping("/total")
    @ResponseBody
    public Double getTotalPrice() {
        return cartService.getTotalPrice();
    }

    @PostMapping("/generate-bill")
    public String generateBill(@RequestParam("customerName") String customerName, Model model) {
        Billing_Product bill = billService.generateBill(customerName);
        model.addAttribute("bill", bill);
        return "bill-summary";  
    }
}

















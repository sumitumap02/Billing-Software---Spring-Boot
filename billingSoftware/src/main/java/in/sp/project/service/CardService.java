package in.sp.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.project.entity.Billing_Product;
import in.sp.project.entity.CardAdd;
import in.sp.project.repository.BillRepository;
import in.sp.project.repository.CardRepository;

@Service
public class CardService {

    @Autowired
    private CardRepository cartRepository;

    @Autowired
    private BillRepository productRepository;

    public void addToCart(Long productId, int quantity) {
        Billing_Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found"));

        double totalPrice = product.getAmount() * quantity;
        CardAdd cartItem = new CardAdd(null, product, quantity, totalPrice);

        cartRepository.save(cartItem);
    }

    public List<CardAdd> getAllItems() {
        return cartRepository.findAll();
    }

    public double getTotalPrice() {
        return cartRepository.findAll().stream().mapToDouble(CardAdd::getTotalPrice).sum();
    }
}

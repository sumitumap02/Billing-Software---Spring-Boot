package in.sp.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import in.sp.project.entity.Billing_Product;
import in.sp.project.entity.CardAdd;
import in.sp.project.repository.BillRepository;

@Service
public class BillService {

	@Autowired
	private  BillRepository billRepos;
	
	@Autowired
	private CardService cardService;
	
	
	public List<Billing_Product> getAllBill(){
		
		return billRepos.findAll();
	}
	
	public void saveBill(Billing_Product bill) {
		billRepos.save(bill);
	}

	public Billing_Product saveBill(String customerName, String item, Double amount, String date) {
	   
	    Billing_Product bill = new Billing_Product();
	    
	  
	    bill.setCustomerName(customerName);
	    bill.setItem(item);
	    bill.setAmount(amount);
	    bill.setDate(date); 

	    return billRepos.save(bill);
	}

	public void updateBill(Long id, String customerName, String item, Double amount, String date) {
	    // Fetch the existing bill by ID
	    Optional<Billing_Product> optionalBill = billRepos.findById(id);

	    // Update the bill if present
	    if (optionalBill.isPresent()) {
	        Billing_Product bill = optionalBill.get();
	        bill.setCustomerName(customerName);
	        bill.setItem(item);
	        bill.setAmount(amount);
	        bill.setDate(date);

	        // Save the updated bill back to the repository
	        billRepos.save(bill);
	    }
	}


	public void deleteById(Long id) {
	
	billRepos.deleteById(id);
	}

	public Billing_Product getBillById(Long id) {
		
		Optional<Billing_Product>billOpt =billRepos.findById(id);
		
		 return billOpt.orElseThrow(() -> new RuntimeException("Bill not found for id: " + id));
		
	}

	public Billing_Product generateBill(String customerName) {
	    // 1. Fetch cart items for the customer
	    List<CardAdd> cartItems = cardService.getAllItems(); // Assuming you have a CartService
	    
	    if (cartItems.isEmpty()) {
	        throw new RuntimeException("No items found in the cart for customer: " + customerName);
	    }

	    // 2. Calculate total amount
	    double totalAmount = 0.0;
	    StringBuilder itemList = new StringBuilder();
	    
	    for (CardAdd item : cartItems) {
	        // Ensure that the methods below exist in CardAdd
	        totalAmount += item.getTotalPrice(); // Assuming getTotalPrice() returns the total price
	        itemList.append(item.getProduct()).append(" (x").append(item.getQuantity()).append("), "); // Assuming getProductName() returns the name of the product
	    }

	    // Remove the last comma and space from the itemList if present
	    if (itemList.length() > 0) {
	        itemList.setLength(itemList.length() - 2); // Remove the last comma and space
	    }

	    // 3. Create a Billing_Product object
	    Billing_Product bill = new Billing_Product();
	    bill.setCustomerName(customerName);
	    bill.setItem(itemList.toString());
	    bill.setAmount(totalAmount);
	    bill.setDate(new Date().toString()); // Optionally, format the date as needed

	    // 4. Save the bill to the repository
	    return billRepos.save(bill);
	}


	
}

























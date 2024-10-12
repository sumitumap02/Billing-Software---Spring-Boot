package in.sp.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CardAdd {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    private Billing_Product product;
	    
	    private int quantity;
	    private double totalPrice;
	    
	    
	    
		public CardAdd(Long id, Billing_Product product, int quantity, double totalPrice) {
			super();
			this.id = id;
			this.product = product;
			this.quantity = quantity;
			this.totalPrice = totalPrice;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Billing_Product getProduct() {
			return product;
		}
		public void setProduct(Billing_Product product) {
			this.product = product;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public double getTotalPrice() {
			return totalPrice;
		}
		public void setTotalPrice(double totalPrice) {
			this.totalPrice = totalPrice;
		}
		@Override
		public String toString() {
			return "CardAdd [id=" + id + ", product=" + product + ", quantity=" + quantity + ", totalPrice="
					+ totalPrice + "]";
		}
	    
	    
	    
	    
}



























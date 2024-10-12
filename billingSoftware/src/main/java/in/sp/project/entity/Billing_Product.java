package in.sp.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="bills")
public class Billing_Product {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@NotBlank(message="Name is required")
	private String customerName;
	private String item;
	private double amount;
	private String date;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Billing_Product [id=" + id + ", customerName=" + customerName + ", item=" + item + ", amount=" + amount
				+ ", date=" + date + "]";
	}
	
	
}

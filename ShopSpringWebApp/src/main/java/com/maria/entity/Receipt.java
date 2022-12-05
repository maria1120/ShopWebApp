package com.maria.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Receipt {
	
	private int customerId;
	private String customerName;
	private String productName;
	private double productPrice;
	private int quantity;
	private double total;
	

}

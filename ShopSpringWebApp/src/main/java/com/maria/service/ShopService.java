package com.maria.service;

import java.util.List;

import com.maria.entity.Customer;
import com.maria.entity.Product;
import com.maria.entity.Receipt;

public interface ShopService {

	Customer loginCheck(int customerId, String password);
	Receipt generateReciept(int customerId,String customerName, Product product, int unit);
	void updateQuantity(int productId,int quantity);
	List<Product> getAllProducts();
	Product findProduct(int id);
	boolean buyProduct(Product product, int units);
	
		
		
	
}

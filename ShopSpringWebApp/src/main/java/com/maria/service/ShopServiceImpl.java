package com.maria.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maria.entity.Customer;
import com.maria.entity.Product;
import com.maria.entity.Receipt;
import com.maria.persistence.CustomerDao;
import com.maria.persistence.ProductDao;

@Service
public class ShopServiceImpl implements ShopService {
	
	@Autowired
	 private CustomerDao customerDao;
	@Autowired
     private ProductDao productDao;

	
	
	@Override
	public Customer loginCheck(int id, String password) {
		Customer customer = customerDao.findByCustomerIdAndCustomerPassword(id, password);
		return customer;
		
	}
	@Override
	public void updateQuantity(int productId, int productDeducted) {
		Product product = productDao.findById(productId).get();
		
		if(product !=null) {
		product.setProductQuantity(product.getProductQuantity()-productDeducted);
		productDao.save(product);
		}
		
	}

	@Override
	public Receipt generateReciept(int customerId,String customerName, Product product, int unit) {
		double total = product.getProductPrice()*unit;
		Receipt receipt = new Receipt(customerId,customerName,product.getProductName(),product.getProductPrice(),unit,total);
		
		return receipt;
	}
	@Override
	public List<Product> getAllProducts() {
		List<Product> myList = productDao.findAll();
		return myList;
	}
	@Override
	public Product findProduct(int id) {
		Product product = productDao.findById(id).get();
		return product;
	}
	@Override
	public boolean buyProduct(Product product, int units) {
		if(product.getProductQuantity()<=units) {
			product.setProductQuantity(product.getProductQuantity()-units);
			productDao.save(product);
			return true;
		}
		else {
		return false;
		}
	}

	

}

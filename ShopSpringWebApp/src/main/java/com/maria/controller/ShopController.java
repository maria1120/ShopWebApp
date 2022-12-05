package com.maria.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.maria.entity.Customer;
import com.maria.entity.Product;
import com.maria.entity.Receipt;
import com.maria.service.ShopServiceImpl;

@Controller
public class ShopController {
	
	@Autowired
	private ShopServiceImpl service;
	ModelAndView mv = new ModelAndView();
	
	
	
	@RequestMapping("/")
	public ModelAndView loginPageController() {
		return new ModelAndView("Login","customer",new Customer());
	}
	
	@RequestMapping("/login")
	public ModelAndView loginController(@RequestParam ("customerId") int customId, @RequestParam ("pass") String customerPassword, HttpSession session) {
		
		Customer customer = service.loginCheck(customId, customerPassword);
		
		if(customer !=null) {
			session.setAttribute("customer", customer);
			List<Product>products = service.getAllProducts();
			mv.addObject("products", products);
			mv.setViewName("index");
		}
		else {
			mv.addObject("message", "Failed to log in. Please try again");
			mv.addObject("customer", new Customer());
			mv.setViewName("Login");
		}
		return mv;
	}
	
	@RequestMapping("/purchise")
	public ModelAndView buyProduct(@RequestParam ("productId")int id, @RequestParam("units") int quantity, HttpSession session) {
	    Customer customer = (Customer)session.getAttribute("customer");
	    Product product = service.findProduct(id);
	    
	    if(service.buyProduct(product, quantity)) {
	    Receipt receipt = service.generateReciept(customer.getCustomerId(), customer.getCustomerName(),product, quantity);
	    
	
		mv.addObject("receipt", receipt);
		mv.setViewName("receipt");
	}
	    else {
	    	mv.addObject("message","Not sufficient quantity in store, please try again later");
	    	mv.setViewName("output");
	    }
	    session.setAttribute("customer", customer);
	    return mv;
	}

}

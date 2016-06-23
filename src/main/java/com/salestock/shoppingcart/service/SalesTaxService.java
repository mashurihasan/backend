package com.salestock.shoppingcart.service;

import com.salestock.shoppingcart.model.ShoppingCart;
import com.salestock.shoppingcart.model.ShoppingCartLineItem;


public interface SalesTaxService {
	 public double calculateSaleTax(ShoppingCartLineItem item) ;
	 public double calculateTotalTaxes(ShoppingCart shoppingCart);
	 public double calculateTotalCost(ShoppingCart shoppingCart);
}

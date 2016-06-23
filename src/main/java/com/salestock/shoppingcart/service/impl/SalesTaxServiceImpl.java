package com.salestock.shoppingcart.service.impl;

import com.salestock.shoppingcart.model.ShoppingCart;
import com.salestock.shoppingcart.model.ShoppingCartLineItem;
import com.salestock.shoppingcart.model.TaxRate;
import com.salestock.shoppingcart.service.SalesTaxService;
import com.salestock.shoppingcart.service.TaxRateService;
import com.salestock.shoppingcart.util.NumberUtils;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class exposes method to find sales tax and total cost of the shopping cart
 * @author ddakshna
 *
 */
@Service
public class SalesTaxServiceImpl implements SalesTaxService {
	Logger logger = LoggerFactory.getLogger(SalesTaxServiceImpl.class);

	TaxRateService taxRateService;

	/**
	 * Calculates total taxes of a shopping cart
	 */
    public double calculateTotalTaxes(ShoppingCart shoppingCart) {
        float totalTax = 0;
        for (ShoppingCartLineItem shoppingCartLineItem : shoppingCart.getLineItems()) {
            totalTax += calculateSaleTax(shoppingCartLineItem);
        }
        return totalTax;
    }

    /**
     * Calculates total cost of a shopping cart
     */
    public double calculateTotalCost(ShoppingCart shoppingCart) {
    	double totalCost = 0;
    	for (ShoppingCartLineItem shoppingCartLineItem : shoppingCart.getLineItems()) {
    		double lineItemCost = calculateTotalCost(shoppingCartLineItem);
    		totalCost+=lineItemCost;
    		shoppingCartLineItem.setTotalCost(NumberUtils.round(lineItemCost));
    	}
    	return totalCost;
    }


    /**
     * Calculates total cost of a line item
     */
    public double calculateTotalCost(ShoppingCartLineItem item) {
        return item.calculateTotalPrice() + calculateSaleTax(item);
    }

    /**
     * Calculates sales tax of a line item
     */
    public double calculateSaleTax(ShoppingCartLineItem item) {
        double taxes = 0;
        double totalPrice = item.calculateTotalPrice();
        Set<TaxRate> taxRates = taxRateService.getTaxRates(item.getProduct().getDescription());
        for (TaxRate taxRate : taxRates) {
            taxes += totalPrice * taxRate.getSalesTaxRate();
        }
        return (float)Math.ceil(taxes / .05f) * .05f;
    }


	public void setTaxRateService(TaxRateService taxRateService) {
		this.taxRateService = taxRateService;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public TaxRateService getTaxRateService() {
		return taxRateService;
	}
	
}

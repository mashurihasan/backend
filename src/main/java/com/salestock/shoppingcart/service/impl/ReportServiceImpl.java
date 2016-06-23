package com.salestock.shoppingcart.service.impl;

import com.salestock.shoppingcart.model.PurchaseReport;
import com.salestock.shoppingcart.model.ShoppingCart;
import com.salestock.shoppingcart.service.ReportService;
import com.salestock.shoppingcart.service.SalesTaxService;
import com.salestock.shoppingcart.service.ShoppingCartService;
import com.salestock.shoppingcart.util.NumberUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
	Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
	
	ShoppingCartService shoppingCartService;

	SalesTaxService salesTaxService;

	public PurchaseReport generatePurchaseReport() {
		PurchaseReport report = new PurchaseReport();
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart();


		double salesTax = salesTaxService.calculateTotalTaxes(shoppingCart);
		double total = salesTaxService.calculateTotalCost(shoppingCart);

		logger.debug("Shopping cart cantains %s items", shoppingCart.getLineItems().size());
		logger.debug("Sales Tax :"+salesTax);
		logger.debug("Total :"+total);

		report.setLineItems(shoppingCart.getLineItems());
		report.setSalesTax(NumberUtils.toBigDecimal(salesTax));
		report.setTotal(NumberUtils.toBigDecimal(total));

		return report;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public ShoppingCartService getShoppingCartService() {
		return shoppingCartService;
	}

	public void setShoppingCartService(ShoppingCartService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}

	public SalesTaxService getSalesTaxService() {
		return salesTaxService;
	}

	public void setSalesTaxService(SalesTaxService salesTaxService) {
		this.salesTaxService = salesTaxService;
	}
}

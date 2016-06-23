package com.salestock.shoppingcart.service;

import com.salestock.shoppingcart.model.TaxRate;

import java.util.Set;


public interface TaxRateService {
	public Set<TaxRate> getTaxRates(String description);
}

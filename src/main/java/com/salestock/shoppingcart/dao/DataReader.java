package com.salestock.shoppingcart.dao;

import com.salestock.shoppingcart.exception.DataReaderException;
import com.salestock.shoppingcart.exception.ResourceNotFoundException;

import java.util.List;

import org.springframework.core.io.Resource;

public interface DataReader {
	public List<String> read(Resource resoure)  throws DataReaderException, ResourceNotFoundException;
}

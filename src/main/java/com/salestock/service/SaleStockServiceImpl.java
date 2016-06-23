package com.salestock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salestock.model.Employee;
import com.salestock.repository.SaleStockRepository;

@Service
public class SaleStockServiceImpl implements SaleStockService {
	
	private SaleStockRepository saleStockRepository;

	public void setsaleStockRepository(SaleStockRepository saleStockRepository) {
		this.saleStockRepository = saleStockRepository;
	}

	@Override
	@Transactional
	public void addEmployee(Employee p) {
		this.saleStockRepository.addEmployee(p);
	}

	@Override
	@Transactional
	public void updateEmployee(Employee p) {
		this.saleStockRepository.updateEmployee(p);
	}

	@Override
	@Transactional
	public List<Employee> listEmployees() {
		return this.saleStockRepository.listEmployees();
	}

	@Override
	@Transactional
	public Employee getEmployeeById(int id) {
		return this.saleStockRepository.getEmployeeById(id);
	}

	@Override
	@Transactional
	public void removeEmployee(int id) {
		this.saleStockRepository.removeEmployee(id);
	}

	public SaleStockRepository getSaleStockRepository() {
		return saleStockRepository;
	}

	public void setSaleStockRepository(SaleStockRepository saleStockRepository) {
		this.saleStockRepository = saleStockRepository;
	}
	
}

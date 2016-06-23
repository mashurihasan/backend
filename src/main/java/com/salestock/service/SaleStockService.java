package com.salestock.service;

import java.util.List;

import com.salestock.model.Employee;

public interface SaleStockService {

	public void addEmployee(Employee p);
	public void updateEmployee(Employee p);
	public List<Employee> listEmployees();
	public Employee getEmployeeById(int id);
	public void removeEmployee(int id);
	
}

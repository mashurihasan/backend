package com.salestock;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.salestock.model.Employee;
import com.salestock.repository.SaleStockRepositoryImpl;
import com.salestock.util.Path;

public class SaleStockControllerTest {

	public static final String SERVER_URI = "http://localhost:8083/SaleStock";
	public static final Logger logger = LoggerFactory.getLogger(SaleStockControllerTest.class);
	
	@Test
	public void getDummyEmployee() {
		logger.info("getDummyEmployee {}{}",SERVER_URI,Path.DUMMY_EMPLOYEE);
		RestTemplate restTemplate = new RestTemplate();
		Employee emp = restTemplate.getForObject(SERVER_URI+Path.DUMMY_EMPLOYEE, Employee.class);
		printEmpData(emp);
	}
	
	@Test
	public void createEmployee() {
		RestTemplate restTemplate = new RestTemplate();
		Employee emp = new Employee();
		emp.setId(1);
		emp.setName("Mashuri Hasan");
		Employee response = restTemplate.postForObject(SERVER_URI+Path.CREATE_EMPLOYEE, emp, Employee.class);
		logger.info("createEmployee {}{}/{}",SERVER_URI,Path.CREATE_EMPLOYEE,emp);
		printEmpData(response);
	}
	
	@Test
	public void getEmployee() {
		logger.info("getEmployee {}{}",SERVER_URI,"/rest/employee/1");
		RestTemplate restTemplate = new RestTemplate();
		Employee emp = restTemplate.getForObject(SERVER_URI+"/rest/employee/1", Employee.class);
		printEmpData(emp);
	}

	@Test
	public void getAllEmployees() {
		logger.info("getAllEmployees {}{}",SERVER_URI,Path.GET_ALL_EMPLOYEE);
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap> emps = restTemplate.getForObject(SERVER_URI+Path.GET_ALL_EMPLOYEE, List.class);
		System.out.println("SIZE : "+emps.size());
		for(LinkedHashMap map : emps){
			System.out.println("ID="+map.get("id")+",Name="+map.get("name")+",Country="+map.get("country"));;
		}
	}

	@Test
	public void listEmployeesDb() {
		logger.info("listEmployeesDb {}{}",SERVER_URI,Path.GET_ALL_EMPLOYEE_FROM_DB);
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap> emps = restTemplate.getForObject(SERVER_URI+Path.GET_ALL_EMPLOYEE_FROM_DB, List.class);
		System.out.println("SIZE : "+emps.size());
		for(LinkedHashMap map : emps){
			System.out.println("ID="+map.get("id")+",Name="+map.get("name")+",Country="+map.get("country"));;
		}
	}
	
	public void printEmpData(Employee emp){
		System.out.println("ID="+emp.getId()+",Name="+emp.getName()+",Country="+emp.getCountry());
	}
}

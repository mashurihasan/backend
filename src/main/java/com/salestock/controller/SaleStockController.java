package com.salestock.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salestock.model.Employee;
import com.salestock.service.SaleStockService;
import com.salestock.util.Path;

@Controller
public class SaleStockController {
	
	private SaleStockService saleStockService;
	
	private static final Logger logger = LoggerFactory.getLogger(SaleStockController.class);
	
	Map<Integer, Employee> empData = new HashMap<Integer, Employee>();
	@RequestMapping(value = Path.DUMMY_EMPLOYEE, method = RequestMethod.GET)
	public @ResponseBody Employee getDummyEmployee() {
		logger.info("Start getDummyEmployee");
		Employee emp = new Employee();
		emp.setId(9999);
		emp.setName("Dummy");
		emp.setCountry("Indonesia");
		empData.put(9999, emp);
		return emp;
	}
	
	@RequestMapping(value = Path.CREATE_EMPLOYEE, method = RequestMethod.POST)
	public @ResponseBody Employee createEmployee(@RequestBody Employee emp) {
		logger.info("Start createEmployee.");
		emp.setCountry("Singapura");
		empData.put(emp.getId(), emp);
		return emp;
	}
	
	@RequestMapping(value = Path.GET_EMPLOYEE, method = RequestMethod.GET)
	public @ResponseBody Employee getEmployee(@PathVariable("id") int empId) {
		logger.info("Start getEmployee. ID="+empId);
		return empData.get(empId);
	}
	
	@RequestMapping(value = Path.GET_ALL_EMPLOYEE, method = RequestMethod.GET)
	public @ResponseBody List<Employee> getAllEmployees() {
		logger.info("Start getAllEmployees.");
		List<Employee> emps = new ArrayList<Employee>();
		Set<Integer> empIdKeys = empData.keySet();
		for(Integer i : empIdKeys){
			emps.add(empData.get(i));
		}
		return emps;
	}
	
	@RequestMapping(value = Path.DELETE_EMPLOYEE, method = RequestMethod.PUT)
	public @ResponseBody Employee deleteEmployee(@PathVariable("id") int empId) {
		logger.info("Start deleteEmployee.");
		Employee emp = empData.get(empId);
		empData.remove(empId);
		return emp;
	}
	
	@RequestMapping(value = Path.GET_ALL_EMPLOYEE_FROM_DB, method = RequestMethod.GET)
	public @ResponseBody List<Employee> listEmployeesDb(Model model) {
		return this.saleStockService.listEmployees();
	}
	
	@Autowired(required=true)
	@Qualifier(value="saleStockService")
	public void setsaleStockService(SaleStockService ps){
		this.saleStockService = ps;
	}
	
	@RequestMapping(value = Path.EMPLOYEE, method = RequestMethod.GET)
	public String listEmployees(Model model) {
		model.addAttribute("employee", new Employee());
		model.addAttribute("listEmployees", this.saleStockService.listEmployees());
		return "employee";
	}
	
	@RequestMapping(value= Path.EMPLOYEE_ADD, method = RequestMethod.POST)
	public String addEmployee(@ModelAttribute("employee") Employee p){
		
		if(p.getId() == 0){
			//new employee, add it
			this.saleStockService.addEmployee(p);
		}else{
			//existing employee, call update
			this.saleStockService.updateEmployee(p);
		}
		
		return "redirect:/employee";
		
	}
	
	@RequestMapping(Path.EMPLOYEE_REMOVE)
    public String removeEmployee(@PathVariable("id") int id){
		
        this.saleStockService.removeEmployee(id);
        return "redirect:/employee";
    }
 
    @RequestMapping(Path.EMPLOYEE_EDIT)
    public String editEmployee(@PathVariable("id") int id, Model model){
        model.addAttribute("employee", this.saleStockService.getEmployeeById(id));
        model.addAttribute("listEmployees", this.saleStockService.listEmployees());
        return "employee";
    }
    
	public SaleStockService getSaleStockService() {
		return saleStockService;
	}

	public void setSaleStockService(SaleStockService saleStockService) {
		this.saleStockService = saleStockService;
	}

	public Map<Integer, Employee> getEmpData() {
		return empData;
	}

	public void setEmpData(Map<Integer, Employee> empData) {
		this.empData = empData;
	}

	public static Logger getLogger() {
		return logger;
	}
    
}

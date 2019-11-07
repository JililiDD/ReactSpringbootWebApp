package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*The Backend Spring Boot REST API is running on 
 * http://localhost:8080, and it is not allowing 
 * requests from other servers - http://localhost:3000, in this example.
 * So we specify the origin (http://localhost:3000)(react app server) 
 * which spring boot rest api would accept the request from.
 */
@CrossOrigin(origins = { "http://localhost:3000" })

/*@RestController : Combination of @Controller and @ResponseBody - Beans 
 * returned are converted to/from JSON/XML.*/
@RestController
class EmployeeController {
	
	private final EmployeeRepository employeeRepository;
	
	EmployeeController(EmployeeRepository repository){
		this.employeeRepository = repository;
	}
	
	// Aggregate root
	
	@GetMapping("/employees")
	List<Employee> all(){return employeeRepository.findAll();}
	
	@PostMapping("/employees")
	Employee newEmployee(@RequestBody Employee newEmployee){
		return employeeRepository.save(newEmployee);
	}
	
	// Single item
	
	@GetMapping("/employees/{id}")
	Employee getOne(@PathVariable Long id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException(id));
	}
	
	@PutMapping("employees/{id}")
	Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		return employeeRepository.findById(id)
				.map(employee -> {
					employee.setName(newEmployee.getName());
					employee.setRole(newEmployee.getRole());
					return employeeRepository.save(employee);
					
				})
				.orElseGet(() -> {
					newEmployee.setId(id);
					return employeeRepository.save(newEmployee);
				});
	}
	
	@DeleteMapping("employees/{id}")
	void deleteEmployee(@PathVariable Long id) {
		employeeRepository.deleteById(id);
	}
}
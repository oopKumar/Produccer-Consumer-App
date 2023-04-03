package com.oop.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oop.bindincg.Employee;
@RestController
@RequestMapping("/employee")
public class EmployeeRestController {
	
	private static Logger logger = LoggerFactory.getLogger(EmployeeRestController.class);
	
	@GetMapping("/data")
	public ResponseEntity<String> showData(){
		logger.info("From Producer..");
		System.out.println("From Producer");
	
		return new ResponseEntity<>("Welcome to APP",HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> saveData(@RequestBody Employee emp){
		logger.info("From Producer..");
		return new ResponseEntity<>("Welcome to App "+emp,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/remove/{id}/{name}")
	public ResponseEntity<String> removeEmp(@PathVariable Integer id,@PathVariable String name){
		logger.info("From Producer..");
		return ResponseEntity.ok("Deleted "+id+"-"+name);
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<String> modifyEmployee(@PathVariable Integer id,@RequestBody Employee employee){
		logger.info("Put Method Called {}"+id+"---"+employee);
		return ResponseEntity.ok(id+"---Data ---"+employee);
	}
	

}
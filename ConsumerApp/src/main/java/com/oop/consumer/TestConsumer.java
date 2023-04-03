package com.oop.consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.oop.binding.Employee;

@Component
public class TestConsumer  implements CommandLineRunner{

	private static Logger logger = LoggerFactory.getLogger(CommandLineRunner.class);
	
	@Autowired
	RestTemplate restTemplate;

	private MediaType MediaType_JSON;
	public void run(String... args) throws Exception {
		//forGetRequest();
		//forPostRequest();
		//deleteHttpMethod(10,"Om Prakash Kumar");	
		
		putHttpMethod(new Employee(101,"Om Prakash Kumar",20003.4) ,10);
	}
	private  void forGetRequest() {

	String url ="http://localhost:7000/employee/data";
	System.out.println("Consumer Start");
	ResponseEntity<String> forEntity = restTemplate.getForEntity(url,String.class);
	
	System.out.println(forEntity.getStatusCode());
	System.out.println(forEntity.getStatusCodeValue());
	System.out.println(forEntity.getStatusCode().name());
	System.out.println(forEntity);
	
	System.out.println("Consumer End");
}

private  void forPostRequest() {
	System.out.println("Consumer Start");

	String url ="http://localhost:7000/employee/save";
	String body ="<Employee><id>101</id>"
	+ "<name>Om Prakash Kumar</name><salary>5000</salary></Employee>";
	
	HttpHeaders httpHeaders = new HttpHeaders();
	httpHeaders.setContentType(MediaType.APPLICATION_XML);
	HttpEntity<String>requestEntity= new HttpEntity<String>(body,httpHeaders);
	ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.POST,requestEntity,String.class);
	
	
	logger.info("Status Code is {}"+response.getStatusCodeValue());
	logger.info("Status Type {}"+response.getStatusCode().name());
	logger.info("Data is"+response.getBody());
	System.out.println("Consumer End");
	System.exit(0);
	}


private void deleteHttpMethod(Integer id,String name) {
	String url ="http://localhost:7000/employee/remove/{id}/{name}";
	
	//restTemplate.delete(url);
	ResponseEntity<String> resp = restTemplate.exchange(
			   url, HttpMethod.POST,null,String.class,id,name);
	logger.info("Status Code {}"+resp.getStatusCode());
	logger.info("Status Type {}"+resp.getStatusCodeValue());
	logger.info("Data is)"+ resp.getBody());
	logger.info("Delete Success");
	System.exit(0);	
}

private void putHttpMethod(Employee emp , Integer id) {
	logger.info("Update Started");
	String url ="http://localhost:7000/employee/edit/{id}";
	HttpHeaders httpHeader = new HttpHeaders();
	httpHeader.setContentType(MediaType_JSON);
	HttpEntity<String> requestEntity = new HttpEntity(emp,httpHeader); 
	
	ResponseEntity<String> resp = restTemplate.exchange(url,HttpMethod.PUT,requestEntity,String.class,id);
	
	logger.info("Data is : {} "+resp.getBody());
	
	logger.info("Status Code Value {}"+resp.getStatusCode());
	logger.info("Status Code {}"+resp.getStatusCodeValue());
	
	logger.info("Update Success");	
	System.exit(0);
}


}

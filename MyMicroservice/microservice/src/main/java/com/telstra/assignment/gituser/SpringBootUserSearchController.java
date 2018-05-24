package com.telstra.assignment.gituser;

import java.util.Collections;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@Component
public class SpringBootUserSearchController {

	private SpringBootUsersService springBootUsersService;

	public SpringBootUserSearchController(SpringBootUsersService springBootUsersService) {
		this.springBootUsersService = springBootUsersService;
	}

	// https://api.github.com/search/users?q=created:2018-05-01

	@RequestMapping(path = "/search", method = RequestMethod.GET)
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.timeout.enabled", value = "false"),
			@HystrixProperty(name = "fallback.enabled", value = "true")}, fallbackMethod = "errorMethod") 
	public SearchResponse searchUser(@RequestParam(value = "createdDate", required = false) String createdDate,
			@RequestParam(value = "location", required = false) String location) {
		SearchResponse sr = new SearchResponse(); 
		System.out.println(createdDate+" "+location);
		try {
			
<<<<<<< HEAD
				 if  (null != createdDate) {
=======
							 if  (null != createdDate) {
>>>>>>> 3dab7fb8b37316c7ce5ce1288b99f40577ca74c6
					  sr = springBootUsersService.getUserCreatedByDate(createdDate);
				} else if (null != location) {
					 sr = springBootUsersService.getUserCreatedByLocation(location);
				}
				
			
			 
			 if (null == createdDate && null == location ) {
			sr.setErrorText("Usage : /search?createdDate=yyyy-MM-dd or /search?location=cityname ");
			sr.setUsers(Collections.emptyList());
			 } 
		
	
		

		
		} catch (Exception e) {
			System.out.println("Exception caught..."+e);
			return sr;
		}
		return sr;
	}

	public SearchResponse errorMethod(String createdDate,String location) {
		System.out.println("errorMethod service  is called!!!");
		SearchResponse errorInfo = new SearchResponse();
		errorInfo.setErrorText("Bad Date formate valide formate is yyyy-MM-dd ");
		return errorInfo;
	}

}

package com.telstra.assignment.gituser;

import java.util.Collections;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class SpringBootUserSearchController {

	private SpringBootUsersService springBootUsersService;

	public SpringBootUserSearchController(SpringBootUsersService springBootUsersService) {
		this.springBootUsersService = springBootUsersService;
	}

	// https://api.github.com/search/users?q=created:2018-05-01

	@RequestMapping(path = "/search", method = RequestMethod.GET)
	public SearchResponse searchUser(@RequestParam(value = "createdDate", required = false) String createdDate,
			@RequestParam(value = "location", required = false) String location) {
		SearchResponse sr = new SearchResponse(); 
		System.out.println(createdDate+" "+location);
		try {
			
				 if  (null != createdDate) {
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



}

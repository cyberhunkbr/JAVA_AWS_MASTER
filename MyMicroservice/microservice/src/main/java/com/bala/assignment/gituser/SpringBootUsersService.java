package com.bala.assignment.gituser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;



@Service
public class SpringBootUsersService {

	private RestTemplate restTemplate;
	

	@Value("${git.base.url}")
	private String gitBaseUrl;

	@Value("${git.user.search}")
	private String gitUserSearchUrl;

	public SpringBootUsersService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * Returns an User profile details from Github profile .
	 *
	 * @return -Users
	 */

	public Users getUserDetails(String name) {
		return restTemplate.getForObject(gitBaseUrl + "/users/" + name, Users.class);// ;
	}

	/*
	 * 
	 * Fetch user by created date
	 * #https://api.github.com/search/users?q=created:2018-05-01
	 * 
	 * 
	 */

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.timeout.enabled", value = "false"),
			@HystrixProperty(name = "fallback.enabled", value = "true")}, fallbackMethod = "badDate") 
	public SearchResponse getUserCreatedByDate(String createdDate) throws Exception{
		SearchResponse searchResponse= new SearchResponse();
		isValidFormat("yyyy-MM-dd",createdDate);
		searchResponse = restTemplate.getForObject(gitUserSearchUrl + "?q=created:" + createdDate,searchResponse.getClass());
		if(null!=searchResponse && !searchResponse.getUsers().isEmpty()) {
		List<Users> sublist = searchResponse.getUsers();
		if(sublist.size() > 4)
		searchResponse.setUsers(sublist.subList(0,3));
		}
		return searchResponse;
	}
	/*
	 * Fetch user according to location
	 * #https://api.github.com/search/users?q=created:2018-05-01+location:london
	 */
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.timeout.enabled", value = "ture"),
			@HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "200"),
			@HystrixProperty(name = "fallback.enabled", value = "true")}, fallbackMethod = "badLocation") 
	public SearchResponse getUserCreatedByLocation(String location) {
		HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(200);
		HystrixCommandProperties.Setter().withMetricsRollingStatisticalWindowInMilliseconds(10);
		System.out.println("getUserCreatedByLocation=" + location);
		SearchResponse searchResponse= new SearchResponse();
		searchResponse = restTemplate.getForObject(gitUserSearchUrl + "?q=location:" + location,searchResponse.getClass());
		if(null!=searchResponse && !searchResponse.getUsers().isEmpty()) {
		List<Users> sublist = searchResponse.getUsers();
		if(sublist.size() > 4)
		searchResponse.setUsers(sublist.subList(0,3));
		}
		return searchResponse;

	}
	
	
	public SearchResponse getUserByParameter(String location , String date) {
		System.out.println("getUserByParameter=" + location);
		SearchResponse searchResponse= new SearchResponse();
		
		System.out.println("URI="+gitUserSearchUrl + "?q=location:" + location +"+created:"+date);
		String uri = gitUserSearchUrl + "?q=location:" + location +"+created:"+date;
		searchResponse = restTemplate.getForObject(uri,searchResponse.getClass());
		if(null!=searchResponse && !searchResponse.getUsers().isEmpty()) {
		List<Users> sublist = searchResponse.getUsers();
		if(sublist.size() > 4)
		searchResponse.setUsers(sublist.subList(0,3));
		}
		return searchResponse;

	}


    public static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return date != null;
    }


	public SearchResponse badDate(String createdDate) {
		System.out.println("badDate fallback  is called!!!");
		SearchResponse errorInfo = new SearchResponse();
		errorInfo.setErrorText("Bad Date formate valide formate is yyyy-MM-dd ");
		return errorInfo;
	}

	public SearchResponse badLocation(String location) {
		System.out.println("badLocation fallback  is called!!!");
		SearchResponse errorInfo = new SearchResponse();
		errorInfo.setErrorText("Please enter valide city name!");
		return errorInfo;
	}

}

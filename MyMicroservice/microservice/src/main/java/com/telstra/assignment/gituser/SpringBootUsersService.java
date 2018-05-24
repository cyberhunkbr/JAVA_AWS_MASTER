package com.telstra.assignment.gituser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



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

	public SearchResponse getUserCreatedByLocation(String location) {
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


	public SearchResponse errorMethod(String createdDate,String location) {
		System.out.println("errorMethod  service is called!!!");
		SearchResponse errorInfo = new SearchResponse();
		errorInfo.setErrorText("Bad Date formate valide formate is yyyy-MM-dd ");
		return errorInfo;
	}


}

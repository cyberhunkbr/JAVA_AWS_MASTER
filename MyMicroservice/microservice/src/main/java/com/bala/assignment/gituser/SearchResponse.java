package com.bala.assignment.gituser;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResponse {
	
	@JsonProperty("items")
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private List<Users> users;
	
	public List<Users> getUsers() {
		return users;
	}
	public void setUsers(List<Users> users) {
		this.users = users;
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	String errorText ;

	public String getErrorText() {
		return errorText;
	}
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
}

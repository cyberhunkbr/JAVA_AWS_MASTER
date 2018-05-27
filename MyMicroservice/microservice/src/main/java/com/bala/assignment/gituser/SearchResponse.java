package com.bala.assignment.gituser;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResponse {
	
	@JsonProperty("items")
	private List<Users> users;
	
	public List<Users> getUsers() {
		return users;
	}
	public void setUsers(List<Users> users) {
		this.users = users;
	}
	
	String errorText ;

	public String getErrorText() {
		return errorText;
	}
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
}

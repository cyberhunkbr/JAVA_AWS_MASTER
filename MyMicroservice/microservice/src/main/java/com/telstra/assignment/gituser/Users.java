package com.telstra.assignment.gituser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Users {


	private String name;
	private String email;
	private String company;
	private String location;
	private String login;
	private String id;
	private String html_url;

	  
	  public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHtml_url() {
		return html_url;
	}

	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}
	  
	  public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Users [name=" + name + ", email=" + email + ", company=" + company + ", location=" + location
				+ ", login=" + login + ", id=" + id + ", html_url=" + html_url + "]";
	}
	
	
	
}

package model;

import java.io.Serializable;

public class Admin implements Serializable
{
	
	private String Admin_id;
	private String username;
	private String password;
	
	public String getAdmin_id() {
		return Admin_id;
	}
	public void setAdmin_id(String admin_id) {
		Admin_id = admin_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}

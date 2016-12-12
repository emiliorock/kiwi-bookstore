package model;

import java.io.*;
import java.util.*;

public class Admin implements Serializable {
	
	private String id;
	private String username;
	private String password;
	private String email;

	public Admin() {

	}

	public Admin(String id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	/*** set method ***/
	public void setID(String id) {
		this.id = id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*** get method ***/
	public String getID() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

}

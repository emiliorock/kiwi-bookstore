package model;

import java.io.*;
import java.util.*;

public class Seller implements Serializable {

	private String id;
	private String username;
	private String password;
	private String email;
	private String address;
	private String ban;

	public Seller() {

	}

	public Seller(String id, String username, String password, String email, String address, String ban) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.ban = ban;
	}

	/*** GET methods ***/
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

	public String getAddress() {
		return address;
	}

	public String getBan() {
		return ban;
	}

	/*** SET methods ***/
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

	public void setAddress(String address) {
		this.address = address;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

}
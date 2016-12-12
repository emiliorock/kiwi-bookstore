package model;

import java.io.*;
import java.util.*;

public class Cart implements Serializable {
	
	private String id;
	private String userID;
	private String seller;
	private String itemID;
	private String itemName;
	private String price;
	private String time;
	
	public Cart() {}
	
	public Cart(String id, String userID, String seller, String itemID, String itemName, String price, String time) {
		this.id = id;
		this.userID = userID;
		this.seller = seller;
		this.itemID = itemID;
		this.itemName = itemName;
		this.price = price;
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getSeller() {
		return seller;
	}
	
	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
package model;

import java.io.*;
import java.util.*;

public class Act implements Serializable {
	
	private String id;
	private String seller;
	private String itemID;
	private String itemName;
	private String price;
	private String act;
	private String time;
	
	public Act() {}
	
	public Act(String id, String seller, String itemID, String itemName, String price, String act, String time) {
		this.id = id;
		this.seller = seller;
		this.itemID = itemID;
		this.itemName = itemName;
		this.price = price;
		this.act = act;
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
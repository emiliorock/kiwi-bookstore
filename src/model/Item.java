package model;

import java.io.*;
import java.util.*;

public class Item implements Serializable {
	
	private String id;
	private String title;
	private String author;
	private String type;
	private String year;
	private String price;
	private String seller;
	private String publisher;
	private String url;
	private String ban;
	
	public Item() {}
	
	public Item(String id, String title, String author, String type, String year, String price, String seller,
			String publisher, String url, String ban) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.type = type;
		this.year = year;
		this.price = price;
		this.seller = seller;
		this.publisher = publisher;
		this.url = url;
		this.ban = ban;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}
	
	
}
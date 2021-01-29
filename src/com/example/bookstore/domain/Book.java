package com.example.bookstore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
	
	@Id //primary key for entity bean Book
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private int id;
	private String isbn;
	private String title;
	private String author;
	private double price;
	
	/*
	 * Hibernate instantiates your objects (instantiate a blank object). So it needs to be able to instantiate
	 * them. If there isn't a no-arg constructor, Hibernate won't know how to
	 * instantiate it, i.e. what argument to pass. 
	 */
	public Book() {
		
	}

	public Book(String isbn, String title, String author, double price) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
	}

	public String toString() {
		return this.title + " by " + this.author;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public String getTitle() {
		return this.title;
	}

	public String getAuthor() {
		return this.author;
	}

	public double getPrice() {
		return this.price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}

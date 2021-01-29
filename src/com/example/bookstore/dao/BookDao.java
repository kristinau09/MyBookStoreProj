package com.example.bookstore.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.bookstore.domain.Book;

@Repository
public interface BookDao {
	
	public List<Book> allBooks();
	public Book findByIsbn(String isbn) throws BookNotFoundException;
	public void create(Book newBook);
	public void delete(Book redundantBook);
	public List<Book> findBooksByAuthor(String author);
	

}

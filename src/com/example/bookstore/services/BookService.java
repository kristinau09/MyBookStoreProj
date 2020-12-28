package com.example.bookstore.services;

import java.util.List;

import com.example.bookstore.domain.Book;

public interface BookService 
{
	   public List<Book> getAllBooksByAuthor(String author);
	   public List<Book> getAllRecommendedBooks(String userId);
	   public Book getBookByIsbn(String isbn);
	   public List<Book> getEntireCatalogue();
	   public void registerNewBook(Book newBook);
}

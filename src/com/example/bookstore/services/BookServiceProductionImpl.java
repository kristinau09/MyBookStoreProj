package com.example.bookstore.services;


import java.util.List;

import com.example.bookstore.dataDao.BookDao;
import com.example.bookstore.dataDao.BookNotFoundException;
import com.example.bookstore.domain.Book;

public class BookServiceProductionImpl implements BookService {
	
	private BookDao bookDao;
	
	//injecting dependency
	public BookServiceProductionImpl(BookDao bookDao) {
		this.bookDao=bookDao;
	}

	@Override
	public List<Book> getAllBooksByAuthor(String author) {
		return bookDao.findBooksByAuthor(author);
	}

	@Override
	public List<Book> getAllRecommendedBooks(String userId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Book getBookByIsbn(String isbn) throws BookNotFoundException {
		return bookDao.findByIsbn(isbn);
	}

	@Override
	public List<Book> getEntireCatalogue() {
		return bookDao.allBooks();
	}

	@Override
	public void registerNewBook(Book newBook) {
		bookDao.create(newBook);

	}

}

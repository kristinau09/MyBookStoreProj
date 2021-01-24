package com.example.bookstore.services;


import java.util.List;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.dao.BookNotFoundException;
import com.example.bookstore.domain.Book;

@Transactional
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

	/* this method will register new book to the database */
	@Override
	public void registerNewBook(Book newBook) {
		bookDao.create(newBook);
		

	}

}

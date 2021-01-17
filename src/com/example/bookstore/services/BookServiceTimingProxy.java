package com.example.bookstore.services;

import java.util.List;

import com.example.bookstore.dataDao.BookDao;
import com.example.bookstore.dataDao.BookNotFoundException;
import com.example.bookstore.domain.Book;

public class BookServiceTimingProxy implements BookService {
	
    private BookService originalBookService;
    
    public void setOriginalBookService(BookService originalBookService) {
		this.originalBookService = originalBookService;
	}
	@Override
	public List<Book> getAllBooksByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getAllRecommendedBooks(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getBookByIsbn(String isbn) throws BookNotFoundException {
		long nanoSecondsInMilliSecond = 1000000;
		//start the clock
		long timeThen = System.nanoTime();
		try {
		Book foundBook = originalBookService.getBookByIsbn(isbn);
		return foundBook;
		}finally {
		//stop the clock
				long timeNow = System.nanoTime();
				//report (actually would be done using a logger)
				long timeTaken = timeNow - timeThen;
				System.out.println("getBookByIsbn took " + timeTaken / nanoSecondsInMilliSecond + " milliseconds");
		}
	}

	@Override
	public List<Book> getEntireCatalogue() {
		long nanoSecondsInMilliSecond = 1000000;
		//start the clock
		long timeThen = System.nanoTime();
		
		List<Book> allBooks = originalBookService.getEntireCatalogue();
		//stop the clock
		long timeNow = System.nanoTime();
		//report (actually would be done using a logger)
		long timeTaken = timeNow - timeThen;
		System.out.println("getEntireCatalogue took " + timeTaken / nanoSecondsInMilliSecond + " milliseconds");
		
		
		return allBooks;
	}

	@Override
	public void registerNewBook(Book newBook) {
		long nanoSecondsInMilliSecond = 1000000;
		//start the clock
		long timeThen = System.nanoTime();
		
		originalBookService.registerNewBook(newBook);
		
		//stop the clock
				long timeNow = System.nanoTime();
				//report (actually would be done using a logger)
				long timeTaken = timeNow - timeThen;
				System.out.println("registerNewBook took " + timeTaken / nanoSecondsInMilliSecond + " milliseconds");

	}

}

package com.example.bookstore.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.bookstore.dao.BookNotFoundException;
import com.example.bookstore.domain.Book;
import com.example.bookstore.services.BookService;
import com.example.bookstore.services.CustomerCreditExceededException;
import com.example.bookstore.services.PurchasingService;

public class Client {
	
	public static void main(String[] args) throws Exception{

		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application-jpa.xml");
		
		try {			
			
			PurchasingService purchasingService = container.getBean(PurchasingService.class);
			BookService bookService = container.getBean("bookService",BookService.class);
						
			//bookService.registerNewBook(new Book("1234567890","Java Programming", "Gary Cornell", 98.42));	
			
			  try { 
				  Book oldBook = bookService.getBookByIsbn("1234567890");
			      bookService.deleteFromStock(oldBook);
			  }catch(BookNotFoundException e) {
				  System.out.println("Book not found");
			  }			 
			
			List<Book> allBooks = bookService.getAllBooksByAuthor("Gary Cornell");
			for(Book book: allBooks) {
				System.out.println(book);
			}
			
		}finally {
			
			container.close();
		}


	}
}

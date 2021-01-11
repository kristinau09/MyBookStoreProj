package com.example.bookstore.client;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.bookstore.domain.Book;
import com.example.bookstore.services.BookService;
import com.example.bookstore.services.PurchasingService;

public class Client {
	public static void main(String[] args) {
		
		System.out.println("Testing buying a book::::::::");
		String requiredIsbn = "ISBN1"; //hard code in mock
		
		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
		//using interface as reference instead of concrete class
		//before adding scope attributes to the xml,all referencing variables 
		BookService bookService = container.getBean("bookService", BookService.class);
		PurchasingService purchasing = container.getBean(PurchasingService.class);
		PurchasingService purchasing1 = container.getBean(PurchasingService.class);
		PurchasingService purchasing2 = container.getBean(PurchasingService.class);
		PurchasingService purchasing3 = container.getBean(PurchasingService.class);
		PurchasingService purchasing4 = container.getBean(PurchasingService.class);
		
		
		purchasing.buyBook(requiredIsbn);
		System.out.println("List of books: ");
		List<Book> bookList = bookService.getEntireCatalogue(); 
		for(Book book: bookList) { 
			System.out.println(book); 
		}
		 	
		
		
		container.close();
	}
}

package com.example.bookstore.client;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.bookstore.domain.Book;
import com.example.bookstore.services.BookService;
import com.example.bookstore.services.PurchasingService;

public class Client {
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
		
		BookService bookService = container.getBean(BookService.class);
		bookService.registerNewBook(new Book("233432432","Sherlock Holmes", "Sir Author Conan Doyle", 120.42));
		List<Book> allBooks = bookService.getEntireCatalogue();
		for(Book nextBook: allBooks) {
			System.out.println(nextBook);
		}
		
		container.close();
		
	}
}

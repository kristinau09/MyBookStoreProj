package com.example.bookstore.client;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.bookstore.dao.BookNotFoundException;
import com.example.bookstore.domain.Book;
import com.example.bookstore.services.BookService;
import com.example.bookstore.services.CustomerCreditExceededException;
import com.example.bookstore.services.PurchasingService;

public class Client {
	public static void main(String[] args) throws Exception{

		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
		try {
			/*
			 * BookService bookService = container.getBean("bookService",BookService.class);
			 * 
			 * bookService.registerNewBook(new Book("233432432","Sherlock Holmes",
			 * "Sir Author Conan Doyle", 120.42));
			 * 
			 * List<Book> allBooks = bookService.getEntireCatalogue(); for(Book nextBook:
			 * allBooks) { System.out.println(nextBook); }
			 * 
			 * try { Book foundBook = bookService.getBookByIsbn("1234");
			 * }catch(BookNotFoundException b) {
			 * System.out.println("Sorry, that Book doesn't exist"); }
			 */
			
			PurchasingService purchasingService = container.getBean(PurchasingService.class);
			BookService bookService = container.getBean("bookService",BookService.class);
			
			/*
			 * since we are calling Book Service method directly from client, bookService
			 * will get its own transaction
			 */
			//begin transaction
			bookService.registerNewBook(new Book("100000999999","Data Structure and Algorithm",
					  "Michael T. Goodrich and Roberto Tamassia", 98.42));
			//commit transaction
			
			//begin
			try {
				
				purchasingService.buyBook("100000999999");
				
			}catch(BookNotFoundException e){
				
				System.out.println("\nSorry, that book doesn't exist");
				
			}catch(CustomerCreditExceededException e) {
				System.out.println("Sorry, Not enough fund to buy this book. Try again!");
			}
			//commit
			
		}finally {
			
			container.close();
		}


	}
}

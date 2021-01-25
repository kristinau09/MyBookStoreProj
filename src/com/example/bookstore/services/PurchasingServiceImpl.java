package com.example.bookstore.services;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.example.bookstore.dao.BookNotFoundException;
import com.example.bookstore.domain.Book;

@Transactional
public class PurchasingServiceImpl implements PurchasingService {
	
	//injecting two objects
	private AccountsService accountService;
	private BookService bookService;
	
	/*
	 * public PurchasingServiceImpl() {
	 * System.out.println("Now creating the purchasing service"); }
	 */
	
	//injecting dependencies through constructor
	public PurchasingServiceImpl(AccountsService accountsService, BookService bookService) {
		this.accountService=accountsService;
		this.bookService=bookService;
		
	}

	
	@Override
	public void buyBook(String isbn) throws BookNotFoundException, CustomerCreditExceededException {
		
		//find the correct book
		Book requireBook = bookService.getBookByIsbn(isbn);

		/* we want to combine following two operations in single transaction */

		//delete the book from stock
		bookService.deleteFromStock(requireBook);

		//raise the invoice (billed to the client)
		try {
			accountService.raiseInvoice(requireBook);
			
		}catch(CustomerCreditExceededException e) {
			
			//tell Spring to rollback --one way of doing this by setting a flag
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

			//throw the exception back to the client
			throw e;
		}
	}

	@Override
	public void setAccountsService(AccountsService accounts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBookService(BookService books) {
		// TODO Auto-generated method stub
		
	}

}

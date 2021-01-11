package com.example.bookstore.services;

import com.example.bookstore.domain.Book;

public class PurchasingServiceImpl implements PurchasingService {
	
	//injecting two objects
	private AccountsService accounts;
	private BookService books;
	
	public PurchasingServiceImpl() {
		System.out.println("Now creating the purchasing service");
	}
	//injecting dependencies through constructor
	public PurchasingServiceImpl(AccountsService accountsService, BookService bookService) {
		this.accounts=accountsService;
		this.books=bookService;
		
	}

	/*
	 * injecting dependencies through setter
	 * @Override public void setAccountsService(AccountsService accounts) {
	 * this.accounts=accounts;
	 * 
	 * }
	 * 
	 * @Override public void setBookService(BookService books) { this.books=books;
	 * 
	 * }
	 */
	@Override
	public void buyBook(String isbn) {
		//find the correct book
		Book requireBook = books.getBookByIsbn(isbn);
		//raise the invoice
		accounts.raiseInvoice(requireBook);

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

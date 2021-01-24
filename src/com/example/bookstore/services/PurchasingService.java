package com.example.bookstore.services;

import com.example.bookstore.dao.BookNotFoundException;

public interface PurchasingService {
	
	public void setAccountsService(AccountsService accounts);
	public void setBookService(BookService books);
	public void buyBook(String isbn) throws BookNotFoundException;

}

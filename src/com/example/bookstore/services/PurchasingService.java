package com.example.bookstore.services;

public interface PurchasingService {
	
	public void setAccountsService(AccountsService accounts);
	public void setBookService(BookService books);
	public void buyBook(String isbn);

}

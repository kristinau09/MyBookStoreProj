package com.example.bookstore.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookstore.dao.BookNotFoundException;
import com.example.bookstore.domain.Book;

@Transactional
@Service("purchasingService")
public class PurchasingServiceImpl implements PurchasingService {
	
	@Autowired//injecting two objects
	private AccountsService accountService;
	@Autowired
	private BookService bookService;
		
	
	@Transactional(rollbackFor = {CustomerCreditExceededException.class, BookNotFoundException.class})
	public void buyBook(String isbn) throws BookNotFoundException, CustomerCreditExceededException {
		
		//find the correct book
		Book requireBook = bookService.getBookByIsbn(isbn);

			//delete the book from stock
		bookService.deleteFromStock(requireBook);

		//raise the invoice (billed to the client)
		accountService.raiseInvoice(requireBook);
		
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

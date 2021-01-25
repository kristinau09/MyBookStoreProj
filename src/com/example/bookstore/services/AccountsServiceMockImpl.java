package com.example.bookstore.services;

import com.example.bookstore.domain.Book;

public class AccountsServiceMockImpl implements AccountsService {

	@Override
	public void raiseInvoice(Book requireBook) throws CustomerCreditExceededException {
		
		System.out.println("Raised an invoice");

		//throw new CustomerCreditExceededException();

	}

}

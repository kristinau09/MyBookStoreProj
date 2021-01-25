package com.example.bookstore.services;

import com.example.bookstore.domain.Book;

public class AccountsServiceMockImpl implements AccountsService {

	@Override
	public void raiseInvoice(Book requireBook) throws CustomerCreditExceededException {

		throw new CustomerCreditExceededException();

	}

}

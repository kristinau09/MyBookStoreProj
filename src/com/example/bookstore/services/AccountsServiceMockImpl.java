package com.example.bookstore.services;

import org.springframework.stereotype.Component;

import com.example.bookstore.domain.Book;

@Component("accountService")
public class AccountsServiceMockImpl implements AccountsService {

	@Override
	public void raiseInvoice(Book requireBook) throws CustomerCreditExceededException {
		
		System.out.println("Raised an invoice");

		//throw new CustomerCreditExceededException();

	}

}

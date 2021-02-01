package com.example.bookstore.domain.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.bookstore.domain.Book;

public class BookTest {

	/* test the book with the same ISBN numbers which are considered the same product */
	@Test
	public void testBooksWithTheSameIsbn() {
		//arrange: where we are going to set up the data
		Book book1 = new Book("7654321","Java Puzzles","Josn Henry", 10.90);
		Book book2 = new Book("7654321","Java Puzzles","Josn Henry", 10.90);

		//act: where we invoke the business logic that we are wanting to test
		boolean areTheyEqual = book1.equals(book2);

		//assert: 
		assertTrue(areTheyEqual);
	}

	@Test
	public void testFormatOfTheToStringIsCorrect() {
		
		//arrange
		Book book1 = new Book("7654321","Java Puzzles","Josh Henry", 10.90);
		
		//act
		String toStringResult = book1.toString();
		
		//assert
		assertEquals("Java Puzzles by Josh Henry", toStringResult);


	}


}

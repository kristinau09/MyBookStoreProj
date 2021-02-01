package com.example.bookstore.integrationTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookstore.dao.BookNotFoundException;
import com.example.bookstore.domain.Book;
import com.example.bookstore.services.BookService;

/**
 * A JUnit4 test to integration test the Spring Container setup
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/application-jpa.xml", "/datasource-test.xml"})
@Transactional
public class ManagingBooksIntegrationTest {
	
	@Autowired
	private BookService bookService;
	
	/* with Before annotation, this code will run first than the others */
	/*
	 * @Before public void setUp() { ClassPathXmlApplicationContext factory = new
	 * ClassPathXmlApplicationContext("application-jpa.xml"); bookService =
	 * factory.getBean(BookService.class); }
	 */
	
	/*
	 * public ManagingBooksIntegrationTest() {
	 * 
	 * System.out.println("------------running constructor--------");
	 * ClassPathXmlApplicationContext factory = new
	 * ClassPathXmlApplicationContext("application-jpa.xml"); bookService =
	 * factory.getBean(BookService.class); }
	 */
	
	@Test
	public void testFindingByIsbn() {
		
		// arrange
		String isbn = "1933988134";
		Book testBook = new Book(isbn, "Spring in Action", "Craig Walls", 23.99);
		bookService.registerNewBook(testBook);

		// act
		Book foundBook = null;
		try {
			foundBook = bookService.getBookByIsbn(isbn);
			assertEquals("The book returned is the wrong one!", testBook, foundBook);
		} catch (BookNotFoundException e) {
			fail("No book was found when one should have been!");
		}
	}

	@Test
	public void testAddingBooks() {
		
		// act
		bookService.registerNewBook(new Book("1933988134", "Spring in Action", "Craig Walls", 23.99));
		bookService.registerNewBook(new Book("0764543857", "Expert One-on-one J2EE Design and Development", "Rod Johnson", 28.59));

		// assert
		int booksInDb = bookService.getEntireCatalogue().size();
		assertEquals("There should be two books in the database!", 2, booksInDb);

	}

	@Test(expected = BookNotFoundException.class)
	public void testFindingNonExistentBook() throws BookNotFoundException {
		Book foundBook = bookService.getBookByIsbn("some garbage isbn");
	}
}
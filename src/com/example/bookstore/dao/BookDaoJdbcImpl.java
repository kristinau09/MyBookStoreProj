package com.example.bookstore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookstore.domain.Book;

@Transactional
@Component("bookDao")
public class BookDaoJdbcImpl implements BookDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String INSERT_BOOK_SQL = "insert into BOOK (ISBN, TITLE, AUTHOR,PRICE) values (?, ?, ?, ?) ";
	private static final String CREATE_TABLE_SQL = "create table BOOK(ISBN VARCHAR(20), TITLE VARCHAR(50), AUTHOR VARCHAR(50), PRICE DOUBLE)";
	private static final String GET_ALL_BOOKS_SQL = "select * from BOOK";

	/*
	 * this method needs to be executed after the dependency injection is done and
	 * must be invoked before the class is put into service.
	 */
	@PostConstruct
	public void createTables() {
		/**
		 * BadSqlGrammarException is only going to work if the table is already existing
		 */

		try {
			jdbcTemplate.update(CREATE_TABLE_SQL);
			
		} catch (BadSqlGrammarException e) {
			System.out.println("Assuming that the table already exists:::");
		}
	}

	@Override
	public List<Book> allBooks() {
		return jdbcTemplate.query(GET_ALL_BOOKS_SQL, new BookMapper());
	}

	@Override
	public Book findByIsbn(String isbn) throws BookNotFoundException {
        //when query for object, you are expecting one and only one result to be returned
		try {
			return jdbcTemplate.queryForObject("SELECT * from book where isbn=?", new BookMapper(), isbn);
			
		}catch (EmptyResultDataAccessException e) {
			throw new BookNotFoundException();
		}
		
	}

	@Override
	public void create(Book newBook) {
		jdbcTemplate.update(INSERT_BOOK_SQL, newBook.getIsbn(), newBook.getTitle(), newBook.getAuthor(),
				newBook.getPrice());

	}

	@Override
	public void delete(Book redundantBook) {
		jdbcTemplate.update("delete from book where isbn=?", redundantBook.getIsbn());

	}

	@Override
	public List<Book> findBooksByAuthor(String author) {
		return jdbcTemplate.query("SELECT * from book where author=?", new BookMapper(), author);
	}

}

class BookMapper implements RowMapper<Book> {
	// ResultSet is what we get back from SQL query
	@Override
	public Book mapRow(ResultSet rs, int rowNumber) throws SQLException {
		String isbn = rs.getString("ISBN");
		String title = rs.getString("TITLE");
		String author = rs.getString("AUTHOR");
		double price = rs.getDouble("PRICE");

		// create a book
		Book book = new Book(isbn, title, author, price);

		return book;
	}

}

package com.example.bookstore.dataDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.bookstore.domain.Book;

public class BookDaoJdbcImpl implements BookDao {

	private JdbcTemplate jdbcTemplate;

	private static final String INSERT_BOOK_SQL = "insert into BOOK (ISBN, TITLE, AUTHOR,PRICE) values (?, ?, ?, ?) ";
	private static final String CREATE_TABLE_SQL = "create table BOOK(ISBN VARCHAR(20), TITLE VARCHAR(50), AUTHOR VARCHAR(50), PRICE DOUBLE)";
	private static final String GET_ALL_BOOKS_SQL = "select * from BOOK";

	//injecting dependency

	public BookDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate; 
		
		try { jdbcTemplate.update(CREATE_TABLE_SQL);
		}catch(Exception e) {
			System.out.println("Assuming that the table already exists:::"); } }


	@Override
	public List<Book> allBooks() {
		return jdbcTemplate.query(GET_ALL_BOOKS_SQL, new BookMapper());
	}

	@Override
	public Book findByIsbn(String isbn) {

		return jdbcTemplate.queryForObject("SELECT * from book where isbn=?", new BookMapper(), isbn);
	}

	@Override
	public void create(Book newBook) {
		jdbcTemplate.update(INSERT_BOOK_SQL, 
				newBook.getIsbn(), newBook.getTitle(),newBook.getAuthor(), newBook.getPrice());

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
class BookMapper implements RowMapper<Book>{
	//ResultSet is what we get back from SQL query
	@Override
	public Book mapRow(ResultSet rs, int rowNumber) throws SQLException {
		String isbn = rs.getString("ISBN");
		String title = rs.getString("TITLE");
		String author = rs.getString("AUTHOR");
		double price = rs.getDouble("PRICE");

		//create a book
		Book book = new Book(isbn, title, author, price);

		return book;
	}

}

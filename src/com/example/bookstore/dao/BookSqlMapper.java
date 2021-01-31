package com.example.bookstore.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.bookstore.domain.Book;

/*
 * contains the SQL statements for myBatis
 */

public interface BookSqlMapper {
	
	/*
	 * Method signature is used by myBatis to convert the rows into objects. 
	 * Here, myBatis will convert these book rows into a list of books
	 */
	
	@Select("select * from book")
    public List<Book> findAllBooksSqlStatement();
	
	@Select("select * from book where author=#{value}")
	public List<Book> findByAuthorSqlStatement(String value);
	
	@Select("select * from book where isbn=#{value}")
	public Book findByIsbnSqlStatement(String value);
	
	@Insert("insert into book(isbn, title, author, price) values( #{isbn}, #{title}, #{author}, #{price})")
	public void createBookSqlStatement(Book book);
	
	@Delete("delete from book where isbn=#{isbn}")
	public void deleteBookSqlStatement(Book book);
	
	@Update("create table BOOK (ISBN varchar(20), title varchar(50), author varchar(50), price double)")	
	public void createTables();
		
	

}

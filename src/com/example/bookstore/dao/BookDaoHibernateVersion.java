package com.example.bookstore.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.example.bookstore.domain.Book;


@Repository
public class BookDaoHibernateVersion implements BookDao {
	
	@Autowired
	private HibernateTemplate template;	
	Session session;
	
	@Override
	public List<Book> allBooks() {
		
		return (List<Book>)template.find("from Book");
		//return template.loadAll(Book.class);
		
		
	}

	@Override
	public Book findByIsbn(String isbn) throws BookNotFoundException {
		
		List<Book> results = (List<Book>) template.find("from Book where isbn=?", isbn);
		if(results.isEmpty()) {
			throw new BookNotFoundException();
		}
		return results.get(0); //return the first element of the result
		
	}

	@Override
	public void create(Book newBook) {
		template.save(newBook);

	}

	@Override
	public void delete(Book redundantBook) {
		Book foundBook = template.get(Book.class, redundantBook.getId());
		template.delete(foundBook);

	}

	@Override
	public List<Book> findBooksByAuthor(String author) {
		return (List<Book>) template.findByNamedParam("from Book where author=:author", "author", author);
	}

}

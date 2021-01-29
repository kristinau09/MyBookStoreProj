package com.example.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.bookstore.domain.Book;

@Repository
public class BookDaoJpaImpl implements BookDao {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> allBooks() {
		
		return em.createQuery("select book from Book as book").getResultList();
	}

	@Override
	public Book findByIsbn(String isbn) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return (Book)em.createQuery("select book from Book as book where book.isbn=:isbn").setParameter("isbn", isbn).getSingleResult();
	}

	@Override
	public void create(Book newBook) {
		System.out.println("Using JPA: ");
		em.persist(newBook);

	}

	@Override
	public void delete(Book redundantBook) {
		
		Book book = em.find(Book.class, redundantBook.getId());
		em.remove(book);

	}

	@Override
	public List<Book> findBooksByAuthor(String author) {
		// TODO Auto-generated method stub
		return em.createQuery("select book from Book as book where book.author=:author").setParameter("author", author).getResultList();
	}

}

package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Book;
import com.example.entity.SubscribedBook;
import com.example.exception.BookException;
import com.example.repository.BookRepository;
import com.example.repository.SubscribedBookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private SubscribedBookRepository subscribedBookRepository;

	public Book getAllBooks(int id) throws BookException {
		try {
			return bookRepository.findById(id).get();
		} catch (Exception e) {
			throw new BookException("Get all Books Details Failed. getAllBooks method term,inated");
		}
	}

	public Book saveBook(Book book) throws BookException {
		try {

			return bookRepository.save(book);
		} catch (Exception e) {
			throw new BookException("Save Books Details Failed. saveBook method term,inated");
		}
	}

	public List<Book> getBook(String category, int author, Double price, String publisher) throws BookException {
		try {
			return bookRepository.findBook(category, author, price, publisher);
		} catch (Exception e) {
			throw new BookException("Get Books Details Failed. getBook method term,inated");
		}
	}

	public List<Book> findBooksByCategory(String category) throws BookException {
		try {
			return bookRepository.findByCategory(category);
		} catch (Exception e) {
			throw new BookException("Find Books by Category Details Failed. findBooksByCategory method term,inated");
		}
	}

	public SubscribedBook buyBook(SubscribedBook subscribedBook) throws BookException {
		try {
			return subscribedBookRepository.save(subscribedBook);
		} catch (Exception e) {
			throw new BookException("Buy Books Details Failed. buyBook method term,inated");
		}
	}

	public List<SubscribedBook> getAllSubcribedBooks() throws BookException {
		try {
			return subscribedBookRepository.findAll();
		} catch (Exception e) {
			throw new BookException("Find Books by Subscription Details Failed. getAllSubcribedBooks method term,inated");
		}
	}

	public List<SubscribedBook> getAllSubcribedBooksByEmailId(String emailId) throws BookException {
		try {
			return subscribedBookRepository.findByReaderEmailId(emailId);
		} catch (Exception e) {
			throw new BookException("Find Books by Subscription and EmailId Details Failed. getAllSubcribedBooksByEmailId method term,inated");
		}
	}

}

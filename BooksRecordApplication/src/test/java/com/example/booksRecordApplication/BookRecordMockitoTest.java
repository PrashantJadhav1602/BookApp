package com.example.booksRecordApplication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.entity.Book;
import com.example.entity.SubscribedBook;
import com.example.exception.BookException;
import com.example.repository.BookRepository;
import com.example.repository.SubscribedBookRepository;
import com.example.service.BookService;

public class BookRecordMockitoTest {

	@Autowired
	private BookService service;
	
	@MockBean
	private BookRepository repo;
	
	@MockBean
	private SubscribedBookRepository subscribedBookRepository;

	@Test
	public void serviceShouldSaveBook() throws BookException {
		Book book = new Book();
		book.setAuthorId(1);
		book.setCategory("History");
		book.setPrice(89.0);
		book.setPublisher("Smart");
		book.setTitle("Life Begins");
		book.setPublishDate(null);
		
		Mockito.when(repo.save(book)).thenReturn(book);
		
		Book savedBook = service.saveBook(book);
		Assertions.assertNotNull(savedBook);
	}

	@Test
	public void serviceShouldBuyBook() throws BookException  {
		SubscribedBook book = new SubscribedBook();
		book.setBookId(1);
		book.setReaderEmailId("test@gmail.com");
		book.setReaderName("prashant");
		book.setSubscribedDateTime(null);
		
		Mockito.when(subscribedBookRepository.save(book)).thenReturn(book);
		
		SubscribedBook savedBook = service.buyBook(book);
		Assertions.assertNotNull(savedBook);
	}
	

}

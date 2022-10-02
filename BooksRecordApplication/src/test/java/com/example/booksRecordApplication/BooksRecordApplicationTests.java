package com.example.booksRecordApplication;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.entity.Book;
import com.example.entity.SubscribedBook;
import com.example.service.BookService;

@SpringBootTest
class BooksRecordApplicationTests {

	@Autowired
	BookService service;
	
	String emailId = "prashant@gmail.com";
	int bookId = 1; 
	int pid = 11;
	String category = "Alpha";
	int author = 1;
	double price = 10;
	String publisher = "My";
	
	@Test
	public void getBooks() throws Exception{
		List<Book> book = service.getBook(category,author,price,publisher);
		Assertions.assertEquals(1,book.get(0).getBookId());
	}
	
	
	@Test
	public void refundBookPossibleOrNot_success() throws Exception{
		SubscribedBook subscribedBook = new SubscribedBook();
		subscribedBook.setReaderEmailId("test@gamil.com");
		subscribedBook.setReaderName("testUser");
		subscribedBook.setBookId(1);
		SubscribedBook book =service.buyBook(subscribedBook);
		
		Assertions.assertEquals(1,subscribedBook.getBookId());
	}
}

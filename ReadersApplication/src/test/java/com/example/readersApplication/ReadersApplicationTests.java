package com.example.readersApplication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.entity.AuthorBook;
import com.example.service.ReaderAppService;

@SpringBootTest
class ReadersApplicationTests {

	@Autowired
	ReaderAppService service;
	
	
	String emailId = "prashant@gmail.com";
	int bookId = 1; 
	int pid = 11;
	
	@Test
	public void readBookByemailIdAndBookId_success() throws Exception{
		AuthorBook book = service.getBookToRead(emailId,bookId);
		Assertions.assertEquals(1,book.getBookId());
	}
	
	
	@Test
	public void refundBookPossibleOrNot_success() throws Exception{
		boolean book =service.getRefundAvailability(emailId,bookId);
		
		Assertions.assertTrue(book);
	}
}

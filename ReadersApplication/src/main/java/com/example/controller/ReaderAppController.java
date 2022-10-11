package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.AuthorBook;
import com.example.entity.ReaderBook;
import com.example.exception.ReaderException;
import com.example.service.ReaderAppService;

@RestController
@RequestMapping("/api/v1/digitalbooks/readers")
public class ReaderAppController {
	
	@Autowired
	private ReaderAppService readerService;
	
	@PostMapping("/subscribe")
	public ReaderBook SubscribeBook(@RequestBody ReaderBook readerBook) throws ReaderException{
//		readerBook.setSubscribedDateTime(LocalDateTime.now());
		return readerService.subscribeBook(readerBook);
	}
	
	@GetMapping("/{emailId}/books") // 3
	public List<AuthorBook> getSubcribedBooksByEmailId(@PathVariable String emailId) throws ReaderException{
		return readerService.getAllSubcribedBooksByEmailId(emailId);
	}
	/*@GetMapping("/{emailId}/books") // 3
	public List<Book> getSubcribedBooksByEmailId(@PathVariable String emailId){
		List<ReaderBook> readerBookList = readerService.getAllSubcribedBooksByEmailId(emailId);
		List<Book> bookList = new ArrayList<>();
		for (ReaderBook readerBook : readerBookList) {
			bookList.add(bookRecordClient.getBooks(readerBook.getBookId()));
			
		}
		return bookList;
	}*/
	
	@GetMapping("/{bookId}")  // 4
	public List<ReaderBook> getReaderBookById(@PathVariable int bookId) throws Exception{
		return readerService.getReaderBook(bookId);
	}
	
	@GetMapping("/{emailId}/books/{bookId}")  // 4
	public AuthorBook getBookToRead(@PathVariable String emailId, @PathVariable int bookId) throws Exception{
		return readerService.getBookToRead(emailId,bookId);
	}
	
	@PostMapping("/{emailId}/books")  //5
	public List<AuthorBook> getBooksBySubscriptioId(@RequestParam int pid, @PathVariable String emailId) throws Exception{
		return readerService.getBookToSubscriptionId(emailId, pid);
	}
	
	@PostMapping("/{emailId}/books/{bookId}/refund")   //6
	public boolean askForRefund(@PathVariable String emailId, 
								@PathVariable int bookId) throws ReaderException{
		
		return readerService.getRefundAvailability(emailId,bookId);
		
	}
	
}

package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Book;
import com.example.entity.SubscribedBook;
import com.example.exception.BookException;
import com.example.service.BookService;

@RestController
@RequestMapping("/api/v1/digitalbooks/books")
public class BookController {

	@Autowired
	private KafkaTemplate<String, Book> kafkaTemplate;

	@Autowired
	private BookService bookService;

	private static final String TOPIC = "kafka-topic";

	@GetMapping("/getBooks") // client
	public Book getAllBooksById(@RequestParam int id) throws BookException {
			kafkaTemplate.send(TOPIC, bookService.getAllBooks(id));
		return bookService.getAllBooks(id);
	}
	@GetMapping("/getBooks/{id}") // client
	public Book getBooksById(@PathVariable int id) throws BookException {
		return bookService.getAllBooks(id);
	}

	@GetMapping("/search") // 1
	public List<Book> getBook(@RequestParam(required = false) String category,
			@RequestParam(required = false) int author, @RequestParam(required = false) Double price,
			@RequestParam(required = false) String publisher) throws BookException {
		return bookService.getBook(category, author, price, publisher);
	}

	/*
	 * @GetMapping("/books/category/{category}") public List<Book>
	 * getBooksByCategory(@PathVariable String category){ return
	 * bookService.findBooksByCategory(category); }
	 */

	// /books/buy
	@PostMapping("/buy") // 2
	public SubscribedBook buyBooks(@RequestBody SubscribedBook subscribedBook) throws BookException {
		subscribedBook.setBookId(subscribedBook.getBookId());
		subscribedBook.setReaderName(subscribedBook.getReaderName());
		subscribedBook.setReaderName(subscribedBook.getReaderEmailId());
		subscribedBook.setSubscribedDateTime(subscribedBook.getSubscribedDateTime());
		return bookService.buyBook(subscribedBook);
	}

}

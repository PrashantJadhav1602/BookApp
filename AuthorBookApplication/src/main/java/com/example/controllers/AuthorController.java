package com.example.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Author;
import com.example.entity.AuthorBook;
import com.example.exception.AuthorException;
import com.example.model.Book;
import com.example.models.JwtRequest;
import com.example.models.JwtResponse;
import com.example.service.AuthorService;
import com.example.service.JwtUserDetailsService;
import com.example.utils.JwtTokenUtil;

@RestController
@RequestMapping("/api/v1/digitalbooks/author")
public class AuthorController {
	
	@Autowired
	private KafkaTemplate<String, Book> kafkaTemplate;

	private static final String TOPIC = "topic-test";
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private JwtUserDetailsService userDetailsService;

	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody JwtRequest req) throws Exception {
		System.out.println("inside login ===============");
//		authenticate(req.getUsername(), req.getPassword());
		System.out.println("after authenticate ===============");
//		final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());
		final UserDetails userDetails = userDetailsService.loadUserByUsernameAndPassword(req);
		System.out.println("after loadUserByUsernameAndPassword ==================================");
		final String token = jwtTokenUtil.generateToken(userDetails);
		int authorId = userDetailsService.getAuthorId(req.getUsername(),req.getPassword());
		return ResponseEntity.ok(new JwtResponse(token,authorId));

	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	@PostMapping("/signup") // 7
	public Author authorSignUp(@RequestBody Author author) throws Exception{
		return authorService.authorSignUp(author);
	}
	
	@PostMapping("/{authorId}/books")  // 9
	public AuthorBook createBook(@RequestBody AuthorBook authorBook) throws AuthorException{
		return authorService.createBook(authorBook);
	}
	
	@PutMapping("/{authorId}/books/{bookId}") //10
	public AuthorBook blockUnblockBook(@PathVariable int authorId, 
										@PathVariable int bookId,
										@RequestBody AuthorBook authorBook) throws AuthorException{
		AuthorBook book = authorService.getAuthorBookByBookId(bookId).get();
	
		System.out.println("Book ===> "+book);
//		int bookId, String title, String category, int authorId, double price, LocalDateTime publishDate,
//		String publisher, boolean block
		AuthorBook updatedBook = authorService.blockUnblockBook(authorId,bookId, authorBook);
		Book newBook = new Book(updatedBook.getBookId(),updatedBook.getTitle(),updatedBook.getCategory(),updatedBook.getAuthorId(),updatedBook.getPrice(),updatedBook.getPublishDate(),updatedBook.getPublisher(),updatedBook.isBlock());
		kafkaTemplate.send(TOPIC,newBook);
		System.out.println("Kafka Post ======================== ");
		return updatedBook;
		
	}
	
	
	@GetMapping("/books/{id}")
	public List<AuthorBook> getBooksByAuthorId(@PathVariable int id){
		return authorService.getBooksByAuthor(id);
	}
}


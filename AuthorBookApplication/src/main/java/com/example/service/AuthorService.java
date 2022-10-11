package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.Author;
import com.example.entity.AuthorBook;
import com.example.exception.AuthorException;
import com.example.repository.AuthorBookRepository;
import com.example.repository.AuthorRepository;

@Service
public class AuthorService {
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	AuthorBookRepository authorBookRepository;
	
	public Author authorSignUp(Author author) throws Exception{
		try {
			Author dbAuthor = authorRepository.findByUsername(author.getUsername());
			if(dbAuthor!=null) {
				throw new AuthorException("Author with userName: "+author.getUsername()+" already exists");
			}
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			String encodedStr = encoder.encode(author.getPassword());
			author.setPassword(encodedStr);
			return authorRepository.save(author);			
		} catch (Exception e) {
			throw new AuthorException("Sign Up operation terminated. authorSignUp Method Failed.");
		}
	}
	
	public AuthorBook createBook(AuthorBook authorBook) throws AuthorException{
		try {
			authorBook.setPublishDate(authorBook.getPublishDate());
			return authorBookRepository.save(authorBook);
		} catch (Exception e) {
			throw new AuthorException("Unable to Create Book. Creation failed. createBook method terminated.");
		}
	}

	public AuthorBook blockUnblockBook(int authorId, int bookId,AuthorBook authorBook) throws AuthorException{
		try {
			AuthorBook updatedBook = authorBookRepository.findByBookIdAndAuthorId(bookId);
			if(updatedBook !=null){
				updatedBook.setBlock(authorBook.isBlock());
				updatedBook.setCategory(authorBook.getCategory());
				updatedBook.setPublisher(authorBook.getPublisher());
				updatedBook.setPrice(authorBook.getPrice());
			}
			return authorBookRepository.save(updatedBook);
		} catch (Exception e) {
			throw new AuthorException("Update Operation Failed. blockUnblockBook method failed");
		}
	}
	public Optional<AuthorBook> getAuthorBookByBookId(int id) {
		return authorBookRepository.findById(id);
	}
	
	public List<AuthorBook>getBooksByAuthor(int id){
		return authorBookRepository.findByAuthorId(id);
	}
}

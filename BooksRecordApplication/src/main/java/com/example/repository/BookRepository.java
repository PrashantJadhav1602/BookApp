package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

	@Query(nativeQuery=false, value=
			"Select b from Book b where b.category = ?1 and b.authorId = ?2 and b.price = ?3 and b.publisher = ?4 and b.block=false")
	List<Book> findBook(String category, int author,Double price, String publisher);
	
	List<Book> findByCategory(String category);

}

package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

	@Query(nativeQuery=false, value=
			"Select b from Book b where b.category = ?1 and b.authorId = ?2 and b.price = ?3 and b.publisher = ?4 and b.block=false")
	List<Book> findBook(String category, int author,Double price, String publisher);
	
	@Query(nativeQuery=false, value=
			"Select b from Book b where b.category = ?1 and b.block=false")
	List<Book> findByCategory(String category);
	
	@Query(nativeQuery=false, value=
			"Select b from Book b where b.authorId = ?1 and b.block=false")
	List<Book> findByAuthor(int authorId);

	@Query(nativeQuery=false, value=
			"Select b from Book b where b.price = ?1 and b.block=false")
	List<Book> findbyPrice(Double price);
	
	@Query(nativeQuery=false, value=
			"Select b from Book b where b.publisher = ?1 and b.block=false")
	List<Book> findbyPublisher(String price);
	
	@Query(nativeQuery=false, value=
			"Select b from Book b where b.category = ?1 and b.authorId = ?2 and b.block=false")
	List<Book> findByCategoryAuthorId(String category,int authorId);
	
	@Query(nativeQuery=false, value=
			"Select b from Book b where b.category = ?1 and b.price =?2 and b.block=false")
	List<Book> findByCategoryPrice(String category, Double price);
	
	@Query(nativeQuery=false, value=
			"Select b from Book b where b.category = ?1 and publisher =?2 and b.block=false")
	List<Book> findByCategory(String category, String publisher);
	
	@Query(nativeQuery=false, value=
			"Select b from Book b where b.authorId = ?1 and b.block=false")
	List<Book> findByAuthorPrice(int authorId, Double price);
	
	@Query(nativeQuery=false, value=
			"Select b from Book b where b.authorId = ?1 and b.publisher = ?2 and b.block=false")
	List<Book> findByAuthorPublisher(int authorId, String publisher);
	
}

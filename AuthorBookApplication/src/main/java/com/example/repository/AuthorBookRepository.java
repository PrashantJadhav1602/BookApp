package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.AuthorBook;


@Repository
public interface AuthorBookRepository extends JpaRepository<AuthorBook, Integer>{

	@Query(nativeQuery = false, value="Select b from AuthorBook b where b.bookId = ?1")
	public AuthorBook findByBookIdAndAuthorId(int bookId);
	
	@Query(nativeQuery = false, value="Select b from AuthorBook b where b.authorId = ?1")
	public List<AuthorBook> findByAuthorId(int id);
}

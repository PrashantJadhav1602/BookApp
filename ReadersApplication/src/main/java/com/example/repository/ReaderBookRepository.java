package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.ReaderBook;


public interface ReaderBookRepository extends JpaRepository<ReaderBook, Integer>{

	@Query(nativeQuery=false, value=
			"Select b from ReaderBook b where b.readerEmailId = ?1")
	public List<ReaderBook> findByReaderEmailId(String emailId);
	
	@Query(nativeQuery=false, value=
			"Select b from ReaderBook b where b.readerEmailId = ?1 and b.bookId = ?2")
	public ReaderBook getValidBookToRead(String emailId, int bookId);
	
	@Query(nativeQuery=false, value=
			"Select b from ReaderBook b where b.readerEmailId = ?1 and b.subscriptionId = ?2")
	public ReaderBook findByReaderBookBySubscriptioId(String emailId, int pid);
	
	@Query(nativeQuery=false, value=
			"Select b from ReaderBook b where b.bookId = ?1")
	public List<ReaderBook> findByReaderBookByBookId(int bookId);
//	
//	@Modifying
//	@Query(nativeQuery=false, value=
//			"delete from ReaderBook b where b.readerEmailId = ?1 and b.bookId = ?2")
//	public long deleteSubscriptionByEmailIdBookId(String emailId, int bookId);

	
}
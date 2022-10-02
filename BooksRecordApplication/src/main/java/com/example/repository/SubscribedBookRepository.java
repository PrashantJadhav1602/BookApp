package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.SubscribedBook;

public interface SubscribedBookRepository extends JpaRepository<SubscribedBook, Integer>{


	public List<SubscribedBook> findByReaderEmailId(String emailId);
}

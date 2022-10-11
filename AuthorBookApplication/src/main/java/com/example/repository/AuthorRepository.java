package com.example.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Author;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{
	
	@Query(nativeQuery = false, value =
			"Select a from Author a where a.username = ?1 and a.password = ?2")
	 Author findByUsernameAndPassword(String username, String password);
	
	@Query(nativeQuery = false, value =
			"Select a from Author a where a.username = ?1")
	 Author findByUsername(String username);
	
}

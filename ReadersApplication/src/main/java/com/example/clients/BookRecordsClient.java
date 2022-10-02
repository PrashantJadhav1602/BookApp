package com.example.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.AuthorBook;


@FeignClient("BOOK-RECORDS")
public interface BookRecordsClient {
	
	@GetMapping("/api/v1/digitalbooks/books/getBooks")
	AuthorBook getBooks(@RequestParam(name = "id") int id);
}

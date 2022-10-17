package com.example.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class AuthorBook {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookId;
	private String title;
	private String category;
	private int authorId;
	private double price;
	private LocalDate publishDate;
	private String publisher;
	private boolean block;
	
	
	public AuthorBook() {
		super();
	}
	
	public AuthorBook(String title, String category, int authorId, double price, LocalDate publishDate, String publisher, boolean block) {
		super();
		this.title = title;
		this.category = category;
		this.authorId = authorId;
		this.price = price;
		this.publishDate = publishDate;
		this.publisher = publisher;
		this.block = block;
	}
	
	public AuthorBook(int bookId, String title, String category, int authorId, double price, LocalDate publishDate,
			String publisher, boolean block) {
		this(title, category, authorId, price, publishDate, publisher, block);
		this.bookId = bookId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public LocalDate getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	

	public boolean isBlock() {
		return block;
	}

	public void setBlock(boolean block) {
		this.block = block;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", category=" + category + ", author=" + authorId
				+ ", price=" + price + ", publishDate=" + publishDate + ", publisher=" + publisher + ", block=" + block +"]";
	}
	

}

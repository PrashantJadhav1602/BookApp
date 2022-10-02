package com.example.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subscribed_book")
public class SubscribedBook {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int subscriptionId;
	private int bookId;
	private String readerName;
	private String readerEmailId;
	private LocalDateTime subscribedDateTime;
	
	
	public SubscribedBook() {
		super();
	}


	public SubscribedBook(int bookId, String readerName, String readerEmailId,LocalDateTime subscribedDateTime ) {
		super();
		this.bookId = bookId;
		this.readerName = readerName;
		this.readerEmailId = readerEmailId;
		this.subscribedDateTime = subscribedDateTime;
	}

	public SubscribedBook(int subscriptionId, int bookId, String readerName, String readerEmailId, LocalDateTime subscribedDateTime ) {
		this(bookId,readerName,readerEmailId,subscribedDateTime);
		this.subscriptionId = subscriptionId;
	}


	public int getSubscriptionId() {
		return subscriptionId;
	}


	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}


	public String getReaderName() {
		return readerName;
	}


	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}


	public String getReaderEmailId() {
		return readerEmailId;
	}


	public void setReaderEmailId(String readerEmailId) {
		this.readerEmailId = readerEmailId;
	}


	public int getBookId() {
		return bookId;
	}


	public void setBookId(int bookId) {
		this.bookId = bookId;
	}


	public LocalDateTime getSubscribedDateTime() {
		return subscribedDateTime;
	}


	public void setSubscribedDateTime(LocalDateTime subscribedDateTime) {
		this.subscribedDateTime = subscribedDateTime;
	}


	@Override
	public String toString() {
		return "SubscribedBook [subscriptionId=" + subscriptionId + ", bookId=" + bookId + ", readerName=" + readerName
				+ ", readerEmailId=" + readerEmailId + ", subscribedDateTime=" + subscribedDateTime +"]";
	}



	

}

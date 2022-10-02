package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime; 

@Entity
@Table(name = "subscribed_book")
public class ReaderBook {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int subscriptionId;
	private int bookId;
	private String readerName;
	private String readerEmailId;
	private LocalDateTime subscribedDateTime;
	private LocalDateTime bookUpdatedOn;
	
	public ReaderBook() {
		super();
	}


	public ReaderBook(int bookId, String readerName, String readerEmailId, LocalDateTime subscribedDateTime, LocalDateTime bookUpdatedOn) {
		super();
		this.bookId = bookId;
		this.readerName = readerName;
		this.readerEmailId = readerEmailId;
		this.subscribedDateTime = subscribedDateTime;
		this.bookUpdatedOn = bookUpdatedOn;
	}

	public ReaderBook(int subscriptionId, int bookId, String readerName, String readerEmailId, LocalDateTime subscribedDateTime, LocalDateTime bookUpdatedOn) {
		this(bookId,readerName,readerEmailId, subscribedDateTime, bookUpdatedOn);
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


	public void setSubscribedDateTime(LocalDateTime localDateTime) {
		this.subscribedDateTime = localDateTime;
	}


	public LocalDateTime getBookUpdatedOn() {
		return bookUpdatedOn;
	}


	public void setBookUpdatedOn(LocalDateTime bookUpdatedOn) {
		this.bookUpdatedOn = bookUpdatedOn;
	}


	@Override
	public String toString() {
		return "SubscribedBook [subscriptionId=" + subscriptionId + ", bookId=" + bookId + ", readerName=" + readerName
				+ ", readerEmailId=" + readerEmailId + ", subscribedDateTime=" + subscribedDateTime + ", bookUpdatedOn=" + bookUpdatedOn +"]";
	}



	

}


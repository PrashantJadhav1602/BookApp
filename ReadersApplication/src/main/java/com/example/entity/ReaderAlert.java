package com.example.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reader_alert")
public class ReaderAlert {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int subscriptionId;
	private String readerName;
	private String redearEmailId;
	private String alertMessage;
	private LocalDateTime dateTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getRedearEmailId() {
		return redearEmailId;
	}
	public void setRedearEmailId(String redearEmailId) {
		this.redearEmailId = redearEmailId;
	}
	public String getAlertMessage() {
		return alertMessage;
	}
	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
	public ReaderAlert(int id, int subscriptionId, String readerName, String redearEmailId, String alertMessage,
			LocalDateTime dateTime) {
		this(subscriptionId,readerName,redearEmailId,alertMessage,dateTime);
		this.id = id;
	}
	public ReaderAlert(int subscriptionId, String readerName, String redearEmailId, String alertMessage,
			LocalDateTime dateTime) {
		super();
		this.subscriptionId = subscriptionId;
		this.readerName = readerName;
		this.redearEmailId = redearEmailId;
		this.alertMessage = alertMessage;
		this.dateTime = dateTime;
	}
	public ReaderAlert() {
		super();
	}
	@Override
	public String toString() {
		return "ReaderAlert [id=" + id + ", subscriptionId=" + subscriptionId + ", readerName=" + readerName
				+ ", redearEmailId=" + redearEmailId + ", alertMessage=" + alertMessage + ", dateTime=" + dateTime
				+ "]";
	}
	
	
	
	
}

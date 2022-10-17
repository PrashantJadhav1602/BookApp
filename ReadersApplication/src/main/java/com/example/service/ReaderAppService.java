package com.example.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.clients.BookRecordsClient;
import com.example.entity.AuthorBook;
import com.example.entity.ReaderAlert;
import com.example.entity.ReaderBook;
import com.example.exception.ReaderException;
import com.example.model.Book;
import com.example.repository.BookAlertRepository;
import com.example.repository.ReaderBookRepository;
import com.example.sendemail.EmailDetails;
import com.example.sendemail.EmailService;

@Service
public class ReaderAppService {

	@Autowired
	private ReaderBookRepository readerBookRepository;

	@Autowired
	private BookAlertRepository bookAlertRepository;

	@Autowired
	private BookRecordsClient bookRecordsClient;
	
	@Autowired 
	private EmailService emailService;

	private static final String TOPIC = "topic-test";

	@KafkaListener(topics = TOPIC, groupId = "group_id", containerFactory = "userKafkaListenerFactory")
	public void consumeJson(Book book) {
		List<ReaderBook> list = readerBookRepository.findByReaderBookByBookId(book.getBookId());
		for (ReaderBook readerBook : list) {
			sendMail(new EmailDetails(readerBook.getReaderEmailId(), "Hi "+readerBook.getReaderName()+
					" your Subscribed Book: "+book.getTitle()+"is updated. Its Block status is: "+book.isBlock()+
					". Thank you", "Subscribed Book Updates"));
			System.out.println("MalSend ==== > readerBook = > " + readerBook);
		}

	}
	
	public String sendMail(EmailDetails details)
    {
        String status= emailService.sendSimpleMail(details);
        System.out.println("status code => "+status);
        return status;
    }

	public List<ReaderBook> getReaderBook(int bookId) {
		return readerBookRepository.findByReaderBookByBookId(bookId);
	}

	public ReaderBook subscribeBook(ReaderBook readerBook) throws ReaderException {
		try {
			return readerBookRepository.save(readerBook);
		} catch (Exception e) {
			throw new ReaderException("UNable to subscribe Book. subscribeBook method failed");
		}
	}

	public List<AuthorBook> getAllSubcribedBooksByEmailId(String emailId) throws ReaderException {
		try {
			List<AuthorBook> bookList = new ArrayList<>();
			List<ReaderBook> readerBookList = readerBookRepository.findByReaderEmailId(emailId);
			for (ReaderBook readerBook : readerBookList) {
				int bookId = readerBook.getBookId();
				AuthorBook book = bookRecordsClient.getBooks(bookId);
				if(book!=null) 
					bookList.add(book);
			}
			return bookList;
		} catch (Exception e) {
			throw new ReaderException("Unable to get Subscribed bOOk using Email Id " + emailId
					+ ". Method getAllSubcribedBooksByEmailId Failed.");
		}
	}

	public AuthorBook getBookToRead(String emailId, int bookId) throws Exception {
		try {
			if (!(readerBookRepository.getValidBookToRead(emailId, bookId) == null)) {
				return bookRecordsClient.getBooks(bookId);

			} else {
				throw new Exception("Invalid Email Id or book Id ");
			}
		} catch (ReaderException re) {
			throw new ReaderException("Book with Book Id " + bookId + " and User " + emailId
					+ " not found to Read. getBookToRead() failed");
		}
	}

	// findByReaderBookBySubscriptioId
	public List<AuthorBook> getBookToSubscriptionId(String emailId, int pid) throws Exception {
		List<AuthorBook> bookList = new ArrayList<>();
		try {
			System.out.println("<===== getBookToSubscriptionId");
			ReaderBook readerBook = readerBookRepository.findByReaderBookBySubscriptioId(emailId, pid);
			if (!(readerBook == null)) {
				bookList.add(bookRecordsClient.getBooks(readerBook.getBookId()));
				return bookList;
			} else {
				throw new ReaderException(
						"Invalid Email Id or Subscription ID .ReaderAppService getBook using pid Failed");
			}
		} catch (ReaderException re) {
			throw new ReaderException("Book with Subscription Id " + pid + " and User " + emailId
					+ " not found. getBookToSubscriptionId() failed");
		}
	}

	// getRefundAvailability
	public boolean getRefundAvailability(String emailId, int bookId) throws ReaderException {
		try {
			ReaderBook readerBook = readerBookRepository.getValidBookToRead(emailId, bookId);
			System.out.println("===> 1");
			if (readerBook == null) {
				return false;
			} else {
				LocalDateTime subscribeDateTime = readerBook.getSubscribedDateTime();
				if (subscribeDateTime == null) {
					return false;
				}

				LocalDateTime toDateTime = LocalDateTime.now();
				long days = subscribeDateTime.until(toDateTime, ChronoUnit.DAYS);
				subscribeDateTime = subscribeDateTime.plusDays(days);
				System.out.println("===> 2");

				if (days > 0) {
					return false;
				} else {
					System.out.println("===> 3");
					ReaderBook uniqueBook= readerBookRepository.getValidBookToRead(emailId, bookId);
					int subscribtionId = uniqueBook.getSubscriptionId();
					System.out.println("===> 4 | subscribtionId = "+subscribtionId);
					readerBookRepository.deleteById(subscribtionId);
					System.out.println("===> 5");
					return true;
				}
			}

		} catch (Exception e) {
			throw new ReaderException("Refund Operation failed for bookId " + bookId + " eamilId = " + emailId
					+ ". getRefundAvailability method failed.");
		}
	}

}

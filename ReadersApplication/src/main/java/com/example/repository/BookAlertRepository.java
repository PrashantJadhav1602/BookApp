	package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.ReaderAlert;


public interface BookAlertRepository extends JpaRepository<ReaderAlert, Integer>{

}

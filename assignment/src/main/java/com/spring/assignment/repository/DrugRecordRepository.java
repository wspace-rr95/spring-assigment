package com.spring.assignment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.assignment.document.DrugRecord;

@Repository
public interface DrugRecordRepository extends MongoRepository<DrugRecord, String> {
	
}
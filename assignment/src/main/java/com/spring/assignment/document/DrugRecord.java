package com.spring.assignment.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "drug_records")
public class DrugRecord {
	@Id
	private String applicationNumber;
	private List<String> substanceName;
	private String sponsorName;
}

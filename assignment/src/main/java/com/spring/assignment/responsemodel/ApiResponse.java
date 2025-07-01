package com.spring.assignment.responsemodel;

import java.util.List;

import com.spring.assignment.document.DrugRecord;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
	private int pageNumber;
	private int pageSize;
	private List<DrugRecord> data;
	private int recordCount;
	private int totalPages;

}

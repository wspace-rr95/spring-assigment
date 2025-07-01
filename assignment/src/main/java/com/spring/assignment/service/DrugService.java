package com.spring.assignment.service;

import com.spring.assignment.document.DrugRecord;
import com.spring.assignment.responsemodel.ApiResponse;

public interface DrugService {

	/**
	 * searchBySubstanceName.
	 * @param substanceName
	 * @param pageNumber
	 * @param recordSize
	 * @return ApiResponse
	 */
	ApiResponse searchBySubstanceName(String substanceName, int pageNumber, int recordSize);

	/**
	 * saveDrugRecord
	 * @param record
	 * @return DrugRecord
	 */
	DrugRecord saveDrugRecord(DrugRecord record);

}

package com.spring.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.assignment.document.DrugRecord;
import com.spring.assignment.responsemodel.ApiResponse;
import com.spring.assignment.service.DrugService;

@RestController
@RequestMapping("/api/drugs")
public class DrugController {
	/**
	 * DrugService
	 */
	@Autowired
	private DrugService drugService;

	/**
	 * search
	 * @param substanceName
	 * @param pageNumber
	 * @param recordSize
	 * @return ApiResponse
	 */
	@GetMapping("/search")
	public ApiResponse search(@RequestParam String substanceName,
			@RequestParam(defaultValue = "1") int pageNumber,
			@RequestParam(defaultValue = "10") int recordSize) {
		return drugService.searchBySubstanceName(substanceName, pageNumber, recordSize);
	}

	/**
	 * save
	 * @param record
	 * @return DrugRecord
	 */
	@PostMapping("/save")
	public DrugRecord saveDrugRecord(@RequestBody DrugRecord record) {
		return drugService.saveDrugRecord(record);
	}
}

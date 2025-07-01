package com.spring.assignment.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spring.assignment.constants.Constants;
import com.spring.assignment.document.DrugRecord;
import com.spring.assignment.repository.DrugRecordRepository;
import com.spring.assignment.responsemodel.ApiResponse;
import com.spring.assignment.service.DrugService;

import lombok.extern.slf4j.Slf4j;

@Service(value = "drugService")
@Slf4j
public class DrugServiceImpl implements DrugService {
	@Autowired
	private DrugRecordRepository repository;

	/**
	 * searchBySubstanceName.
	 * @param substanceName
	 * @param pageNumber
	 * @param recordSize
	 * @return ApiResponse
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ApiResponse searchBySubstanceName(String substanceName, int pageNumber, int recordSize) {
		log.info("Entry::DrugServiceImpl::searchBySubstanceName.");
		List<DrugRecord> finalResult = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();
		String url = String.format(Constants.FDA_BASE_URL, substanceName, Constants.SUBMISSION_STATUS, recordSize);
		ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

		List<Map<String, Object>> results = (List<Map<String, Object>>) response.getBody().get("results");
		Map<String, Object> metaData = (Map<String, Object>) response.getBody().get("meta");
		Map<String, Object> metaDataResults = (Map<String, Object>) metaData.get("results");
		finalResult = results.stream().map(drugRecord -> {
			DrugRecord drugRecordObj = DrugRecord.builder()
					.applicationNumber((String) drugRecord.get("application_number"))
					.substanceName(Arrays.asList(substanceName)).sponsorName((String) drugRecord.get("sponsor_name"))
					.build();
			return drugRecordObj;
		}).collect(Collectors.toList());
		log.info("Leave::DrugServiceImpl::searchBySubstanceName.");
		return getPaginatedResponseList(finalResult, pageNumber, recordSize, metaDataResults);
	}

	/**
	 * getPaginatedResponseList.
	 * @param finalResult
	 * @param pageNumber
	 * @param pageSize
	 * @param metaDataResults
	 * @return ApiResponse
	 */
	private ApiResponse getPaginatedResponseList(List<DrugRecord> finalResult, int pageNumber, int recordSize,
			Map<String, Object> metaDataResults) {
		log.info("Entry::DrugServiceImpl::getPaginatedResponseList.");
		if (finalResult == null || finalResult.isEmpty()) {
			return ApiResponse.builder().pageNumber(pageNumber).pageSize(recordSize).data(new ArrayList<>())
					.recordCount(0).totalPages(0).build();
		}
		int fromIndex = (pageNumber - 1) * recordSize;
		int toIndex = Math.min(fromIndex + recordSize, finalResult.size());
		if (fromIndex >= finalResult.size() || fromIndex < 0) {
			return ApiResponse.builder().pageNumber(pageNumber).pageSize(recordSize).data(new ArrayList<>())
					.recordCount(0).totalPages(0).build();
		}
		int recordCount = 0;
		int totalPageCount = 0;
		if (Objects.nonNull(metaDataResults.get("total").toString())) {
			recordCount = Integer.parseInt(metaDataResults.get("total").toString());
			totalPageCount = (recordCount + recordSize - 1) / recordSize;
		}
		log.info("Leave::DrugServiceImpl::getPaginatedResponseList.");
		return ApiResponse.builder().pageNumber(pageNumber).pageSize(recordSize)
				.data(finalResult.subList(fromIndex, toIndex)).recordCount(recordCount).totalPages(totalPageCount)
				.build();
	}

	/**
	 * saveDrugRecord.
	 * @param drugRecord
	 * @return DrugRecord
	 */
	public DrugRecord saveDrugRecord(DrugRecord drugRecord) {
		log.info("Entry::DrugServiceImpl::saveDrugRecord.");
		DrugRecord savedRecord = null;
		if (Objects.nonNull(drugRecord.getApplicationNumber())) {
			savedRecord = repository.save(drugRecord);
		}
		log.info("Entry::DrugServiceImpl::saveDrugRecord.");
		return savedRecord;
	}

}

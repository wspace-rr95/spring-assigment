package com.spring.assignment.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.spring.assignment.document.DrugRecord;
import com.spring.assignment.responsemodel.ApiResponse;
import com.spring.assignment.service.DrugService;

@SpringBootTest
@ActiveProfiles("local")
public class DrugControllerTest {

	@InjectMocks
	private DrugController drugController;

	@Mock
	private DrugService drugService;

	@SuppressWarnings("static-access")
	@Test
	public void searchSubstanceByName() {
		List<DrugRecord> response = addElements();
		ApiResponse expectedResponse = new ApiResponse().builder().pageNumber(1).pageSize(10)
				.data(response).recordCount(7).totalPages(1).build();
		Mockito.when(drugService.searchBySubstanceName("TICAGRELOR", 1, 10))
				.thenReturn(expectedResponse);
		ApiResponse actualResponse = drugController.search("TICAGRELOR", 1, 10);
		Assertions.assertEquals(expectedResponse, actualResponse);
	}

	private List<DrugRecord> addElements() {
		List<DrugRecord> response = new ArrayList<DrugRecord>();
		DrugRecord drug1 = DrugRecord.builder().applicationNumber("ANDA208508")
				.substanceName(Arrays.asList("TICAGRELOR")).sponsorName("SUNSHINE").build();
		DrugRecord drug2 = DrugRecord.builder().applicationNumber("ANDA208537")
				.substanceName(Arrays.asList("TICAGRELOR")).sponsorName("INVAGEN PHARMS").build();
		DrugRecord drug3 = DrugRecord.builder().applicationNumber("ANDA208541")
				.substanceName(Arrays.asList("TICAGRELOR")).sponsorName("DR REDDYS").build();
		DrugRecord drug4 = DrugRecord.builder().applicationNumber("ANDA208576")
				.substanceName(Arrays.asList("TICAGRELOR")).sponsorName("ALEMBIC").build();
		DrugRecord drug5 = DrugRecord.builder().applicationNumber("ANDA208584")
				.substanceName(Arrays.asList("TICAGRELOR")).sponsorName("APOTEX").build();
		DrugRecord drug6 = DrugRecord.builder().applicationNumber("ANDA208599")
				.substanceName(Arrays.asList("TICAGRELOR")).sponsorName("PRINSTON INC").build();
		DrugRecord drug7 = DrugRecord.builder().applicationNumber("ANDA208596")
				.substanceName(Arrays.asList("TICAGRELOR")).sponsorName("MSN").build();
		response.add(drug1);
		response.add(drug2);
		response.add(drug3);
		response.add(drug4);
		response.add(drug5);
		response.add(drug6);
		response.add(drug7);
		return response;
	}

	@Test
	void testSaveDrugRecordTest() {
		DrugRecord drug = DrugRecord.builder().applicationNumber("ANDA208584")
				.substanceName(Arrays.asList("TICAGRELOR")).sponsorName("APOTEX").build();
		Mockito.when(drugService.saveDrugRecord(drug)).thenReturn(drug);
	    DrugRecord result = drugController.saveDrugRecord(drug);
	    Assertions.assertEquals(drug, result);
	}
}
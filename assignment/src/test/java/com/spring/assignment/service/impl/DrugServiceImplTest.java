package com.spring.assignment.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.assignment.constants.Constants;
import com.spring.assignment.document.DrugRecord;
import com.spring.assignment.responsemodel.ApiResponse;

@SpringBootTest
@ActiveProfiles("local")
public class DrugServiceImplTest {

	@InjectMocks
	private DrugServiceImpl drugService;

	@Mock
	private RestTemplate restTemplate;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	void searchBySubstanceNameTest() throws IOException, URISyntaxException {
		// String substanceName = "ibuprofen"; because ther is no data for this
		// substance it will throw an error
		String substanceName = "TICAGRELOR";
		int pageNumber = 1;
		int recordSize = 10;
		//added the sample json file in src/main/resources/static folder
		File file = new File(getClass().getClassLoader().getResource("static/sample.json").toURI());
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> jsonData = mapper.readValue(file, Map.class);
		ResponseEntity<Map> responseEntity = new ResponseEntity<>(jsonData, HttpStatus.OK);
		String url = String.format(Constants.FDA_BASE_URL, substanceName, Constants.SUBMISSION_STATUS, recordSize);
		Mockito.when(restTemplate.getForEntity(url, Map.class)).thenReturn(responseEntity);
		ApiResponse response = drugService.searchBySubstanceName(substanceName, pageNumber, recordSize);
		Assertions.assertEquals(7, response.getRecordCount());
		Assertions.assertEquals(1, response.getTotalPages());
		Assertions.assertFalse(response.getData().isEmpty());
		Assertions.assertTrue(response.getData().get(0) instanceof DrugRecord);
	}
}

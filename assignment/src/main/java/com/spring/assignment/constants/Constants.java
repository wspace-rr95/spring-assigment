package com.spring.assignment.constants;

public class Constants {

	/**
	 * FDA_BASE_URL
	 */
	public static final String FDA_BASE_URL = "https://api.fda.gov/drug/drugsfda.json?"
			+ "search=openfda.substance_name:%s+AND+submissions.submission_status:%s&limit=%d";

	/**
	 * SUBMISSION_STATUS
	 */
	public static final String SUBMISSION_STATUS = "TA";
}

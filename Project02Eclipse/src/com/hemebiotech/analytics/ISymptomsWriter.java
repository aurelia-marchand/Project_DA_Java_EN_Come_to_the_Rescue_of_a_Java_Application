package com.hemebiotech.analytics;

/**
 * Write a list of symptoms with their occurrences in a file
 * 
 * 
 */
public interface ISymptomsWriter {

	/**
	 * @see WriteDataSymptomsToFile
	 * @see AnalyticsCounter
	 * 
	 * @throws Exception
	 */
	void writeSymptoms() throws Exception;

}

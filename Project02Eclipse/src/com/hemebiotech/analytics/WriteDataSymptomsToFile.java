package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;

/**
 * Generate a new text file named results.out which lists each symptom in
 * alphabetical order, followed by the number of occurrences in the file.
 * 
 * @see ISymptomsWriter
 *
 */
public class WriteDataSymptomsToFile implements ISymptomsWriter {

	/**
	 * The container that stores our pairs (key = symptom / value = number of
	 * occurrence)
	 */
	private TreeMap<String, Integer> sortedList;

	/**
	 * The output file path
	 */
	private String fileOut;

	/**
	 * Class constructor
	 * 
	 * @param sortedList Container symptoms/occurrences
	 * @param fileOut    output file path
	 */
	public WriteDataSymptomsToFile(TreeMap<String, Integer> sortedList, String fileOut) {
		this.sortedList = sortedList;
		this.fileOut = fileOut;
	}

	/**
	 * @throws IOException can throw an exception FileNotFoundException
	 * 
	 * @see IWriteDataSymptomsToFile
	 */
	@Override
	public void writeSymptoms() throws IOException {

		try (FileWriter writer = new FileWriter(fileOut)) {

			/*
			 * Get keys with keySet () method, return list of keys. By
			 * browsing our TreeMap, we retrieve the corresponding values. We write our
			 * result in the output file with the write () method
			 */
			for (String key : sortedList.keySet()) {
				writer.write(key + " : " + sortedList.get(key) + "\n");
			}

		}

	}

}

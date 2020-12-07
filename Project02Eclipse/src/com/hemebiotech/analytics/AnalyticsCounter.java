package com.hemebiotech.analytics;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * This class counts the number of occurrences of symptoms and returns a list
 * sorted alphabetically.
 * 
 * @see ReadSymptomDataFromFile
 * @see ISymptomReader
 * @see WriteDataSymptomsToFile
 * @see ISymptomsWriter
 */
public class AnalyticsCounter {

	public static void main(String args[]) {

		/**
		 * The path of the text file containing the symptoms.
		 */
		String fileIn = "symptoms.txt";

		/**
		 * The path of the output text file that will contain our symptoms and their
		 * occurrences
		 */
		String fileOut = "result.out";

		/*
		 * Creation of instances of read and write classes via their interfaces Using
		 * data recovery and write methods
		 * 
		 * These class calls can report errors, we manage here the exception management
		 * responsibility
		 * 
		 */
		try {
			/*
			 * We send our file path via the constructor of the class. We call the
			 * getSymptoms () method, the results are placed in a list of String.
			 */
			ISymptomReader extractionSymptoms = new ReadSymptomDataFromFile(fileIn);
			List<String> completeList = extractionSymptoms.getSymptoms();

			// Counter
			/*
			 * Object HashSet : list without duplicate
			 * 
			 */
			Set<String> setList = new HashSet<>(completeList);

			/*
			 * Object TreeMap : hashSet / key and occurrences / value.
			 * 
			 * Provided natural order, here alphabetical
			 */
			TreeMap<String, Integer> mapList = new TreeMap<>();
			for (String s : setList) {
				mapList.put(s, Collections.frequency(completeList, s));
			}

			try {
				/*
				 * Sending the TreeMap and output file path as parameter
				 */
				ISymptomsWriter sortedSymptoms = new WriteDataSymptomsToFile(mapList, fileOut);
				sortedSymptoms.writeSymptoms();
			} catch (Exception i) {
				i.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

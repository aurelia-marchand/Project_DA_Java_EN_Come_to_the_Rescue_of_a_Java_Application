package com.hemebiotech.analytics;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
		 * Cr�ation des instances des classes de lecture et d'�criture via leurs
		 * interfaces Utilisation des m�thodes de r�cup�ration des donn�es et d'�criture
		 * 
		 * Ces appels de classes pouvant remonter des erreurs, on g�re ici la
		 * responsabili� de gestion des exceptions
		 * 
		 */
		try {
			/*
			 * On envoi notre chemin de fichier via le constructeur de la classe On appel la
			 * m�thode getSymptoms(), les r�sultats sont plac�s dans une liste de String.
			 */
			ISymptomReader extractionSymptoms = new ReadSymptomDataFromFile(fileIn);
			List<String> completeList = extractionSymptoms.getSymptoms();

			// Counter
			/*
			 * Object HashSet : list without duplicate
			 * 
			 */
			Set<String> setList = new HashSet<String>(completeList);

			/*
			 * Object HashMap : hashSet / key and occurrences / value
			 */
			Map<String, Integer> mapList = new HashMap<String, Integer>();
			for (String s : setList) {
				mapList.put(s, Collections.frequency(completeList, s));
			}

			/*
			 * Object TreeMap : provided natural order, here alphabetical
			 */
			TreeMap<String, Integer> sortedList = new TreeMap<>(mapList);

			try {
				/*
				 * Sending the TreeMap and output file path as parameter
				 */
				ISymptomsWriter sortedSymptoms = new WriteDataSymptomsToFile(sortedList, fileOut);
				sortedSymptoms.writeSymptoms();
			} catch (Exception i) {
				// To do try to put exception in log file
				System.out.println(i + " Writing problem, origin WriteDataSymptomFromFile");
			}

		} catch (Exception e) {

			// To do try to put exception in log file
			System.out.println(e + " Reading problem, origin ReadSymptomsDataToFile");
		}

	}

}


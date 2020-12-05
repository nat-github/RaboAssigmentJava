package com.rabo.assignment;


import com.rabo.assignment.exception.FileContentNotValidException;
import com.rabo.assignment.service.IRecordService;
import com.rabo.assignment.service.RecordService;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class RaboAssignmentApp {


	public static void main(String[] args) {
		RaboAssignmentApp assignmentApp = new RaboAssignmentApp();
		assignmentApp.processRecords();
	}

	private void processRecords() {


		IRecordService recordService = new RecordService();
		try {

			String csvFileName = "records.csv";
			File csvFile = new File(getClass().getClassLoader().getResource(csvFileName).toURI());
            List<String> csvFailedRecords = recordService.processNdGenerateReport(csvFile);
			writeErrorLogs(csvFailedRecords,csvFile.getName());

			String xmlFileName = "records.xml";
			File xmlFile = new File(getClass().getClassLoader().getResource(xmlFileName).toURI());
			List<String> xmlFailedRecords = recordService.processNdGenerateReport(xmlFile);
			writeErrorLogs(xmlFailedRecords,xmlFile.getName());

		} catch (FileContentNotValidException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void writeErrorLogs(List<String> csvFailedRecords,String fileName) throws IOException {
		FileWriter fileWriter = new FileWriter("Failed_" + fileName.replace(".","_") + ".log");
		for (String failedRecord: csvFailedRecords) {
			fileWriter.write(failedRecord+"\n");
		}
			fileWriter.close();
	}
}

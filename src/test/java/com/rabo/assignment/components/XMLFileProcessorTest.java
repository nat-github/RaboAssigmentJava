/**
 * 
 */
package com.rabo.assignment.components;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.rabo.assignment.exception.FileContentNotValidException;
import com.rabo.assignment.model.Record;

/**
 * Testing the methods in CSV File Processor
 *
 */
public class XMLFileProcessorTest {

	XMLFileProcessor xmlFileProcessor = new XMLFileProcessor();

	@Test
	public void testProcess() throws IOException, FileContentNotValidException, URISyntaxException {
		// Test input data
		File file = new File(getClass().getClassLoader().getResource("records.xml").toURI());

		List<Record> records = xmlFileProcessor.process(file);

		assertThat(records).isNotNull();
		assertThat(records.size()).isEqualTo(10);
	}

	@Test(expected = FileContentNotValidException.class)
	public void testProcessWithEmptyFile() throws IOException, FileContentNotValidException, URISyntaxException {
		// Test input data
		File file = new File(getClass().getClassLoader().getResource("empty.xml").toURI());

		xmlFileProcessor.process(file);
	}

	@Test(expected = FileContentNotValidException.class)
	public void testProcessWithWrongFile() throws IOException, FileContentNotValidException, URISyntaxException {
		// Test input data
		File file = new File(getClass().getClassLoader().getResource("records_wit_error.xml").toURI());

		xmlFileProcessor.process(file);
	}
}

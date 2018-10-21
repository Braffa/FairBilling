package com.braffa.bt.fair.billing;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;

public class TestReadAndValidateLogFile extends TestCase {

	ReadAndValidateLogFile readAndValidateLogFile;

	final String userDir = System.getProperty("user.dir");

	@BeforeClass
	public void setUp() throws Exception {
		readAndValidateLogFile = new ReadAndValidateLogFile();
	}

	@Test
	public void testReadFileNotFound() {
		try {
			readAndValidateLogFile.readFile(userDir + "/src/test/resources/fairbilling.log");
		} catch (FileNotFoundException e) {
			assertTrue("file not found", e instanceof FileNotFoundException);
		}
	}

	@Test
	public void testReadFileForEariestRecord() {
		try {
			readAndValidateLogFile.readFile(userDir + "/src/test/resources/fairbilling.log");
			readAndValidateLogFile.getlogRecordMap();
			assertEquals("The earlest record should be 14:02:03 ", "14:02:03",
					readAndValidateLogFile.getTimeOfFirstRecord());
		} catch (Exception e) {
			assertFalse("file not found", e instanceof FileNotFoundException);
		}
	}

	@Test
	public void testReadFileForLatestRecord() {
		try {
			readAndValidateLogFile.readFile(userDir + "/src/test/resources/fairbilling.log");
			readAndValidateLogFile.getlogRecordMap();
			assertEquals("The latest record should be 14:04:41 ", "14:04:41",
					readAndValidateLogFile.getTimeOfLastRecord());
		} catch (Exception e) {
			assertFalse("file not found", e instanceof FileNotFoundException);
		}
	}

	@Test
	public void testReadFileForCorrectEntriesInMap() {
		try {
			readAndValidateLogFile.readFile(userDir + "/src/test/resources/fairbilling.log");
			Map<String, ArrayList<LogRecord>> logRecordMap = readAndValidateLogFile.getlogRecordMap();
			assertEquals("The map of log records should have 2 entries ", 2, logRecordMap.size());

		} catch (Exception e) {
			assertFalse("file not found", e instanceof FileNotFoundException);
		}
	}

	@Test
	public void testReadFileCheckContentsOfLogRecordsForALICE99() {
		try {
			readAndValidateLogFile.readFile(userDir + "/src/test/resources/fairbilling.log");
			Map<String, ArrayList<LogRecord>> logRecordMap = readAndValidateLogFile.getlogRecordMap();

			List<LogRecord> lOfLogRecords = logRecordMap.get("ALICE99");
			assertEquals("The number of Log records for user ALICE99 should be 7  ", 7, lOfLogRecords.size());

			LogRecord logRecord = lOfLogRecords.get(0);
			assertEquals("Expected 14:02:03 ", "14:02:03", logRecord.getTime());
			assertEquals("Expected ALICE99 ", "ALICE99", logRecord.getName());
			assertEquals("Expected a Start label ", "Start", logRecord.getLabel());

			logRecord = lOfLogRecords.get(1);
			assertEquals("Expected 14:02:34", "14:02:34", logRecord.getTime());
			assertEquals("Expected ALICE99 ", "ALICE99", logRecord.getName());
			assertEquals("Expected an End label ", "End", logRecord.getLabel());

			logRecord = lOfLogRecords.get(2);
			assertEquals("Expected 14:02:58", "14:02:58", logRecord.getTime());
			assertEquals("Expected ALICE99 ", "ALICE99", logRecord.getName());
			assertEquals("Expected an Start label ", "Start", logRecord.getLabel());

			logRecord = lOfLogRecords.get(3);
			assertEquals("Expected 14:03:33", "14:03:33", logRecord.getTime());
			assertEquals("Expected ALICE99 ", "ALICE99", logRecord.getName());
			assertEquals("Expected an Start label ", "Start", logRecord.getLabel());

			logRecord = lOfLogRecords.get(4);
			assertEquals("Expected 14:03:35", "14:03:35", logRecord.getTime());
			assertEquals("Expected ALICE99 ", "ALICE99", logRecord.getName());
			assertEquals("Expected an End label ", "End", logRecord.getLabel());

			logRecord = lOfLogRecords.get(5);
			assertEquals("Expected 14:04:05", "14:04:05", logRecord.getTime());
			assertEquals("Expected ALICE99 ", "ALICE99", logRecord.getName());
			assertEquals("Expected an End label ", "End", logRecord.getLabel());

			logRecord = lOfLogRecords.get(6);
			assertEquals("Expected 14:04:23", "14:04:23", logRecord.getTime());
			assertEquals("Expected ALICE99 ", "ALICE99", logRecord.getName());
			assertEquals("Expected an End label ", "End", logRecord.getLabel());

		} catch (Exception e) {
			assertFalse("file not found", e instanceof FileNotFoundException);
		}
	}

	@Test
	public void testReadFileCheckContentsOfLogRecordsForCHARLIE() {
		try {
			readAndValidateLogFile.readFile(userDir + "/src/test/resources/fairbilling.log");
			Map<String, ArrayList<LogRecord>> logRecordMap = readAndValidateLogFile.getlogRecordMap();

			List<LogRecord> lOfLogRecords = logRecordMap.get("CHARLIE");
			
			assertEquals("The number of Log records for user CHARLIE should be 4  ", 4, lOfLogRecords.size());

			LogRecord logRecord = lOfLogRecords.get(0);
			assertEquals("Expected 14:02:05 ", "14:02:05", logRecord.getTime());
			assertEquals("Expected CHARLIE ", "CHARLIE", logRecord.getName());
			assertEquals("Expected a End label ", "End", logRecord.getLabel());
			
			logRecord = lOfLogRecords.get(1);
			assertEquals("Expected 14:03:02 ", "14:03:02", logRecord.getTime());
			assertEquals("Expected CHARLIE ", "CHARLIE", logRecord.getName());
			assertEquals("Expected a Start label ", "Start", logRecord.getLabel());
			
			logRecord = lOfLogRecords.get(2);
			assertEquals("Expected 14:03:37 ", "14:03:37", logRecord.getTime());
			assertEquals("Expected CHARLIE ", "CHARLIE", logRecord.getName());
			assertEquals("Expected a End label ", "End", logRecord.getLabel());
			
			logRecord = lOfLogRecords.get(3);
			assertEquals("Expected 14:04:41", "14:04:41", logRecord.getTime());
			assertEquals("Expected CHARLIE ", "CHARLIE", logRecord.getName());
			assertEquals("Expected a Start label ", "Start", logRecord.getLabel());

		} catch (Exception e) {
			assertFalse("file not found", e instanceof FileNotFoundException);
		}
	}

}

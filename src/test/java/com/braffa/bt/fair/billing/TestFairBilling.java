package com.braffa.bt.fair.billing;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class TestFairBilling {

	FairBilling fairBilling;

	final String userDir = System.getProperty("user.dir");

	@Before
	public void setUp() throws Exception {
		fairBilling = new FairBilling();
	}

	@Test
	public void testFairBillingForEariestRecord() {
		try {
			fairBilling.calculateBills(userDir + "/src/test/resources/fairbilling.log");
			assertEquals("The earlest record should be 14:02:03 ", "14:02:03", fairBilling.getTimeOfFirstRecord());
		} catch (Exception e) {
			assertFalse("file not found", e instanceof FileNotFoundException);
		}
	}

	@Test
	public void testFairBillingForLatestRecord() {
		try {
			fairBilling.calculateBills(userDir + "/src/test/resources/fairbilling.log");
			assertEquals("The latest record should be 14:04:41 ", "14:04:41", fairBilling.getTimeOfLastRecord());
		} catch (Exception e) {
			assertFalse("file not found", e instanceof FileNotFoundException);
		}
	}

	@Test
	public void testFairBillingForCorrectEntriesInMap() {
		try {
			fairBilling.calculateBills(userDir + "/src/test/resources/fairbilling.log");
			Map<String, ArrayList<LogRecord>> logRecordMap = fairBilling.getlogRecordMap();
			assertEquals("The map of log records should have 2 entries ", 2, logRecordMap.size());
		} catch (Exception e) {
			assertFalse("file not found", e instanceof FileNotFoundException);
		}
	}

	@Test
	public void testReadFileCheckContentsOfLogRecordsForALICE99() {
		try {
			fairBilling.calculateBills(userDir + "/src/test/resources/fairbilling.log");
			Map<String, ArrayList<LogRecord>> logRecordMap = fairBilling.getlogRecordMap();

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
			fairBilling.calculateBills(userDir + "/src/test/resources/fairbilling.log");
			Map<String, ArrayList<LogRecord>> logRecordMap = fairBilling.getlogRecordMap();

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

	@Test
	public void testFairBillingCheckNumberOfBillRecords() {
		try {
			fairBilling.calculateBills(userDir + "/src/test/resources/fairbilling.log");
			ArrayList<BillRecord> lOfBillRecords = fairBilling.getlOfBillRecords();
			assertEquals("Incorrect number of bill records ", 2, lOfBillRecords.size());
		} catch (Exception e) {
			assertFalse("file not found", e instanceof FileNotFoundException);
		}
	}
	
	@Test
	public void testFairBillingUserALICE99BillRecords() {
		try {
			fairBilling.calculateBills(userDir + "/src/test/resources/fairbilling.log");

			ArrayList<BillRecord> lOfBillRecords = fairBilling.getlOfBillRecords();
			BillRecord billRecord = lOfBillRecords.get(0);
			assertEquals("User name should be ALICE99 ", "ALICE99", billRecord.getName());
			assertEquals("Number of sessions should be 4 ", 4, billRecord.getNoOfSessions());
			assertEquals("Total time usage should be 240 seconds", 240, billRecord.getTotalTime());
			
		} catch (Exception e) {
			assertFalse("file not found", e instanceof FileNotFoundException);
		}
	}
	
	@Test
	public void testFairBillingUserCHARLIEBillRecords() {
		try {
			fairBilling.calculateBills(userDir + "/src/test/resources/fairbilling.log");

			ArrayList<BillRecord> lOfBillRecords = fairBilling.getlOfBillRecords();
			BillRecord billRecord = lOfBillRecords.get(1);
			assertEquals("User name should be CHARLIE ", "CHARLIE", billRecord.getName());
			assertEquals("Number of sessions shpuld be 3 ", 3, billRecord.getNoOfSessions());
			assertEquals("Total time usage should be 37 seconds", 37, billRecord.getTotalTime());

		} catch (Exception e) {
			assertFalse("file not found", e instanceof FileNotFoundException);
		}
	}

	@Test
	public void testFairBillingUserALICE99Bill() {
		try {
			fairBilling.calculateBills(userDir + "/src/test/resources/fairbilling2.log");

			assertEquals("earliest record should be 14:02:03 ", "14:02:03", fairBilling.getTimeOfFirstRecord());
			assertEquals("latest record 14:07:41 ", "14:07:41", fairBilling.getTimeOfLastRecord());
			assertEquals("Size of map of log records should be 1 ", 1, fairBilling.getlogRecordMap().size());

			Map<String, ArrayList<LogRecord>> logRecordMap = fairBilling.getlogRecordMap();
			List<LogRecord> lOfLogRecords = logRecordMap.get("ALICE99");
			assertEquals("User ALICE99 should have 10 log records ", 10, lOfLogRecords.size());

			ArrayList<BillRecord> lOfBillRecords = fairBilling.getlOfBillRecords();
			assertEquals("User ALICE99 should have 1 bill record  ", 1, lOfBillRecords.size());

			BillRecord billRecord = lOfBillRecords.get(0);
			assertEquals("User name should be ALICE99 ", "ALICE99", billRecord.getName());
			assertEquals("Number of sessions should be 7 ", 7, billRecord.getNoOfSessions());
			assertEquals("Total used time should be 420 ", 420, billRecord.getTotalTime());

		} catch (Exception e) {
			assertFalse("file not found", e instanceof FileNotFoundException);
		}
	}

	@Test
	public void testFairBillingUsercharlieBill() {
		try {
			fairBilling.calculateBills(userDir + "/src/test/resources/fairbilling3.log");

			assertEquals("earliest record should be 14:02:05 ", "14:02:05", fairBilling.getTimeOfFirstRecord());
			assertEquals("latest record 14:07:41 ", "14:07:41", fairBilling.getTimeOfLastRecord());
			assertEquals("Size of map of log records should be 1 ", 1, fairBilling.getlogRecordMap().size());

			Map<String, ArrayList<LogRecord>> logRecordMap = fairBilling.getlogRecordMap();

			List<LogRecord> lOfLogRecords = logRecordMap.get("CHARLIE");
			assertEquals("User CHARLIE should have 10 log records  ", 7, lOfLogRecords.size());

			ArrayList<BillRecord> lOfBillRecords = fairBilling.getlOfBillRecords();
			assertEquals("User CHARLIE should have 1 bill record  ", 1, lOfBillRecords.size());

			BillRecord billRecord = lOfBillRecords.get(0);
			assertEquals("User name should be CHARLIE ", "CHARLIE", billRecord.getName());
			assertEquals("Number of sessions should be 5 ", 5, billRecord.getNoOfSessions());
			assertEquals("Total used time should be 707  ", 707, billRecord.getTotalTime());

		} catch (Exception e) {
			assertFalse("file not found", e instanceof FileNotFoundException);
		}
	}
}

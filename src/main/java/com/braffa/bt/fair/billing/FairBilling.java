package com.braffa.bt.fair.billing;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FairBilling extends ReadAndValidateLogFile {

	private ArrayList<BillRecord> lOfBillRecords;

	public ArrayList<BillRecord> getlOfBillRecords() {
		return lOfBillRecords;
	}

	public void calculateBills(String filePath) throws FileNotFoundException {
		readFile(filePath);
		lOfBillRecords = new ArrayList<BillRecord>();
		Map<String, ArrayList<LogRecord>> mapOfLogRecords = getlogRecordMap();
		for (Map.Entry<String, ArrayList<LogRecord>> entry : mapOfLogRecords.entrySet()) {
			lOfBillRecords.add(calculateBill(entry.getValue()));
		}
	}

	private BillRecord calculateBill(List<LogRecord> lOfRecords) {
		ArrayList<Integer> startArray = new ArrayList<Integer>();
		ArrayList<Integer> endArray = new ArrayList<Integer>();
		for (LogRecord log : lOfRecords) {
			if (log.getLabel().equals(START)) {
				startArray.add(getSeconds(log.getTime()));
			} else if (log.getLabel().equals(END)) {
				endArray.add(getSeconds(log.getTime()));
				if (startArray.isEmpty() || (startArray.size() < endArray.size())) {
					startArray.add(getSeconds(getTimeOfFirstRecord()));
				}
			}
		}
		if (startArray.size() > endArray.size()) {
			while (startArray.size() != endArray.size()) {
				endArray.add(getSeconds(getTimeOfLastRecord()));
			}
		}
		int time = 0;
		for (int x = 0; x < startArray.size(); x++) {
			time = time + endArray.get(x) - startArray.get(x);
		}
		BillRecord billRecord = new BillRecord(lOfRecords.get(1).getName(), startArray.size(), time);
		return billRecord;
	}

}

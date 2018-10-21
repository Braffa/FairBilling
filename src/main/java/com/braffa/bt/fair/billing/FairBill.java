package com.braffa.bt.fair.billing;

import java.util.ArrayList;

public class FairBill {

	public static void main(String[] args) {
		try {
			FairBilling fairBilling = new FairBilling();
			fairBilling.calculateBills(args[0]);
			ArrayList<BillRecord> lOfBills = fairBilling.getlOfBillRecords();
			for (BillRecord br : lOfBills) {
				System.out.println(br.getName() + " " + br.getNoOfSessions() + " " + br.getTotalTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

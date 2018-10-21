package com.braffa.bt.fair.billing;

public class FairBillingUtilities {
	
	protected static final String START = "Start";
	protected static final String END = "End";
	
	protected int getSeconds(String time) {
		int hh = Integer.parseInt(time.substring(0, 2));
		int mm = Integer.parseInt(time.substring(3, 5));
		int ss = Integer.parseInt(time.substring(6, 8));
		int seconds = (hh * 60 * 60) + (mm * 60) + ss;
		return seconds;
	}

}

package com.braffa.bt.fair.billing;

public class BillRecord {
	private String name;
	private int noOfSessions;
	private int totalTime;

	public BillRecord(String name, int noOfSessions, int totalTime) {
		super();
		this.name = name;
		this.noOfSessions = noOfSessions;
		this.totalTime = totalTime;
	}

	public String getName() {
		return name;
	}

	public int getNoOfSessions() {
		return noOfSessions;
	}

	public int getTotalTime() {
		return totalTime;
	}

	@Override
	public String toString() {
		return "BillRecord [name=" + name + ", noOfSessions=" + noOfSessions + ", totalTime=" + totalTime + "]";
	}

}

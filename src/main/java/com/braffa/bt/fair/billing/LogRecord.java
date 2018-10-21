package com.braffa.bt.fair.billing;

public class LogRecord {
	
	private String time;
	private String name;
	private String label;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public boolean isValid () {
		if (time.equals(null) || name.equals(null) || label.equals(null)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "LogRecord [time=" + time + ", name=" + name + ", label=" + label + "]";
	}
	
}

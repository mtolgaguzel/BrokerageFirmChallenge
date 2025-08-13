package com.tolga.brokeragefirmchallenge.constants;

public enum Status {
	PENDING("PENDING"),
	MATCHED("MATCHED"),
	CANCELLED("CANCELLED"),
	NONEXISTENT("NONEXISTENT");
	
	private final String label;
	
	Status(String label) {
		this.label = label;
	}
	
	public static Status getStatus(String label) {
		for (Status status : values()) {
			if (status.label.equals(label)) {
				return status;
			}
		}
		return Status.NONEXISTENT;
	}
}

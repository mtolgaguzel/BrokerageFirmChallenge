package com.tolga.brokeragefirmchallenge.constants;

public enum Result {
	SUCCESS("SUCCESS"),
	EXCEPTIONAL_ERROR("EXCEPTIONAL_ERROR"),
	NONEXISTENT("NONEXISTENT");
	
	private final String label;
	
	Result(String label) {
		this.label = label;
	}
	
	public static Result getResult(String label) {
		for (Result result : values()) {
			if (result.label.equals(label)) {
				return result;
			}
		}
		return Result.NONEXISTENT;
	}
	
}

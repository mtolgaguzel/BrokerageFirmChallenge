package com.tolga.brokeragefirmchallenge.constants;

public enum SideEnum {
	BUY("BUY"),
	SELL("SELL"),
	NONEXISTENT("NONEXISTENT");
	
	private final String label;
	
	SideEnum(String label) {
		this.label = label;
	}
	
	public static SideEnum getSideEnum(String label) {
		for (SideEnum sideEnum : values()) {
			if (sideEnum.label.equals(label)) {
				return sideEnum;
			}
		}
		return SideEnum.NONEXISTENT;
	}
}

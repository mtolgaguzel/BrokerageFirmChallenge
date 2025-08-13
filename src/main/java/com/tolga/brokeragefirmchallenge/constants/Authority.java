package com.tolga.brokeragefirmchallenge.constants;

public enum Authority {
	USER("USER"),
	ADMIN("ADMIN"),
	NONEXISTENT("NONEXISTENT");
	
	private final String label;
	
	Authority(String label) {
		this.label = label;
	}
	
	public static Authority getAuthority(String label) {
		for (Authority authority : values()) {
			if (authority.label.equals(label)) {
				return authority;
			}
		}
		return Authority.NONEXISTENT;
	}
}

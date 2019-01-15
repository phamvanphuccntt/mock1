package com.itsol.model;

public enum Role {
	CANDIDATE("CANDIDATE"), HR("HR"), MARKETING("MARKETING"), MANAGER("MANAGER");
	
	private final String name;       

    private Role(String name) {
        this.name = name;
    }

    public String getName() {
       return this.name;
    }

}

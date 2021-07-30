package uk.gov.ch.transformers.transaction;

public enum TransactionCategory {
	
	ACCOUNTS("0", "Accounts"),
	ANNUAL_RETURN("1", "Annual Return"),
	TWO("2","two 2"),
	THREE("3", "three 3"),
	MORTGAGE("4", "Mortgage"),
	LIQUIDATION("5", "Liquidation"),
	INCORPORATION("6", "Incorporation"),
	CHANGE_OF_NAME("7", "Change of name"),
	CAPITAL("8","Capital"),
	NINE("9", "Nine 9"),
	TEN("10", "Ten 10"),
	ELEVEN("11", "Eleven 11");
	
	TransactionCategory(String id, String description){
		this.id = id;
		this.description = description;
	}
	
	private String id;
	private String description;
	
	public static TransactionCategory fromString(String id) {
		for(TransactionCategory tc : TransactionCategory.values()) {
			if(tc.id.equalsIgnoreCase(id)) {
				return tc;
			}
		}
		throw new IllegalArgumentException("No constant with id " + id + "found");
	}
	
	public String getDescription() {
		return description;
	}
	
	

}

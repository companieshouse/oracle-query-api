package uk.gov.ch.transformers.transaction;

public enum TransactionCategory {

    ACCOUNTS("0", "Accounts"), 
    ANNUAL_RETURN("1", "Annual Return"), 
    APPOINTMENT("2", "Appointment"),
    REGISTERED_OFFICE("3", "Registered Office"), 
    MORTGAGE("4", "Mortgage"), 
    LIQUIDATION("5", "Liquidation"),
    INCORPORATION("6", "New Incorporation"), 
    CHANGE_OF_NAME("7", "Change Of Name"), 
    CAPITAL("8", "Capital"),
    MISCELLANEOUS("9", "Miscellaneous");

    TransactionCategory(String id, String description) {
        this.id = id;
        this.description = description;
    }

    private String id;
    private String description;

    public static TransactionCategory fromString(String id) {
        for (TransactionCategory tc : TransactionCategory.values()) {
            if (tc.id.equalsIgnoreCase(id)) {
                return tc;
            }
        }
        throw new IllegalArgumentException("No constant with id " + id + "found");
    }

    public String getDescription() {
        return description;
    }

}
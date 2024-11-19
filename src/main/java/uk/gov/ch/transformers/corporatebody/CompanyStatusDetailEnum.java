package uk.gov.ch.transformers.corporatebody;

public enum CompanyStatusDetailEnum {

    TRANSFERRED_FROM_UK("5", "transferred-from-uk"),
    ACTIVE_PROPOSAL_TO_STRIKE_OFF("Q", "active-proposal-to-strike-off"),
    PETITION_TO_RESTORE_DISSOLVED("R", "petition-to-restore-dissolved"),
    TRANSFORMED_TO_SE("X", "transformed-to-se"),
    CONVERTED_TO_PLC("Z", "converted-to-plc"),
    CONVERTED_TO_UK_SOCIETAS("AA", "converted-to-uk-societas"),
    CONVERTED_TO_UKEIG("AB", "converted to ukeig");

    private final String id;
    private final String description;
    CompanyStatusDetailEnum(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public static CompanyStatusDetailEnum fromString(String id) {
        for (CompanyStatusDetailEnum csde : CompanyStatusDetailEnum.values()) {
            if (csde.id.equalsIgnoreCase(id)) {
                return csde;
            }
        }
        throw new IllegalArgumentException("No enum with id " + id + " exists");
    }

    public String getDescription() {
        return description;
    }

}

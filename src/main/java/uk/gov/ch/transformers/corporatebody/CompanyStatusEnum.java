package uk.gov.ch.transformers.corporatebody;

public enum CompanyStatusEnum {
    
    ACTIVE("0","active"),
    DISSOLVED("1","dissolved"),
    LIQUIDATION("2","liquidation"),
    RECEIVERSHIP("4", "receivership"),
    CONVERTED_CLOSED("5", "converted-closed");    
    
    CompanyStatusEnum(String id, String description) {
        this.id = id;
        this.description = description;
    }
    
    private String id;
    private String description;
    
    public static CompanyStatusEnum fromString(String id) {
        for(CompanyStatusEnum tse : CompanyStatusEnum.values()) {
            if(tse.id.equalsIgnoreCase(id)) {
                return tse;
            }
        }
        throw new IllegalArgumentException("No enum with id " + id + " exists");
    }
    
    public String getDescription() {
        return description;
    }

}

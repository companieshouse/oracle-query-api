package uk.gov.ch.transformers.corporatebody;

public enum CompanyAccountTypeEnum {
    
    FULL("1","full"),
    SMALL("2","small"),
    MEDIUM("3","medium"),
    GROUP("4","group"),
    DORMANT("5","dormant"),
    INTERIM("6","interim"),
    INITIAL("7","initial"),
    TOTAL_EXEMPTION_FULL("8","total-exemption-full"),
    TOTAL_EXEMPTION_SMALL("9","total-exemption-small"),
    PARTIAL_EXEMPTION("10","partial-exemption"),
//    PARENT_LAW_ACCOUNTS("11",""),
//    OVERSEAS_EXEMPTION("12",""),
//    REJECTED("13",""),
    AUDIT_EXEMPTION_SUBSIDIARY("14","audit-exemption-subsidiary"),
    FILING_EXEMPTION_SUBSIDIARY("15","filing-exemption-subsidiary"),
    MICRO_ENTITY("16","micro-entity"),
    AUDITED_ABRIDGED("17","audited-abridged"),
    UNAUDITED_ABRIDGED("18","unaudited-abridged");
    
    private CompanyAccountTypeEnum(String id, String description) {
        this.id = id;
        this.description = description;
    }
    
    private String id;
    private String description;
    
    public static CompanyAccountTypeEnum fromString(String id) {
        for(CompanyAccountTypeEnum tse : CompanyAccountTypeEnum.values()) {
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

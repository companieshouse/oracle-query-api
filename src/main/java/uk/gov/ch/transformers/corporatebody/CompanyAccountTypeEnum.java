package uk.gov.ch.transformers.corporatebody;

public enum CompanyAccountTypeEnum {
    
    FULL("1","full"),
    SMALL("2","small"),
    MEDIUM("3","medium"),
    GROUP("4","group"),
    DORMANT("5","dormant"),
    INTERIM("6","interim"),
    INITIAL("7","initial"),
    NO_ACCOUNTS_TYPE_AVAILABLE("8", "no-accounts-type-available"),
    TOTAL_EXEMPTION_FULL("9","total-exemption-full"),
    TOTAL_EXEMPTION_SMALL("A","total-exemption-small"),
    PARTIAL_EXEMPTION("B","partial-exemption"),
//    PARENT_LAW_ACCOUNTS("11",""),
//    OVERSEAS_EXEMPTION("12",""),
//    REJECTED("13",""),
    AUDIT_EXEMPTION_SUBSIDIARY("C","audit-exemption-subsidiary"),
    FILING_EXEMPTION_SUBSIDIARY("D","filing-exemption-subsidiary"),
    MICRO_ENTITY("E","micro-entity"),
    AUDITED_ABRIDGED("F","audited-abridged"),
    UNAUDITED_ABRIDGED("G","unaudited-abridged");
    
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

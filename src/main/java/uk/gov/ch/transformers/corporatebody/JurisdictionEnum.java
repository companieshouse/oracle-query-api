package uk.gov.ch.transformers.corporatebody;

public enum JurisdictionEnum {
    
    ENGLAND_WALES("1","england-wales"),
    WALES("2","wales"),
    SCOTLAND("3","scotland"),
    NORTHERN_IRELAND("4","northern-ireland"),
    EUROPEAN_UNION("5","european-union"),
    UNITED_KINGDOM("6","united-kingdom"),
    ENGLAND("7","england"),
    FOREIGN_NON_EU("8","noneu");
    
    
    JurisdictionEnum(String id, String description) {
        this.id = id;
        this.description = description;
    }
    
    private String id;
    private String description;
    
    public static JurisdictionEnum fromString(String id) {
        for(JurisdictionEnum js : JurisdictionEnum.values()) {
            if(js.id.equals(id)) {
                return js;
            }
        }
        throw new IllegalArgumentException("No enum with id " + id + " exists");
    }
    
    public String getDescription() {
        return description;
    }

}

package uk.gov.ch.transformers.corporatebody;

public enum CompanyStatusEnum {
    
    ACTIVE("0","active"),
    DISSOLVED("1","dissolved"),
    LIQUIDATION("2","liquidation"),
    RECEIVERSHIP("4", "receivership"),
    CONVERTED_CLOSED("5", "converted-closed"),
    TRANSFERRED_FROM_GB("5","active"),
    RECEIVERSHIP_A("A","receivership"),
    VOLUNTARY_ARRANGEMENT_B("B","voluntary-arrangement"),
    VOLUNTARY_ARRANGEMENT_RECEIVERSHIP("C","insolvency-proceedings"),
    ADMINISTRATION_ORDER("D","administration"),
    LIVE_BUT_RECEIVER_MANAGER("F","receivership"),
    ADMINISTRATIVE_RECEIVER("G","receivership"),
    RECEIVER_MANAGER_ADMINISTRATIVE_RECEIVER("H","insolvency-proceedings"),
    VOLUNTARY_ARRANGEMENT_I("I","voluntary-arrangement"),
    VOLUNTARY_ARRANGEMENT_RECEIVER_MANAGER("J","insolvency-proceedings"),
    VOLUNTARY_ARRANGEMENT_ADMINISTRATIVE_RECEIVER("K","insolvency-proceedings"),
    VOLUNTARY_ARRANGEMENT_ADMINISTRATIVE_RECEIVER_RECEIVER_MANAGER("L","insolvency-proceedings"),
    ADMINISTRATION_ORDER_M("M","administration"),
    ADMINISTRATION_ORDER_RECEIVER_MANAGER_N("N","insolvency-proceedings"),
    ADMINISTRATION_ORDER_ADMINISTRATIVE_RECEIVER("O","insolvency-proceedings"),
    ADMINISTRATION_ORDER_RECEIVER_MANAGER_P("P","insolvency-proceedings"),
    ACTIVE_PROPOSAL_TO_STRIKE_OFF("Q","active"),
    PETITION_TO_RESTORE_DISSOLVED("R","dissolved"),
    IN_ADMINISTRATION_RECEIVERSHIP("S","insolvency-proceedings"),
    IN_ADMINISTRATION("T","administration"),
    IN_ADMINISTRATION_RECEIVER_MANAGER("U","insolvency-proceedings"),
    IN_ADMINISTRATION_ADMINISTRATIVE_RECEIVER("V","insolvency-proceedings"),
    IN_ADMINISTRATION_RECEIVER_MANAGER_ADMINISTRATIVE_RECEIVER("W","insolvency-proceedings"),
    TRANSFORMED_TO_SE("X","converted-closed"),
    LIVE_PROPOSED_CONVERSION_TO_SE("Y","active"),
    CONVERTED_TO_PLC("Z","converted-closed"),
    CONVERTED_TO_UK_SOCIETAS("AA","active"),
    CONVERTED_TO_UKEIG("AB","active");    
    
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

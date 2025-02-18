package uk.gov.ch.transformers.corporatebody;

public enum CompanyStatusEnum {

    ACTIVE("0", CompanyStatusConstants.ACTIVE),
    DISSOLVED("1", CompanyStatusConstants.DISSOLVED),
    LIQUIDATION("2", CompanyStatusConstants.LIQUIDATION),
    RECEIVERSHIP("3", CompanyStatusConstants.RECEIVERSHIP),
    CONVERTED_CLOSED("4", CompanyStatusConstants.CONVERTED_CLOSED),
    OTHER_ACTIVE("5", CompanyStatusConstants.ACTIVE),
    TRANSFERRED_FROM_GB("6", CompanyStatusConstants.ACTIVE),
    OTHER_CONVERTED_CLOSED("7", CompanyStatusConstants.CONVERTED_CLOSED),
    OPEN("8", "open"),
    CLOSED("9", "closed"),
    RECEIVERSHIP_A("A", CompanyStatusConstants.RECEIVERSHIP),
    VOLUNTARY_ARRANGEMENT_B("B", CompanyStatusConstants.VOLUNTARY_ARRANGEMENT),
    VOLUNTARY_ARRANGEMENT_RECEIVERSHIP("C", CompanyStatusConstants.INSOLVENCY_PROCEEDINGS),
    ADMINISTRATION_ORDER("D", CompanyStatusConstants.ADMINISTRATION),
    LIVE_BUT_RECEIVER_MANAGER("F", CompanyStatusConstants.RECEIVERSHIP),
    ADMINISTRATIVE_RECEIVER("G", CompanyStatusConstants.RECEIVERSHIP),
    RECEIVER_MANAGER_ADMINISTRATIVE_RECEIVER("H", CompanyStatusConstants.INSOLVENCY_PROCEEDINGS),
    VOLUNTARY_ARRANGEMENT_I("I", CompanyStatusConstants.VOLUNTARY_ARRANGEMENT),
    VOLUNTARY_ARRANGEMENT_RECEIVER_MANAGER("J", CompanyStatusConstants.INSOLVENCY_PROCEEDINGS),
    VOLUNTARY_ARRANGEMENT_ADMINISTRATIVE_RECEIVER("K",
            CompanyStatusConstants.INSOLVENCY_PROCEEDINGS),
    VOLUNTARY_ARRANGEMENT_ADMINISTRATIVE_RECEIVER_RECEIVER_MANAGER("L",
            CompanyStatusConstants.INSOLVENCY_PROCEEDINGS),
    ADMINISTRATION_ORDER_M("M", CompanyStatusConstants.ADMINISTRATION),
    ADMINISTRATION_ORDER_RECEIVER_MANAGER_N("N", CompanyStatusConstants.INSOLVENCY_PROCEEDINGS),
    ADMINISTRATION_ORDER_ADMINISTRATIVE_RECEIVER("O",
            CompanyStatusConstants.INSOLVENCY_PROCEEDINGS),
    ADMINISTRATION_ORDER_RECEIVER_MANAGER_P("P", CompanyStatusConstants.INSOLVENCY_PROCEEDINGS),
    ACTIVE_PROPOSAL_TO_STRIKE_OFF("Q", CompanyStatusConstants.ACTIVE),
    PETITION_TO_RESTORE_DISSOLVED("R", CompanyStatusConstants.DISSOLVED),
    IN_ADMINISTRATION_RECEIVERSHIP("S", CompanyStatusConstants.INSOLVENCY_PROCEEDINGS),
    IN_ADMINISTRATION("T", CompanyStatusConstants.ADMINISTRATION),
    IN_ADMINISTRATION_RECEIVER_MANAGER("U", CompanyStatusConstants.INSOLVENCY_PROCEEDINGS),
    IN_ADMINISTRATION_ADMINISTRATIVE_RECEIVER("V", CompanyStatusConstants.INSOLVENCY_PROCEEDINGS),
    IN_ADMINISTRATION_RECEIVER_MANAGER_ADMINISTRATIVE_RECEIVER("W",
            CompanyStatusConstants.INSOLVENCY_PROCEEDINGS),
    TRANSFORMED_TO_SE("X", CompanyStatusConstants.CONVERTED_CLOSED),
    LIVE_PROPOSED_CONVERSION_TO_SE("Y", CompanyStatusConstants.ACTIVE),
    CONVERTED_TO_PLC("Z", CompanyStatusConstants.CONVERTED_CLOSED),
    CONVERTED_TO_UK_SOCIETAS("AA", CompanyStatusConstants.ACTIVE),
    CONVERTED_TO_UKEIG("AB", CompanyStatusConstants.ACTIVE);

    private final String id;
    private final String description;
    CompanyStatusEnum(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public static CompanyStatusEnum fromString(String id) {
        for (CompanyStatusEnum tse : CompanyStatusEnum.values()) {
            if (tse.id.equalsIgnoreCase(id)) {
                return tse;
            }
        }
        throw new IllegalArgumentException("No enum with id " + id + " exists");
    }

    public String getDescription() {
        return description;
    }

    private static class CompanyStatusConstants {

        public static final String ACTIVE = "active";
        public static final String ADMINISTRATION = "administration";
        public static final String CONVERTED_CLOSED = "converted-closed";
        public static final String DISSOLVED = "dissolved";
        public static final String INSOLVENCY_PROCEEDINGS = "insolvency-proceedings";
        public static final String LIQUIDATION = "liquidation";
        public static final String RECEIVERSHIP = "receivership";
        public static final String VOLUNTARY_ARRANGEMENT = "voluntary-arrangement";
    }

}

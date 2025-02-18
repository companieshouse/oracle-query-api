package uk.gov.ch.transformers.corporatebody;

public enum CorporateBodyTypeEnum {

    PRIVATE_UNLIMITED("1", "private-unlimited"),
    PRIVATE_LIMITED("2", "ltd"),
    PUBLIC_LIMITED_COMPANY("3", "plc"),
    OLD_PUBLIC_COMPANY("4", "old-public-company"),
    PRIVATE_LIMITED_BY_GUARANTEE_LIMITED_EXEMPTION("5",
            "private-limited-guarant-nsc-limited-exemption"),
    LIMITED_PARTNERSHIP("6", "limited-partnership"),
    PRIVATE_LIMITED_BY_GUARANTEE("7", "private-limited-guarant-nsc"),
    CONVERTED_CLOSED("8", "converted-or-closed"),
    PRIVATE_UNLIMITED_NO_SHARE_CAPITAL("9", "private-unlimited-nsc"),
    PRIVATE_LIMITED_BY_SHARES_SECTION_30("10", "private-limited-shares-section-30-exemption"),
    ASSURANCE_COMPANY("11", "assurance-company"),
    OVERSEA_COMPANY("12", "oversea-company"),
    EUROPEAN_ECONOMIC_INTEREST_GROUPING_ESTABLISHMENT("13", "eeig"),
    INVESTMENT_COMPANY_VARIABLE_CAPITAL_SECURITIES("14", "icvc-securities"),
    INVESTMENT_COMPANY_VARIABLE_CAPITAL_WARRANT("15", "icvc-warrant"),
    INVESTMENT_COMPANY_VARIABLE_CAPITAL_UMBRELLA("16", "icvc-umbrella"),
    INDUSTRIAL_AND_PROVIDENT_SOCIETY("17", "industrial-and-provident-society"),
    NORTHERN_IRELAND_COMPANY("18", "northern-ireland"),
    OTHER_TYPE_OF_COMPANY("19", "northern-ireland-other"),
    LIMITED_LIABILITY_PARTNERSHIP("20", "llp"),
    ROYAL_CHARTER_COMPANY("21", "royal-charter"),
    INVESTMENT_COMPANY_VARIABLE_CAPITAL("22", "investment-company-with-variable-capital"),
    UNREGISTERED_COMPANY("23", "unregistered-company"),
    LIMITED_LIABILITY_PARTNERSHIPS("24", "Limited Liability Partnerships"),
    OTHER_COMPANY_TYPE("25", "other"),
    UNITED_KINGDOM_SOCIETAS("26", "United Kingdom Societas"),
    REGISTERED_SOCIETY("27", "registered-society-non-jurisdictional"),
    PROTECTED_CELL_COMPANY("29", "protected-cell-company"),
    SCOTTISH_PARTNERSHIP("30", "scottish-partnership"),
    CHARITABLE_INCORPORATED_ORGANISATION("31", "charitable-incorporated-organisation"),
    SCOTTISH_CHARITABLE_INCORPORATED_ORGANISATION("32",
            "scottish-charitable-incorporated-organisation"),
    FURTHER_EDUCATION_SIXTH_FORM_COLLEGE("33",
            "further-education-or-sixth-form-college-corporation"),
    EUROPEAN_PUBLIC_LIMITED_LIABILITY_COMPANY("34", "european-public-limited-liability-company-se"),
    EUROPEAN_ECONOMIC_INTEREST_GROUPING("35", "eeig)"),
    UNITED_KINGDOM_ECONOMIC_INTEREST_GROUPING("36", "United Lingdom Economic Interest Grouping");


    private final String id;
    private final String description;
    CorporateBodyTypeEnum(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public static CorporateBodyTypeEnum fromString(String id) {
        for (CorporateBodyTypeEnum cbte : CorporateBodyTypeEnum.values()) {
            if (cbte.id.equalsIgnoreCase(id)) {
                return cbte;
            }
        }
        throw new IllegalArgumentException("No enum with id " + id + "exists");
    }

    public String getDescription() {
        return description;
    }

}

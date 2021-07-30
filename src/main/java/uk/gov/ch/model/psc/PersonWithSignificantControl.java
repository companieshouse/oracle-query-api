package uk.gov.ch.model.psc;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonWithSignificantControl {

    @JsonProperty("OFFICER_FORENAME1")
    private String officerForename1;
    @JsonProperty("OFFICER_FORENAME2")
    private String officerForename2;
    @JsonProperty("OFFICER_SURNAME")
    private String officerSurname;
    @JsonProperty("APPOINTMENT_TYPE_ID")
    private String appointmentTypeId;
    @JsonProperty("SERVICE_ADDRESS_LINE1")
    private String serviceAddressLine1;
    @JsonProperty("SERVICE_ADDRESS_POST_CODE")
    private String serviceAddressPostCode;
    @JsonProperty("SERVICE_ADDRESS_POST_TOWN")
    private String serviceAddressPostTown;
    @JsonProperty("SUPER_SECURE_PSC_IND")
    private String superSecurePscInd;
    @JsonProperty("OFFICER_NATIONALITY")
    private String officerNationality;
    @JsonProperty("OFFICER_DATE_OF_BIRTH")
    private String officerDateOfBirth;
    @JsonProperty("HOUSE_NAME_NUMBER")
    private String houseNameNumber;
    @JsonProperty("STREET")
    private String street;
    @JsonProperty("AREA")
    private String area;
    @JsonProperty("POST_TOWN")
    private String postTown;
    @JsonProperty("POST_CODE")
    private String postCode;
    @JsonProperty("REGION")
    private String region;
    @JsonProperty("COUNTY_NAME")
    private String countryName;
    @JsonProperty("SUPPLIED_COMPANY_NAME")
    private String suppliedCompanyName;
    @JsonProperty("PO_BOX")
    private String poBox;
    @JsonProperty("ADDRESS_LINE1")
    private String addressLine1;
    @JsonProperty("NATURE_OF_CONTROL")
    private String natureOfControl;

    public String getOfficerForename1() {
        return officerForename1;
    }

    public void setOfficerForename1(String officerForename1) {
        this.officerForename1 = officerForename1;
    }

    public String getOfficerForename2() {
        return officerForename2;
    }

    public void setOfficerForename2(String officerForename2) {
        this.officerForename2 = officerForename2;
    }

    public String getOfficerSurname() {
        return officerSurname;
    }

    public void setOfficerSurname(String officerSurname) {
        this.officerSurname = officerSurname;
    }

    public String getAppointmentTypeId() {
        return appointmentTypeId;
    }

    public void setAppointmentTypeId(String appointmentTypeId) {
        this.appointmentTypeId = appointmentTypeId;
    }

    public String getServiceAddressLine1() {
        return serviceAddressLine1;
    }

    public void setServiceAddressLine1(String serviceAddressLine1) {
        this.serviceAddressLine1 = serviceAddressLine1;
    }

    public String getServiceAddressPostCode() {
        return serviceAddressPostCode;
    }

    public void setServiceAddressPostCode(String serviceAddressPostCode) {
        this.serviceAddressPostCode = serviceAddressPostCode;
    }

    public String getServiceAddressPostTown() {
        return serviceAddressPostTown;
    }

    public void setServiceAddressPostTown(String serviceAddressPostTown) {
        this.serviceAddressPostTown = serviceAddressPostTown;
    }

    public String getSuperSecurePscInd() {
        return superSecurePscInd;
    }

    public void setSuperSecurePscInd(String superSecurePscInd) {
        this.superSecurePscInd = superSecurePscInd;
    }

    public String getOfficerNationality() {
        return officerNationality;
    }

    public void setOfficerNationality(String officerNationality) {
        this.officerNationality = officerNationality;
    }

    public String getOfficerDateOfBirth() {
        return officerDateOfBirth;
    }

    public void setOfficerDateOfBirth(String officerDateOfBirth) {
        this.officerDateOfBirth = officerDateOfBirth;
    }

    public String getHouseNameNumber() {
        return houseNameNumber;
    }

    public void setHouseNameNumber(String houseNameNumber) {
        this.houseNameNumber = houseNameNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPostTown() {
        return postTown;
    }

    public void setPostTown(String postTown) {
        this.postTown = postTown;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getSuppliedCompanyName() {
        return suppliedCompanyName;
    }

    public void setSuppliedCompanyName(String suppliedCompanyName) {
        this.suppliedCompanyName = suppliedCompanyName;
    }

    public String getPoBox() {
        return poBox;
    }

    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getNatureOfControl() {
        return natureOfControl;
    }

    public void setNatureOfControl(String natureOfControl) {
        this.natureOfControl = natureOfControl;
    }
}

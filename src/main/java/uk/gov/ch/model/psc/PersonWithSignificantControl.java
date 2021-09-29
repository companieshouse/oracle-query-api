package uk.gov.ch.model.psc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PersonWithSignificantControl {

    @Id
    @Column(name = "CORPORATE_BODY_APPOINTMENT_ID")
    private Long corporateBodyAppointmentId;
    @Column(name = "OFFICER_FORENAME_1")
    private String officerForename1;
    @Column(name = "OFFICER_FORENAME_2")
    private String officerForename2;
    @Column(name = "OFFICER_SURNAME")
    private String officerSurname;
    @Column(name = "APPOINTMENT_TYPE_ID")
    private String appointmentTypeId;
    @Column(name = "SERVICE_ADDRESS_LINE_1")
    private String serviceAddressLine1;
    @Column(name = "SERVICE_ADDRESS_POST_CODE")
    private String serviceAddressPostCode;
    @Column(name = "SERVICE_ADDRESS_POST_TOWN")
    private String serviceAddressPostTown;
    @Column(name = "SERVICE_ADDRESS_CARE_OF")
    private String serviceAddressCareOf;
    @Column(name = "SERVICE_ADDRESS_REGION")
    private String serviceAddressRegion;
    @Column(name = "SERVICE_ADDRESS_COUNTRY_NAME")
    private String serviceAddressCountryName;
    @Column(name = "SERVICE_ADDRESS_PO_BOX")
    private String serviceAddressPoBox;
    @Column(name = "SERVICE_ADDRESS_AREA")
    private String serviceAddressArea;
    @Column(name = "SERVICE_ADDRESS_HOUSE_NAME_NUMBER")
    private String serviceAddressHouseNameNumber;
    @Column(name = "OFFICER_NATIONALITY")
    private String officerNationality;
    @Column(name = "OFFICER_DATE_OF_BIRTH")
    private String officerDateOfBirth;
    @Column(name = "HOUSE_NAME_NUMBER")
    private String houseNameNumber;
    @Column(name = "STREET")
    private String street;
    @Column(name = "AREA")
    private String area;
    @Column(name = "POST_TOWN")
    private String postTown;
    @Column(name = "POST_CODE")
    private String postCode;
    @Column(name = "REGION")
    private String region;
    @Column(name = "COUNTRY_NAME")
    private String countryName;
    @Column(name = "SUPPLIED_COMPANY_NAME")
    private String suppliedCompanyName;
    @Column(name = "PO_BOX")
    private String poBox;
    @Column(name = "CARE_OF")
    private String careOf;
    @Column(name = "ADDRESS_LINE_1")
    private String addressLine1;
    @Column(name = "NATURE_OF_CONTROL")
    private String natureOfControl;
    @Column(name = "PSC_REGISTRATION_NUMBER")
    private String registrationNumber;
    @Column(name = "PSC_LAW_GOVERNED")
    private String lawGoverned;
    @Column(name = "PSC_LEGAL_FORM")
    private String legalForm;
    @Column(name = "PSC_COUNTRY")
    private String countryOfResidence;

    public Long getCorporateBodyAppointmentId() {
        return corporateBodyAppointmentId;
    }

    public void setCorporateBodyAppointmentId(Long corporateBodyAppointmentId) {
        this.corporateBodyAppointmentId = corporateBodyAppointmentId;
    }

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

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getLawGoverned() {
        return lawGoverned;
    }

    public void setLawGoverned(String lawGoverned) {
        this.lawGoverned = lawGoverned;
    }

    public String getLegalForm() {
        return legalForm;
    }

    public void setLegalForm(String legalForm) {
        this.legalForm = legalForm;
    }

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public String getServiceAddressCareOf() {
        return serviceAddressCareOf;
    }

    public void setServiceAddressCareOf(String serviceAddressCareOf) {
        this.serviceAddressCareOf = serviceAddressCareOf;
    }

    public String getServiceAddressRegion() {
        return serviceAddressRegion;
    }

    public void setServiceAddressRegion(String serviceAddressRegion) {
        this.serviceAddressRegion = serviceAddressRegion;
    }

    public String getServiceAddressCountryName() {
        return serviceAddressCountryName;
    }

    public void setServiceAddressCountryName(String serviceAddressCountryName) {
        this.serviceAddressCountryName = serviceAddressCountryName;
    }

    public String getServiceAddressPoBox() {
        return serviceAddressPoBox;
    }

    public void setServiceAddressPoBox(String serviceAddressPoBox) {
        this.serviceAddressPoBox = serviceAddressPoBox;
    }

    public String getServiceAddressArea() {
        return serviceAddressArea;
    }

    public void setServiceAddressArea(String serviceAddressArea) {
        this.serviceAddressArea = serviceAddressArea;
    }

    public String getCareOf() {
        return careOf;
    }

    public void setCareOf(String careOf) {
        this.careOf = careOf;
    }

    public String getServiceAddressHouseNameNumber() {
        return serviceAddressHouseNameNumber;
    }

    public void setServiceAddressHouseNameNumber(String serviceAddressHouseNameNumber) {
        this.serviceAddressHouseNameNumber = serviceAddressHouseNameNumber;
    }
}

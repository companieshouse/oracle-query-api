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
    @Column(name = "SUPER_SECURE_PSC_IND")
    private String superSecurePscInd;
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
    @Column(name = "ADDRESS_LINE_1")
    private String addressLine1;
    @Column(name = "NATURE_OF_CONTROL")
    private String natureOfControl;

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

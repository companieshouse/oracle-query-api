package uk.gov.ch.model.psc;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import uk.gov.companieshouse.api.model.common.Address;

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
    @Column(name = "APPOINTMENT_DATE")
    private String appointmentDate;
    @Column(name = "SERVICE_ADDRESS_LINE_1")
    private String serviceAddressLine1;
    @Column(name = "SERVICE_ADDRESS_LINE_2")
    private String serviceAddressLine2;
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
    @Column(name = "OFFICER_NATIONALITY")
    private String officerNationality;
    @Column(name = "USUAL_RESIDENTIAL_COUNTRY")
    private String usualResidentialCountry;
    @Column(name = "OFFICER_DATE_OF_BIRTH")
    private String officerDateOfBirth;
    @Column(name = "HOUSE_NAME_NUMBER")
    private String houseNameNumber;
    @Column(name = "STREET")
    private String street;
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
    @Column(name = "PSC_REGISTER_LOCATION")
    private String registerLocation;
    @Column(name = "PSC_REGISTRATION_NUMBER")
    private String registrationNumber;
    @Column(name = "PSC_LAW_GOVERNED")
    private String lawGoverned;
    @Column(name = "PSC_LEGAL_FORM")
    private String legalForm;
    @Column(name = "PSC_COUNTRY")
    private String pscCountry;

    @Transient
    @JsonProperty("service_address")
    private Address serviceAddress;

    @Transient
    private Address address;

    public Address getServiceAddress() {
        serviceAddress = new Address();
        serviceAddress.setAddressLine1(serviceAddressLine1);
        serviceAddress.setAddressLine2(serviceAddressLine2);
        serviceAddress.setCareOf(serviceAddressCareOf);
        serviceAddress.setCountry(serviceAddressCountryName);
        serviceAddress.setLocality(serviceAddressPostTown);
        serviceAddress.setPoBox(serviceAddressPoBox);
        serviceAddress.setPostalCode(serviceAddressPostCode);
        serviceAddress.setRegion(serviceAddressRegion);
        return serviceAddress;
    }

    public Address getAddress() {
        address = new Address();
        address.setAddressLine1(houseNameNumber);
        address.setAddressLine2(street);
        address.setCareOf(careOf);
        address.setCountry(countryName);
        address.setLocality(postTown);
        address.setPoBox(poBox);
        address.setPostalCode(postCode);
        address.setRegion(region);
        return address;
    }

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

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setServiceAddressLine1(String serviceAddressLine1) {
        this.serviceAddressLine1 = serviceAddressLine1;
    }

    public void setServiceAddressPostCode(String serviceAddressPostCode) {
        this.serviceAddressPostCode = serviceAddressPostCode;
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

    public String getUsualResidentialCountry() {
        return usualResidentialCountry;
    }

    public void setUsualResidentialCountry(String usualResidentialCountry) {
        this.usualResidentialCountry = usualResidentialCountry;
    }

    public String getOfficerDateOfBirth() {
        return officerDateOfBirth;
    }

    public void setOfficerDateOfBirth(String officerDateOfBirth) {
        this.officerDateOfBirth = officerDateOfBirth;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostTown(String postTown) {
        this.postTown = postTown;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public void setPoBox(String poBox) {
        this.poBox = poBox;
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

    public String getRegisterLocation() {
        return registerLocation;
    }

    public void setRegisterLocation(String registerLocation) {
        this.registerLocation = registerLocation;
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

    public String getPscCountry() {
        return pscCountry;
    }

    public void setPscCountry(String pscCountry) {
        this.pscCountry = pscCountry;
    }

    public void setServiceAddressCareOf(String serviceAddressCareOf) {
        this.serviceAddressCareOf = serviceAddressCareOf;
    }

    public void setServiceAddressRegion(String serviceAddressRegion) {
        this.serviceAddressRegion = serviceAddressRegion;
    }

    public void setServiceAddressCountryName(String serviceAddressCountryName) {
        this.serviceAddressCountryName = serviceAddressCountryName;
    }

    public void setServiceAddressPoBox(String serviceAddressPoBox) {
        this.serviceAddressPoBox = serviceAddressPoBox;
    }

    public void setCareOf(String careOf) {
        this.careOf = careOf;
    }

    public void setServiceAddressLine2(String serviceAddressLine2) {
        this.serviceAddressLine2 = serviceAddressLine2;
    }

    public String getHouseNameNumber() {
        return houseNameNumber;
    }

    public void setHouseNameNumber(String houseNameNumber) {
        this.houseNameNumber = houseNameNumber;
    }
}

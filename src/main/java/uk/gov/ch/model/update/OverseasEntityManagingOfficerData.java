package uk.gov.ch.model.update;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import uk.gov.companieshouse.api.model.common.Address;

@Entity
public class OverseasEntityManagingOfficerData {

    @Id
    @Column(name = "MO_ID")
    private String managingOfficerId;
    @Column(name = "RESIDENTIAL_ADDRESS_HOUSE_NAME_NUMBER")
    private String residentialPremises;
    @Column(name = "RESIDENTIAL_ADDRESS_LINE_1")
    private String residentialAddressLine1;
    @Column(name = "RESIDENTIAL_ADDRESS_LINE_2")
    private String residentialAddressLine2;
    @Column(name = "RESIDENTIAL_ADDRESS_POST_TOWN")
    private String residentialPostTown;
    @Column(name = "RESIDENTIAL_ADDRESS_REGION")
    private String residentialRegion;
    @Column(name = "RESIDENTIAL_ADDRESS_COUNTRY_NAME")
    private String residentialCountryName;
    @Column(name = "RESIDENTIAL_ADDRESS_POST_CODE")
    private String residentialPostalCode;
    @Column(name = "RESIDENTIAL_ADDRESS_PO_BOX")
    private String residentialPoBox;
    @Column(name = "RESIDENTIAL_ADDRESS_CARE_OF")
    private String residentialCareOf;
    @Column(name = "PRINCIPAL_ADDRESS_HOUSE_NAME_NUMBER")
    private String principalPremises;
    @Column(name = "PRINCIPAL_ADDRESS_LINE_1")
    private String principalAddressLine1;
    @Column(name = "PRINCIPAL_ADDRESS_LINE_2")
    private String principalAddressLine2;
    @Column(name = "PRINCIPAL_ADDRESS_POST_TOWN")
    private String principalTown;
    @Column(name = "PRINCIPAL_ADDRESS_REGION")
    private String principalRegion;
    @Column(name = "PRINCIPAL_ADDRESS_COUNTRY_NAME")
    private String principalCountryName;
    @Column(name = "PRINCIPAL_ADDRESS_POST_CODE")
    private String principalPostalCode;
    @Column(name = "PRINCIPAL_ADDRESS_PO_BOX")
    private String principalPoBox;
    @Column(name = "PRINCIPAL_ADDRESS_CARE_OF")
    private String principalCareOf;

    @Transient
    @JsonProperty("residential_address")
    private Address residentialAddress;

    @Transient
    @JsonProperty("principal_address")
    private Address principalAddress;

    @Column(name = "DOB")
    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    @Column(name = "CONTACT_NAME_FULL")
    @JsonProperty("contact_name_full")
    private String contactNameFull;

    @Column(name = "CONTACT_EMAIL_ADDRESS")
    @JsonProperty("contact_email_address")
    private String contactEmailAddress;

    public OverseasEntityManagingOfficerData() {
    }

    public String getManagingOfficerId() {
        return managingOfficerId;
    }

    public void setManagingOfficerId(String managingOfficerId) {
        this.managingOfficerId = managingOfficerId;
    }

    public String getContactNameFull() {
        return contactNameFull;
    }

    public void setContactNameFull(String contactNameFull) {
        this.contactNameFull = contactNameFull;
    }

    public String getContactEmailAddress() {
        return contactEmailAddress;
    }

    public void setContactEmailAddress(String contactEmailAddress) {
        this.contactEmailAddress = contactEmailAddress;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setResidentialAddressLine1(String residentialAddressLine1) {
        this.residentialAddressLine1 = residentialAddressLine1;
    }

    public void setResidentialAddressLine2(String residentialAddressLine2) {
        this.residentialAddressLine2 = residentialAddressLine2;
    }

    public void setResidentialCareOf(String residentialCareOf) {
        this.residentialCareOf = residentialCareOf;
    }

    public void setResidentialCountryName(String residentialCountryName) {
        this.residentialCountryName = residentialCountryName;
    }

    public void setResidentialPostTown(String residentialPostTown) {
        this.residentialPostTown = residentialPostTown;
    }

    public void setResidentialPoBox(String residentialPoBox) {
        this.residentialPoBox = residentialPoBox;
    }

    public void setResidentialPostalCode(String residentialPostalCode) {
        this.residentialPostalCode = residentialPostalCode;
    }

    public void setResidentialPremises(String residentialPremises) {
        this.residentialPremises = residentialPremises;
    }

    public void setResidentialRegion(String residentialRegion) {
        this.residentialRegion = residentialRegion;
    }

    // Setters for principal address fields
    public void setPrincipalPremises(String principalPremises) {
        this.principalPremises = principalPremises;
    }

    public void setPrincipalAddressLine1(String principalAddressLine1) {
        this.principalAddressLine1 = principalAddressLine1;
    }

    public void setPrincipalAddressLine2(String principalAddressLine2) {
        this.principalAddressLine2 = principalAddressLine2;
    }

    public void setPrincipalTown(String principalTown) {
        this.principalTown = principalTown;
    }

    public void setPrincipalRegion(String principalRegion) {
        this.principalRegion = principalRegion;
    }

    public void setPrincipalCountryName(String principalCountryName) {
        this.principalCountryName = principalCountryName;
    }

    public void setPrincipalPostalCode(String principalPostalCode) {
        this.principalPostalCode = principalPostalCode;
    }

    public void setPrincipalPoBox(String principalPoBox) {
        this.principalPoBox = principalPoBox;
    }

    public void setPrincipalCareOf(String principalCareOf) {
        this.principalCareOf = principalCareOf;
    }

    public Address getResidentialAddress() {
        residentialAddress = new Address();
        residentialAddress.setAddressLine1(residentialAddressLine1);
        residentialAddress.setAddressLine2(residentialAddressLine2);
        residentialAddress.setCountry(residentialCountryName);
        residentialAddress.setCareOf(residentialCareOf);
        residentialAddress.setLocality(residentialPostTown);
        residentialAddress.setPoBox(residentialPoBox);
        residentialAddress.setPostalCode(residentialPostalCode);
        residentialAddress.setPremises(residentialPremises);
        residentialAddress.setRegion(residentialRegion);
        return residentialAddress;
    }

    public Address getPrincipalAddress() {
        principalAddress = new Address();
        principalAddress.setAddressLine1(principalAddressLine1);
        principalAddress.setAddressLine2(principalAddressLine2);
        principalAddress.setCountry(principalCountryName);
        principalAddress.setCareOf(principalCareOf);
        principalAddress.setLocality(principalTown);
        principalAddress.setPoBox(principalPoBox);
        principalAddress.setPostalCode(principalPostalCode);
        principalAddress.setPremises(principalPremises);
        principalAddress.setRegion(principalRegion);
        return principalAddress;
    }

    public void setResidentialAddress(Address residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public void setPrincipalAddress(Address principalAddress) {
        this.principalAddress = principalAddress;
    }
}

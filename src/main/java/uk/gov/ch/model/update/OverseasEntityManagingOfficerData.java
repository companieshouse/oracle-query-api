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
    @Column(name = "premises")
    private String premises;
    @Column(name = "address_line_1")
    private String addressLine1;
    @Column(name = "address_line_2")
    private String addressLine2;
    @Column(name = "locality")
    private String locality;
    @Column(name = "region")
    private String region;
    @Column(name = "country_name")
    private String countryName;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "po_box")
    private String poBox;
    @Column(name = "care_of")
    private String careOf;

    @Transient
    @JsonProperty("residential_address")
    private Address residentialAddress;

    @Column(name = "DOB")
    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    @Column(name = "CONTACT_NAME_FULL")
    @JsonProperty("contact_name_full")
    private String contactNameFull;

    @Column(name = "CONTACT_EMAIL_ADDRESS")
    @JsonProperty("contact_email_address")
    private String contactEmailAddress;

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

    public void setPremises(String premises){
        this.premises = premises;
    }

    public void setAddressLine1(String addressLine1){
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2){
        this.addressLine2 = addressLine2;
    }

    public void setRegion(String region){
        this.region = region;
    }

    public void setLocality(String locality){
        this.locality = locality;
    }

    public void setCountryName(String countryName){
        this.countryName = countryName;
    }

    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }

    public void setPoBox(String poBox){
        this.poBox = poBox;
    }

    public void setCareOf(String careOf){
        this.careOf = careOf;
    }
    
    public Address getResidentialAddress() {
        residentialAddress = new Address();
        residentialAddress.setAddressLine1(addressLine1);
        residentialAddress.setAddressLine2(addressLine2);
        residentialAddress.setCountry(countryName);
        residentialAddress.setCareOf(careOf);
        residentialAddress.setLocality(locality);
        residentialAddress.setPoBox(poBox);
        residentialAddress.setPostalCode(postalCode);
        residentialAddress.setPremises(premises);
        residentialAddress.setRegion(region);
        return residentialAddress;
    }

    public void setResidentialAddress(Address residentialAddress) {
        this.residentialAddress = residentialAddress;
    }
}

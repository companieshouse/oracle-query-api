package uk.gov.ch.model.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.companieshouse.api.model.common.Address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class OverseasEntityBeneficialOwner {
    @Id
    @Column(name = "CORPORATE_BODY_APPOINTMENT_ID")
    @JsonProperty("id")
    private String id;

    @Column(name = "APPOINTMENT_DATE")
    @JsonProperty("date_became_registrable")
    private String dateBecameRegistrable;

    @Column(name = "URA_SAME_AS_SERVICE_IND")
    @JsonProperty("is_service_address_same_as_usual_address")
    private String isServiceAddressSameAsUsualAddress;

    @Column(name = "RESIDENTIAL_ADDRESS_LINE_1")
    private String residentialAddressLine1;

    @Column(name = "RESIDENTIAL_ADDRESS_LINE_2")
    private String residentialAddressLine2;

    @Column(name = "RESIDENTIAL_ADDRESS_CARE_OF")
    private String residentialAddressCareOf;

    @Column(name = "RESIDENTIAL_ADDRESS_COUNTRY_NAME")
    private String residentialAddressCountryName;

    @Column(name = "RESIDENTIAL_ADDRESS_POST_TOWN")
    private String residentialAddressPostTown;

    @Column(name = "RESIDENTIAL_ADDRESS_PO_BOX")
    private String residentialAddressPoBox;

    @Column(name = "RESIDENTIAL_ADDRESS_POST_CODE")
    private String residentialAddressPostCode;

    @Column(name = "RESIDENTIAL_ADDRESS_HOUSE_NAME_NUMBER")
    private String residentialAddressHouseNameNumber;

    @Column(name = "RESIDENTIAL_ADDRESS_REGION")
    private String residentialAddressRegion;

    @Column(name = "PRINCIPAL_ADDRESS_LINE_1")
    private String principalAddressLine1;

    @Column(name = "PRINCIPAL_ADDRESS_LINE_2")
    private String principalAddressLine2;

    @Column(name = "PRINCIPAL_ADDRESS_CARE_OF")
    private String principalAddressCareOf;

    @Column(name = "PRINCIPAL_ADDRESS_COUNTRY_NAME")
    private String principalAddressCountryName;

    @Column(name = "PRINCIPAL_ADDRESS_POST_TOWN")
    private String principalAddressPostTown;

    @Column(name = "PRINCIPAL_ADDRESS_PO_BOX")
    private String principalAddressPoBox;

    @Column(name = "PRINCIPAL_ADDRESS_POST_CODE")
    private String principalAddressPostCode;

    @Column(name = "PRINCIPAL_ADDRESS_HOUSE_NAME_NUMBER")
    private String principalAddressHouseNameNumber;

    @Column(name = "PRINCIPAL_ADDRESS_REGION")
    private String principalAddressRegion;

    @Column(name = "OFFICER_DATE_OF_BIRTH")
    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    @Transient
    @JsonProperty("usual_residential_address")
    private Address usualResidentialAddress;

    @Transient
    @JsonProperty("principal_address")
    private Address principalAddress;

    public Address getUsualResidentialAddress() {
        usualResidentialAddress = new Address();
        usualResidentialAddress.setAddressLine1(residentialAddressLine1);
        usualResidentialAddress.setAddressLine2(residentialAddressLine2);
        usualResidentialAddress.setCareOf(residentialAddressCareOf);
        usualResidentialAddress.setCountry(residentialAddressCountryName);
        usualResidentialAddress.setLocality(residentialAddressPostTown);
        usualResidentialAddress.setPoBox(residentialAddressPoBox);
        usualResidentialAddress.setPostalCode(residentialAddressPostCode);
        usualResidentialAddress.setRegion(residentialAddressRegion);
        usualResidentialAddress.setPremises(residentialAddressHouseNameNumber);
        return usualResidentialAddress;
    }

    public Address getPrincipalAddress() {
        principalAddress = new Address();
        principalAddress.setAddressLine1(principalAddressLine1);
        principalAddress.setAddressLine2(principalAddressLine2);
        principalAddress.setCareOf(principalAddressCareOf);
        principalAddress.setCountry(principalAddressCountryName);
        principalAddress.setLocality(principalAddressPostTown);
        principalAddress.setPoBox(principalAddressPoBox);
        principalAddress.setPostalCode(principalAddressPostCode);
        principalAddress.setRegion(principalAddressRegion);
        principalAddress.setPremises(principalAddressHouseNameNumber);
        return principalAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateBecameRegistrable() {
        return dateBecameRegistrable;
    }

    public void setDateBecameRegistrable(String dateBecameRegistrable) {
        this.dateBecameRegistrable = dateBecameRegistrable;
    }

    public String getIsServiceAddressSameAsUsualAddress() {
        return isServiceAddressSameAsUsualAddress;
    }

    public void setIsServiceAddressSameAsUsualAddress(String isServiceAddressSameAsUsualAddress) {
        this.isServiceAddressSameAsUsualAddress = isServiceAddressSameAsUsualAddress;
    }

    public void setResidentialAddressLine1(String residentialAddressLine1) {
        this.residentialAddressLine1 = residentialAddressLine1;
    }

    public void setResidentialAddressLine2(String residentialAddressLine2) {
        this.residentialAddressLine2 = residentialAddressLine2;
    }

    public void setResidentialAddressCareOf(String residentialAddressCareOf) {
        this.residentialAddressCareOf = residentialAddressCareOf;
    }

    public void setResidentialAddressCountryName(String residentialAddressCountryName) {
        this.residentialAddressCountryName = residentialAddressCountryName;
    }

    public void setResidentialAddressPostTown(String residentialAddressPostTown) {
        this.residentialAddressPostTown = residentialAddressPostTown;
    }

    public void setResidentialAddressPoBox(String residentialAddressPoBox) {
        this.residentialAddressPoBox = residentialAddressPoBox;
    }

    public void setResidentialAddressPostCode(String residentialAddressPostCode) {
        this.residentialAddressPostCode = residentialAddressPostCode;
    }

    public void setResidentialAddressHouseNameNumber(String residentialAddressHouseNameNumber) {
        this.residentialAddressHouseNameNumber = residentialAddressHouseNameNumber;
    }

    public void setResidentialAddressRegion(String residentialAddressRegion) {
        this.residentialAddressRegion = residentialAddressRegion;
    }

    public void setPrincipalAddressLine1(String principalAddressLine1) {
        this.principalAddressLine1 = principalAddressLine1;
    }

    public void setPrincipalAddressLine2(String principalAddressLine2) {
        this.principalAddressLine2 = principalAddressLine2;
    }

    public void setPrincipalAddressCareOf(String principalAddressCareOf) {
        this.principalAddressCareOf = principalAddressCareOf;
    }

    public void setPrincipalAddressCountryName(String principalAddressCountryName) {
        this.principalAddressCountryName = principalAddressCountryName;
    }

    public void setPrincipalAddressPostTown(String principalAddressPostTown) {
        this.principalAddressPostTown = principalAddressPostTown;
    }

    public void setPrincipalAddressPoBox(String principalAddressPoBox) {
        this.principalAddressPoBox = principalAddressPoBox;
    }

    public void setPrincipalAddressPostCode(String principalAddressPostCode) {
        this.principalAddressPostCode = principalAddressPostCode;
    }

    public void setPrincipalAddressHouseNameNumber(String principalAddressHouseNameNumber) {
        this.principalAddressHouseNameNumber = principalAddressHouseNameNumber;
    }

    public void setPrincipalAddressRegion(String principalAddressRegion) {
        this.principalAddressRegion = principalAddressRegion;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}

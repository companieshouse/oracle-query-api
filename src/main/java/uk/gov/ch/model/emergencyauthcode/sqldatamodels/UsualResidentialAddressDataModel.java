package uk.gov.ch.model.emergencyauthcode.sqldatamodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUAL_RESIDENTIAL_ADDRESS")
public class UsualResidentialAddressDataModel {

    @Id
    @Column(name = "USUAL_RESIDENTIAL_ADDRESS_ID")
    private Long usualResidentialAddressId;

    @Column(name = "PO_BOX")
    private String poBox;

    @Column(name = "HOUSE_NAME_NUMBER")
    private String premises;

    @Column(name = "ADDRESS_LINE_1")
    private String addressLine1;

    @Column(name = "AREA")
    private String addressLine2;

    @Column(name = "POST_TOWN")
    private String locality;

    @Column(name = "REGION")
    private String region;

    @Column(name = "COUNTRY_NAME")
    private String country;

    @Column(name = "POST_CODE")
    private String postCode;

    public Long getUsualResidentialAddressId() {
        return usualResidentialAddressId;
    }

    public void setUsualResidentialAddressId(Long usualResidentialAddressId) {
        this.usualResidentialAddressId = usualResidentialAddressId;
    }

    public String getPoBox() {
        return poBox;
    }

    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    public String getPremises() {
        return premises;
    }

    public void setPremises(String premises) {
        this.premises = premises;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}

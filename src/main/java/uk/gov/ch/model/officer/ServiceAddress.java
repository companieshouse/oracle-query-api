package uk.gov.ch.model.officer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceAddress {

    @JsonProperty("address_line_1")
    private String addressLine1;

    @JsonProperty("address_line_2")
    private String addressLine2;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("care_of_name")
    private String careOfName;

    @JsonProperty("country")
    private String country;

    @JsonProperty("locality")
    private String locality;

    @JsonProperty("po_box")
    private String poBox;

    @JsonProperty("premises")
    private String premises;

    @JsonProperty("region")
    private String region;

    @JsonProperty("usual_country_of_residence")
    private String usualCountryOfResidence;

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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCareOfName() {
        return careOfName;
    }

    public void setCareOfName(String careOfName) {
        this.careOfName = careOfName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getUsualCountryOfResidence() {
        return usualCountryOfResidence;
    }

    public void setUsualCountryOfResidence(String usualCountryOfResidence) {
        this.usualCountryOfResidence = usualCountryOfResidence;
    }
}

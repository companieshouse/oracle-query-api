package uk.gov.ch.model.officer.active;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class ActiveOfficerDetails {

    @JsonProperty("service_address_line_1")
    private String serviceAddressLine1;

    @JsonProperty("service_address_post_town")
    private String serviceAddressPostTown;

    @JsonProperty("service_address_post_code")
    private String serviceAddressPostCode;

    @JsonProperty("address_line_1")
    private String residentialAddressLine1;

    @JsonProperty("post_town")
    private String residentialAddressPostTown;

    @JsonProperty("post_code")
    private String residentialAddressPostCode;

    @JsonProperty("officer_forename_1")
    private String foreName1;

    @JsonProperty("officer_forename_2")
    private String foreName2;

    @JsonProperty("officer_surname")
    private String surname;

    @JsonProperty("occupation_desc")
    private String occupation;

    @JsonProperty("officer_nationality")
    private String nationality;

    @JsonProperty("officer_date_of_birth")
    private String dateOfBirth;

    @JsonProperty("secure_director_service_ind")
    private String secureIndicator;

    public String getServiceAddressLine1() {
        return serviceAddressLine1;
    }

    public void setServiceAddressLine1(String serviceAddressLine1) {
        this.serviceAddressLine1 = serviceAddressLine1;
    }

    public String getServiceAddressPostTown() {
        return serviceAddressPostTown;
    }

    public void setServiceAddressPostTown(String serviceAddressPostTown) {
        this.serviceAddressPostTown = serviceAddressPostTown;
    }

    public String getServiceAddressPostCode() {
        return serviceAddressPostCode;
    }

    public void setServiceAddressPostCode(String serviceAddressPostCode) {
        this.serviceAddressPostCode = serviceAddressPostCode;
    }

    public String getResidentialAddressLine1() {
        return residentialAddressLine1;
    }

    public void setResidentialAddressLine1(String residentialAddressLine1) {
        this.residentialAddressLine1 = residentialAddressLine1;
    }

    public String getResidentialAddressPostTown() {
        return residentialAddressPostTown;
    }

    public void setResidentialAddressPostTown(String residentialAddressPostTown) {
        this.residentialAddressPostTown = residentialAddressPostTown;
    }

    public String getResidentialAddressPostCode() {
        return residentialAddressPostCode;
    }

    public void setResidentialAddressPostCode(String residentialAddressPostCode) {
        this.residentialAddressPostCode = residentialAddressPostCode;
    }

    public String getForeName1() {
        return foreName1;
    }

    public void setForeName1(String foreName1) {
        this.foreName1 = foreName1;
    }

    public String getForeName2() {
        return foreName2;
    }

    public void setForeName2(String foreName2) {
        this.foreName2 = foreName2;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSecureIndicator() {
        return secureIndicator;
    }

    public void setSecureIndicator(String secureIndicator) {
        this.secureIndicator = secureIndicator;
    }

}

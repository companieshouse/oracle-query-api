package uk.gov.ch.model.emergencyauthcode.jsondatamodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class CorporateBodyAppointment {

    @JsonProperty("id")
    private String id;

    @JsonProperty("forename")
    private String forename;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("officer_role")
    private String officerRole;

    @JsonProperty("date_of_birth")
    private CorporateBodyAppointmentDateOfBirth dateOfBirth;

    @JsonProperty("appointed_on")
    private LocalDate appointedOn;

    @JsonProperty("nationality")
    private String nationality;

    @JsonProperty("country_of_residence")
    private String countryOfResidence;

    @JsonProperty("occupation")
    private String occupation;

    @JsonProperty("usual_residential_address")
    private CorporateBodyAppointmentURA usualResidentialAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOfficerRole() {
        return officerRole;
    }

    public void setOfficerRole(String officerRole) {
        this.officerRole = officerRole;
    }

    public CorporateBodyAppointmentDateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(CorporateBodyAppointmentDateOfBirth dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getAppointedOn() {
        return appointedOn;
    }

    public void setAppointedOn(LocalDate appointedOn) {
        this.appointedOn = appointedOn;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public CorporateBodyAppointmentURA getUsualResidentialAddress() {
        return usualResidentialAddress;
    }

    public void setUsualResidentialAddress(CorporateBodyAppointmentURA usualResidentialAddress) {
        this.usualResidentialAddress = usualResidentialAddress;
    }
}

package uk.gov.ch.model.officer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfficerDataModel {

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("internal_id")
    private String internalId;

    @JsonProperty("appointment_date")
    private String appointmentDate;

    @JsonProperty("resignation_date")
    private String resignationDate;

    @JsonProperty("appt_date_prefix")
    private String apptDatePrefix;

    @JsonProperty("corporate_ind")
    private String corporateInd;

    @JsonProperty("forename")
    private String forename;

    @JsonProperty("middle_name")
    private String middleName;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("title")
    private String title;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    @JsonProperty("service_address_same_as_registered_office")
    private String serviceAddressSameAsRegisteredOffice;

    @JsonProperty("nationality")
    private String nationality;

    @JsonProperty("occupation")
    private String occupation;

    @JsonProperty("officer_id")
    private String officerId;

    @JsonProperty("officer_detail_id")
    private String officerDetailId;

    @JsonProperty("service_address")
    private ServiceAddress serviceAddress;

    @JsonProperty("identification")
    private Identification identification;

    @JsonProperty("previous_name_array")
    private List<PreviousNameModel> previousNameArray;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getResignationDate() {
        return resignationDate;
    }

    public void setResignationDate(String resignationDate) {
        this.resignationDate = resignationDate;
    }

    public String getApptDatePrefix() {
        return apptDatePrefix;
    }

    public void setApptDatePrefix(String apptDatePrefix) {
        this.apptDatePrefix = apptDatePrefix;
    }

    public String getCorporateInd() {
        return corporateInd;
    }

    public void setCorporateInd(String corporateInd) {
        this.corporateInd = corporateInd;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getServiceAddressSameAsRegisteredOffice() {
        return serviceAddressSameAsRegisteredOffice;
    }

    public void setServiceAddressSameAsRegisteredOffice(
            String serviceAddressSameAsRegisteredOffice) {
        this.serviceAddressSameAsRegisteredOffice = serviceAddressSameAsRegisteredOffice;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOfficerId() {
        return officerId;
    }

    public void setOfficerId(String officerId) {
        this.officerId = officerId;
    }

    public String getOfficerDetailId() {
        return officerDetailId;
    }

    public void setOfficerDetailId(String officerDetailId) {
        this.officerDetailId = officerDetailId;
    }

    public ServiceAddress getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(ServiceAddress serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }

    public List<PreviousNameModel> getPreviousNameArray() {
        return previousNameArray;
    }

    public void setPreviousNameArray(List<PreviousNameModel> previousNameArray) {
        this.previousNameArray = previousNameArray;
    }
}

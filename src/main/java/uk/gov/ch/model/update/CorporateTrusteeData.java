package uk.gov.ch.model.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CorporateTrusteeData {

    @Column(name = "TRUSTEE_ID")
    @JsonProperty("trusteeId")
    private Long trusteeId;

    @Column(name = "TRUSTEE_NAME")
    @JsonProperty("trusteeName")
    private String trusteeName;

    @Column(name = "REGISTER_LOCATION")
    @JsonProperty("registerLocation")
    private String registerLocation;

    @Column(name = "REGISTRATION_NUMBER")
    @JsonProperty("registrationNumber")
    private String registrationNumber;

    @Column(name = "LAW_GOVERNED")
    @JsonProperty("lawGoverned")
    private String lawGoverned;

    @Column(name = "LEGAL_FORM")
    @JsonProperty("legalForm")
    private String legalForm;

    @Column(name = "COUNTRY")
    @JsonProperty("country")
    private String country;

    @Column(name = "MODIFICATION_ID")
    @JsonProperty("modificationId")
    private Long modificationId;

    @Column(name = "ON_REGISTER_IN_COUNTRY_FORMED")
    @JsonProperty("onRegisterInCountryFormed")
    private Boolean onRegisterInCountryFormed;

    @Column(name = "SERVICE_SAME_AS_ROA_IND")
    @JsonProperty("serviceSameAsRoaInd")
    private Boolean serviceSameAsRoaInd;


    @Column(name = "CORPORATE_IND")
    @JsonProperty("corporateInd")
    private String corporateInd;

    @Column(name = "TRUSTEE_TYPE_ID")
    @JsonProperty("trusteeTypeId")
    private Long trusteeTypeId;

    @Column(name = "APPOINTMENT_DATE")
    @JsonProperty("appointmentDate")
    private LocalDate appointmentDate;

    @Column(name = "CEASED_DATE")
    @JsonProperty("ceasedDate")
    private LocalDate ceasedDate;

    public Long getTrusteeId() {
        return trusteeId;
    }

    public void setTrusteeId(Long trusteeId) {
        this.trusteeId = trusteeId;
    }

    public String getTrusteeName() {
        return trusteeName;
    }

    public void setTrusteeName(String trusteeName) {
        this.trusteeName = trusteeName;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getModificationId() {
        return modificationId;
    }

    public void setModificationId(Long modificationId) {
        this.modificationId = modificationId;
    }

    public Boolean getOnRegisterInCountryFormed() {
        return onRegisterInCountryFormed;
    }

    public void setOnRegisterInCountryFormed(Boolean onRegisterInCountryFormed) {
        this.onRegisterInCountryFormed = onRegisterInCountryFormed;
    }

    public Boolean getServiceSameAsRoaInd() {
        return serviceSameAsRoaInd;
    }

    public void setServiceSameAsRoaInd(Boolean serviceSameAsRoaInd) {
        this.serviceSameAsRoaInd = serviceSameAsRoaInd;
    }

    public String getCorporateInd() {
        return corporateInd;
    }

    public void setCorporateInd(String corporateInd) {
        this.corporateInd = corporateInd;
    }

    public Long getTrusteeTypeId() {
        return trusteeTypeId;
    }

    public void setTrusteeTypeId(Long trusteeTypeId) {
        this.trusteeTypeId = trusteeTypeId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalDate getCeasedDate() {
        return ceasedDate;
    }

    public void setCeasedDate(LocalDate ceasedDate) {
        this.ceasedDate = ceasedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CorporateTrusteeData that = (CorporateTrusteeData) o;
        return Objects.equals(trusteeId, that.trusteeId) && Objects.equals(
                trusteeName, that.trusteeName) && Objects.equals(registerLocation,
                that.registerLocation) && Objects.equals(registrationNumber,
                that.registrationNumber) && Objects.equals(lawGoverned, that.lawGoverned)
                && Objects.equals(legalForm, that.legalForm) && Objects.equals(
                country, that.country) && Objects.equals(modificationId, that.modificationId)
                && Objects.equals(onRegisterInCountryFormed, that.onRegisterInCountryFormed)
                && Objects.equals(serviceSameAsRoaInd, that.serviceSameAsRoaInd)
                && Objects.equals(corporateInd, that.corporateInd)
                && Objects.equals(trusteeTypeId, that.trusteeTypeId)
                && Objects.equals(appointmentDate, that.appointmentDate)
                && Objects.equals(ceasedDate, that.ceasedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trusteeId, trusteeName, registerLocation, registrationNumber,
                lawGoverned,
                legalForm, country, modificationId, onRegisterInCountryFormed, serviceSameAsRoaInd,
                corporateInd, trusteeTypeId, appointmentDate, ceasedDate);
    }
}

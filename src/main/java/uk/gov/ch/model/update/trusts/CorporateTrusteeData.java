package uk.gov.ch.model.update.trusts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CorporateTrusteeData {

    @Id
    @Column(name = "TRUSTEE_ID")
    @JsonProperty("trusteeId")
    private String trusteeId;

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

    @Column(name="COUNTRY")
    @JsonProperty("country")
    private String country;

    @Column(name = "ON_REGISTER_IN_COUNTRY_FORMED")
    @JsonProperty("onRegisterInCountryFormed")
    private Boolean onRegisterInCountryFormed;


    @Column(name = "CORPORATE_IND")
    @JsonProperty("corporateIndicator")
    private String corporateInd;

    @Column(name = "TRUSTEE_TYPE_ID")
    @JsonProperty("trusteeTypeId")
    private Long trusteeTypeId;

    @Column(name = "APPOINTMENT_DATE")
    @JsonProperty("appointmentDate")
    private String appointmentDate;

    @Column(name = "CEASED_DATE")
    @JsonProperty("ceasedDate")
    private String ceasedDate;

    public String getTrusteeId() {
        return trusteeId;
    }

    public void setTrusteeId(String trusteeId) {
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

    public Boolean getOnRegisterInCountryFormed() {
        return onRegisterInCountryFormed;
    }

    public void setOnRegisterInCountryFormed(Boolean onRegisterInCountryFormed) {
        this.onRegisterInCountryFormed = onRegisterInCountryFormed;
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

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getCeasedDate() {
        return ceasedDate;
    }

    public void setCeasedDate(String ceasedDate) {
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
                country, that.country) && Objects.equals(onRegisterInCountryFormed,
                that.onRegisterInCountryFormed) && Objects.equals(corporateInd,
                that.corporateInd) && Objects.equals(trusteeTypeId, that.trusteeTypeId)
                && Objects.equals(appointmentDate, that.appointmentDate)
                && Objects.equals(ceasedDate, that.ceasedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trusteeId, trusteeName, registerLocation, registrationNumber,
                lawGoverned,
                legalForm, country, onRegisterInCountryFormed, corporateInd, trusteeTypeId,
                appointmentDate, ceasedDate);
    }
}

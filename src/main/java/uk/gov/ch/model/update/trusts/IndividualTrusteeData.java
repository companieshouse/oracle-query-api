package uk.gov.ch.model.update.trusts;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class IndividualTrusteeData {
    @Id
    @Column(name = "trustee_id")
    @JsonProperty("trusteeId")
    private Long trusteeId;

    @Column(name = "trustee_forename_1")
    @JsonProperty("trusteeForename1")
    private String trusteeForename1;

    @Column(name = "trustee_forename_2")
    @JsonProperty("trusteeForename2")
    private String trusteeForename2;

    @Column(name = "trustee_surname")
    @JsonProperty("trusteeSurname")
    private String trusteeSurname;

    @Column(name = "date_of_birth")
    @JsonProperty("dateOfBirth")
    private String dateOfBirth;

    @Column(name = "nationality")
    @JsonProperty("nationality")
    private String nationality;

    @Column(name = "corporate_ind")
    @JsonProperty("corporateIndicator")
    private String corporateIndicator;

    @Column(name = "trustee_type_id")
    @JsonProperty("trusteeTypeId")
    private Long trusteeTypeId;

    @Column(name = "appointment_date")
    @JsonProperty("appointmentDate")
    private String appointmentDate;

    @Column(name = "ceased_date")
    @JsonProperty("ceasedDate")
    private String ceasedDate;

    public Long getTrusteeId() {
        return trusteeId;
    }

    public void setTrusteeId(Long trusteeId) {
        this.trusteeId = trusteeId;
    }

    public String getTrusteeForename1() {
        return trusteeForename1;
    }

    public void setTrusteeForename1(String trusteeForename1) {
        this.trusteeForename1 = trusteeForename1;
    }

    public String getTrusteeForename2() {
        return trusteeForename2;
    }

    public void setTrusteeForename2(String trusteeForename2) {
        this.trusteeForename2 = trusteeForename2;
    }

    public String getTrusteeSurname() {
        return trusteeSurname;
    }

    public void setTrusteeSurname(String trusteeSurname) {
        this.trusteeSurname = trusteeSurname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCorporateIndicator() {
        return corporateIndicator;
    }

    public void setCorporateIndicator(String corporateIndicator) {
        this.corporateIndicator = corporateIndicator;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndividualTrusteeData that = (IndividualTrusteeData) o;
        return Objects.equals(trusteeId, that.trusteeId)
                && Objects.equals(trusteeForename1, that.trusteeForename1)
                && Objects.equals(trusteeForename2, that.trusteeForename2)
                && Objects.equals(trusteeSurname, that.trusteeSurname)
                && Objects.equals(dateOfBirth, that.dateOfBirth)
                && Objects.equals(nationality, that.nationality)
                && Objects.equals(corporateIndicator, that.corporateIndicator)
                && Objects.equals(trusteeTypeId, that.trusteeTypeId)
                && Objects.equals(appointmentDate, that.appointmentDate)
                && Objects.equals(ceasedDate, that.ceasedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trusteeId, trusteeForename1, trusteeForename2, trusteeSurname, dateOfBirth, nationality,
                corporateIndicator, trusteeTypeId, appointmentDate, ceasedDate);
    }
}

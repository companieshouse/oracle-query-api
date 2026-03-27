package uk.gov.ch.model.officer.active;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class ActiveOfficerDetails extends OfficerBase {

    @Column(name = "corporate")
    @JsonProperty("is_corporate")
    private Boolean corporate;
    @Column(name = "role")
    private String role;
    @Column(name = "place_registered")
    @JsonProperty("place_registered")
    private String placeRegistered;
    @Column(name = "registration_number")
    @JsonProperty("registration_number")
    private String registrationNumber;
    @Column(name = "law_governed")
    @JsonProperty("law_governed")
    private String lawGoverned;
    @Column(name = "legal_form")
    @JsonProperty("legal_form")
    private String legalForm;
    @Column(name = "identification_type")
    @JsonProperty("identification_type")
    private String identificationType;

    public boolean isCorporate() {
        return corporate;
    }

    public void setCorporate(boolean corporate) {
        this.corporate = corporate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPlaceRegistered() {
        return placeRegistered;
    }

    public void setPlaceRegistered(String placeRegistered) {
        this.placeRegistered = placeRegistered;
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

    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

}

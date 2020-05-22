package uk.gov.ch.model.emergency_auth_code.sqlDataModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "OFFICER_DETAIL")
public class OfficerDetailDataModel {

    @Id
    @Column(name = "OFFICER_DETAIL_ID")
    private Long officerId;

    @Column(name = "OFFICER_FORENAME_1")
    private String forename1;

    @Column(name = "OFFICER_FORENAME_2")
    private String forename2;

    @Column(name = "OFFICER_SURNAME")
    private String surname;

    @Column(name = "OFFICER_DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Column(name = "OFFICER_NATIONALITY")
    private String officerNationality;

    @Column(name = "USUAL_RESIDENTIAL_COUNTRY")
    private String usualResidentialCountry;

    @OneToOne
    @JoinColumn(name = "USUAL_RESIDENTIAL_ADDRESS_ID")
    private UsualResidentialAddressDataModel usualResidentialAddress;

    public Long getOfficerId() {
        return officerId;
    }

    public void setOfficerId(Long officerId) {
        this.officerId = officerId;
    }

    public String getForename1() {
        return forename1;
    }

    public void setForename1(String forename1) {
        this.forename1 = forename1;
    }

    public String getForename2() {
        return forename2;
    }

    public void setForename2(String forename2) {
        this.forename2 = forename2;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getOfficerNationality() {
        return officerNationality;
    }

    public void setOfficerNationality(String officerNationality) {
        this.officerNationality = officerNationality;
    }

    public String getUsualResidentialCountry() {
        return usualResidentialCountry;
    }

    public void setUsualResidentialCountry(String usualResidentialCountry) {
        this.usualResidentialCountry = usualResidentialCountry;
    }

    public UsualResidentialAddressDataModel getUsualResidentialAddress() {
        return usualResidentialAddress;
    }

    public void setUsualResidentialAddress(UsualResidentialAddressDataModel usualResidentialAddress) {
        this.usualResidentialAddress = usualResidentialAddress;
    }
}

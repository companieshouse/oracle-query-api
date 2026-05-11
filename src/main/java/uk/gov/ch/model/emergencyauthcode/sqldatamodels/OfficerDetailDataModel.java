package uk.gov.ch.model.emergencyauthcode.sqldatamodels;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import uk.gov.ch.model.common.AltNameBase;

@Entity
@Table(name = "OFFICER_DETAIL")
@AttributeOverride(name = "forename1", column = @Column(name = "OFFICER_FORENAME_1"))
@AttributeOverride(name = "forename2", column = @Column(name = "OFFICER_FORENAME_2"))
@AttributeOverride(name = "surname", column = @Column(name = "OFFICER_SURNAME"))
public class OfficerDetailDataModel extends AltNameBase {

    @Id
    @Column(name = "OFFICER_DETAIL_ID")
    private Long officerId;

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

    public void setUsualResidentialAddress(
            UsualResidentialAddressDataModel usualResidentialAddress) {
        this.usualResidentialAddress = usualResidentialAddress;
    }
}

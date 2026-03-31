package uk.gov.ch.model.update.trusts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import java.util.Objects;
import uk.gov.companieshouse.api.model.common.Address;

@Entity
@JsonPropertyOrder({
    "trusteeId",
    "trusteeForename1",
    "trusteeForename2",
    "trusteeSurname",
    "dateOfBirth",
    "nationality",
    "corporateIndicator",
    "trusteeTypeId",
    "appointmentDate",
    "ceasedDate",
    "serviceAddress",
    "usualResidentialAddress"
})
public class IndividualTrusteeData extends TrusteeDataBase {

    @Id
    @Column(name = "TRUSTEE_ID")
    @JsonProperty("trusteeId")
    private String trusteeId;

    @Column(name = "TRUSTEE_FORENAME_1")
    @JsonProperty("trusteeForename1")
    private String trusteeForename1;

    @Column(name = "TRUSTEE_FORENAME_2")
    @JsonProperty("trusteeForename2")
    private String trusteeForename2;

    @Column(name = "TRUSTEE_SURNAME")
    @JsonProperty("trusteeSurname")
    private String trusteeSurname;

    @Column(name = "DATE_OF_BIRTH")
    @JsonProperty("dateOfBirth")
    private String dateOfBirth;

    @Column(name = "NATIONALITY")
    @JsonProperty("nationality")
    private String nationality;

    @Column(name = "CORPORATE_IND")
    @JsonProperty("corporateIndicator")
    private String corporateIndicator;

    @Column(name = "RES_ADDR_NAME_NUMBER")
    private String residentialAddressHouseNameNumber;

    @Column(name = "RES_ADDR_STREET")
    private String residentialAddressStreet;

    @Column(name = "RES_ADDR_AREA")
    private String residentialAddressArea;

    @Column(name = "RES_ADDR_POST_TOWN")
    private String residentialAddressPostTown;

    @Column(name = "RES_ADDR_REGION")
    private String residentialAddressRegion;

    @Column(name = "RES_ADDR_COUNTRY_NAME")
    private String residentialAddressCountryName;

    @Column(name = "RES_ADDR_POST_CODE")
    private String residentialAddressPostCode;

    @Transient
    @JsonProperty("usualResidentialAddress")
    private Address usualResidentialAddress;

    public Address getUsualResidentialAddress() {
        this.usualResidentialAddress = new Address();
        this.usualResidentialAddress.setPremises(residentialAddressHouseNameNumber);
        this.usualResidentialAddress.setAddressLine1(residentialAddressStreet);
        this.usualResidentialAddress.setAddressLine2(residentialAddressArea);
        this.usualResidentialAddress.setLocality(residentialAddressPostTown);
        this.usualResidentialAddress.setRegion(residentialAddressRegion);
        this.usualResidentialAddress.setCountry(residentialAddressCountryName);
        this.usualResidentialAddress.setPostalCode(residentialAddressPostCode);
        return this.usualResidentialAddress;
    }

    public void setUsualResidentialAddress(Address usualResidentialAddress) {
        if (usualResidentialAddress != null) {
            this.residentialAddressHouseNameNumber = usualResidentialAddress.getPremises();
            this.residentialAddressStreet = usualResidentialAddress.getAddressLine1();
            this.residentialAddressArea = usualResidentialAddress.getAddressLine2();
            this.residentialAddressPostTown = usualResidentialAddress.getLocality();
            this.residentialAddressRegion = usualResidentialAddress.getRegion();
            this.residentialAddressCountryName = usualResidentialAddress.getCountry();
            this.residentialAddressPostCode = usualResidentialAddress.getPostalCode();
        }
    }


    public String getTrusteeId() {
        return trusteeId;
    }

    public void setTrusteeId(String trusteeId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IndividualTrusteeData that = (IndividualTrusteeData) o;
	return Objects.equals(trusteeId, that.trusteeId)
		&& Objects.equals(trusteeForename1,that.trusteeForename1)
		&& Objects.equals(trusteeForename2,that.trusteeForename2)
		&& Objects.equals(trusteeSurname,that.trusteeSurname)
		&& Objects.equals(dateOfBirth,that.dateOfBirth)
		&& Objects.equals(nationality,that.nationality)
		&& Objects.equals(corporateIndicator,that.corporateIndicator)
        && Objects.equals(trusteeTypeId,that.trusteeTypeId)
        && Objects.equals(appointmentDate,that.appointmentDate)
        && Objects.equals(ceasedDate,that.ceasedDate)
	    && Objects.equals(serviceAddressHouseNameNumber,that.serviceAddressHouseNameNumber)
	    && Objects.equals(serviceAddressStreet,that.serviceAddressStreet)
	    && Objects.equals(serviceAddressArea,that.serviceAddressArea)
	    && Objects.equals(serviceAddressPostTown,that.serviceAddressPostTown)
	    && Objects.equals(serviceAddressRegion,that.serviceAddressRegion)
	    && Objects.equals(serviceAddressCountryName,that.serviceAddressCountryName)
	    && Objects.equals(serviceAddressPostCode,that.serviceAddressPostCode)
		&& Objects.equals(residentialAddressHouseNameNumber,that.residentialAddressHouseNameNumber)
		&& Objects.equals(residentialAddressStreet,that.residentialAddressStreet)
		&& Objects.equals(residentialAddressArea,that.residentialAddressArea)
		&& Objects.equals(residentialAddressPostTown,that.residentialAddressPostTown)
		&& Objects.equals(residentialAddressRegion,that.residentialAddressRegion)
		&& Objects.equals(residentialAddressCountryName,that.residentialAddressCountryName)
		&& Objects.equals(residentialAddressPostCode,that.residentialAddressPostCode);
    }

    @Override
    public int hashCode() {
	    return Objects.hash(trusteeId,
			    trusteeForename1,
			    trusteeForename2,
			    trusteeSurname,
			    dateOfBirth,
			    nationality,
			    corporateIndicator,
			    trusteeTypeId,
			    appointmentDate,
                ceasedDate,
                serviceAddressHouseNameNumber,
                serviceAddressStreet,
                serviceAddressArea,
                serviceAddressPostTown,
                serviceAddressRegion,
                serviceAddressCountryName,
                serviceAddressPostCode,
			    residentialAddressHouseNameNumber,
			    residentialAddressStreet,
			    residentialAddressArea,
			    residentialAddressPostTown,
			    residentialAddressRegion,
			    residentialAddressCountryName,
			    residentialAddressPostCode);
    }

}

package uk.gov.ch.model.update.trusts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import uk.gov.companieshouse.api.model.common.Address;

@Entity
public class IndividualTrusteeData {

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

    @Column(name = "TRUSTEE_TYPE_ID")
    @JsonProperty("trusteeTypeId")
    private String trusteeTypeId;

    @Column(name = "APPOINTMENT_DATE")
    @JsonProperty("appointmentDate")
    private String appointmentDate;

    @Column(name = "CEASED_DATE")
    @JsonProperty("ceasedDate")
    private String ceasedDate;

    @Column(name = "SERV_ADDR_NAME_NUMBER")
    private String serviceAddressHouseNameNumber;

    @Column(name = "SERV_ADDR_STREET")
    private String serviceAddressStreet;

    @Column(name = "SERV_ADDR_AREA")
    private String serviceAddressArea;

    @Column(name = "SERV_ADDR_POST_TOWN")
    private String serviceAddressPostTown;

    @Column(name = "SERV_ADDR_REGION")
    private String serviceAddressRegion;

    @Column(name = "SERV_ADDR_COUNTRY_NAME")
    private String serviceAddressCountryName;

    @Column(name = "SERV_ADDR_POST_CODE")
    private String serviceAddressPostCode;

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
    @JsonProperty("serviceAddress")
    private Address serviceAddress;

    @Transient
    @JsonProperty("residentialAddress")
    private Address residentialAddress;


    public Address getServiceAddress() {
        this.serviceAddress = new Address();
        this.serviceAddress.setPremises(serviceAddressHouseNameNumber);
        this.serviceAddress.setAddressLine1(serviceAddressStreet);
        this.serviceAddress.setAddressLine2(serviceAddressArea);
        this.serviceAddress.setLocality(serviceAddressPostTown);
        this.serviceAddress.setRegion(serviceAddressRegion);
        this.serviceAddress.setCountry(serviceAddressCountryName);
        this.serviceAddress.setPostalCode(serviceAddressPostCode);
        return this.serviceAddress;
    }

    public void setServiceAddress(Address serviceAddress) {
        if (serviceAddress != null) {
            this.serviceAddressHouseNameNumber = serviceAddress.getPremises();
            this.serviceAddressStreet = serviceAddress.getAddressLine1();
            this.serviceAddressArea = serviceAddress.getAddressLine2();
            this.serviceAddressPostTown = serviceAddress.getLocality();
            this.serviceAddressRegion = serviceAddress.getRegion();
            this.serviceAddressCountryName = serviceAddress.getCountry();
            this.serviceAddressPostCode = serviceAddress.getPostalCode();
        }
    }

    public Address getResidentialAddress() {
        this.residentialAddress = new Address();
        this.residentialAddress.setPremises(residentialAddressHouseNameNumber);
        this.residentialAddress.setAddressLine1(residentialAddressStreet);
        this.residentialAddress.setAddressLine2(residentialAddressArea);
        this.residentialAddress.setLocality(residentialAddressPostTown);
        this.residentialAddress.setRegion(residentialAddressRegion);
        this.residentialAddress.setCountry(residentialAddressCountryName);
        this.residentialAddress.setPostalCode(residentialAddressPostCode);
        return this.residentialAddress;
    }

    public void setResidentialAddress(Address residentialAddress) {
        if (residentialAddress != null) {
            this.residentialAddressHouseNameNumber = residentialAddress.getPremises();
            this.residentialAddressStreet = residentialAddress.getAddressLine1();
            this.residentialAddressArea = residentialAddress.getAddressLine2();
            this.residentialAddressPostTown = residentialAddress.getLocality();
            this.residentialAddressRegion = residentialAddress.getRegion();
            this.residentialAddressCountryName = residentialAddress.getCountry();
            this.residentialAddressPostCode = residentialAddress.getPostalCode();
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

    public String getTrusteeTypeId() {
        return trusteeTypeId;
    }

    public void setTrusteeTypeId(String trusteeTypeId) {
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
        IndividualTrusteeData that = (IndividualTrusteeData) o;
        return Objects.equals(trusteeId, that.trusteeId) && Objects.equals(trusteeForename1,
                that.trusteeForename1) && Objects.equals(trusteeForename2, that.trusteeForename2)
                && Objects.equals(trusteeSurname, that.trusteeSurname) && Objects.equals(
                dateOfBirth, that.dateOfBirth) && Objects.equals(nationality, that.nationality)
                && Objects.equals(corporateIndicator, that.corporateIndicator) && Objects.equals(
                trusteeTypeId, that.trusteeTypeId) && Objects.equals(appointmentDate,
                that.appointmentDate) && Objects.equals(ceasedDate, that.ceasedDate)
                && Objects.equals(serviceAddressHouseNameNumber, that.serviceAddressHouseNameNumber)
                && Objects.equals(serviceAddressStreet, that.serviceAddressStreet)
                && Objects.equals(serviceAddressArea, that.serviceAddressArea) && Objects.equals(
                serviceAddressPostTown, that.serviceAddressPostTown) && Objects.equals(
                serviceAddressRegion, that.serviceAddressRegion) && Objects.equals(
                serviceAddressCountryName, that.serviceAddressCountryName) && Objects.equals(
                serviceAddressPostCode, that.serviceAddressPostCode) && Objects.equals(
                residentialAddressHouseNameNumber, that.residentialAddressHouseNameNumber)
                && Objects.equals(residentialAddressStreet, that.residentialAddressStreet)
                && Objects.equals(residentialAddressArea, that.residentialAddressArea)
                && Objects.equals(residentialAddressPostTown, that.residentialAddressPostTown)
                && Objects.equals(residentialAddressRegion, that.residentialAddressRegion)
                && Objects.equals(residentialAddressCountryName, that.residentialAddressCountryName)
                && Objects.equals(residentialAddressPostCode, that.residentialAddressPostCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trusteeId, trusteeForename1, trusteeForename2, trusteeSurname,
                dateOfBirth, nationality, corporateIndicator, trusteeTypeId, appointmentDate,
                ceasedDate, serviceAddressHouseNameNumber, serviceAddressStreet, serviceAddressArea,
                serviceAddressPostTown, serviceAddressRegion, serviceAddressCountryName,
                serviceAddressPostCode, residentialAddressHouseNameNumber, residentialAddressStreet,
                residentialAddressArea, residentialAddressPostTown, residentialAddressRegion,
                residentialAddressCountryName, residentialAddressPostCode);
    }

}

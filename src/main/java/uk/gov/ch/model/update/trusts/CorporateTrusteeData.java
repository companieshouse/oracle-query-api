package uk.gov.ch.model.update.trusts;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import java.util.Objects;
import uk.gov.companieshouse.api.model.common.Address;

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

    @Column(name = "COUNTRY")
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

    @Column(name = "RES_OFFICE_NAME_NUMBER")
    private String registeredOfficeHouseNameNumber;

    @Column(name = "RES_OFFICE_STREET")
    private String registeredOfficeStreet;

    @Column(name = "RES_OFFICE_AREA")
    private String registeredOfficeArea;

    @Column(name = "RES_OFFICE_POST_TOWN")
    private String registeredOfficePostTown;

    @Column(name = "RES_OFFICE_REGION")
    private String registeredOfficeRegion;

    @Column(name = "RES_OFFICE_COUNTRY_NAME")
    private String registeredOfficeCountryName;

    @Column(name = "RES_OFFICE_POST_CODE")
    private String registeredOfficePostCode;

    @Transient
    @JsonProperty("serviceAddress")
    private Address serviceAddress;

    @Transient
    @JsonProperty("registeredOfficeAddress")
    private Address registeredOfficeAddress;


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

    public Address getRegisteredOfficeAddress() {
        this.registeredOfficeAddress = new Address();
        this.registeredOfficeAddress.setPremises(registeredOfficeHouseNameNumber);
        this.registeredOfficeAddress.setAddressLine1(registeredOfficeStreet);
        this.registeredOfficeAddress.setAddressLine2(registeredOfficeArea);
        this.registeredOfficeAddress.setLocality(registeredOfficePostTown);
        this.registeredOfficeAddress.setRegion(registeredOfficeRegion);
        this.registeredOfficeAddress.setCountry(registeredOfficeCountryName);
        this.registeredOfficeAddress.setPostalCode(registeredOfficePostCode);
        return this.registeredOfficeAddress;
    }

    public void setRegisteredOfficeAddress(Address registeredOfficeAddress) {
        if (registeredOfficeAddress != null) {
            this.registeredOfficeHouseNameNumber = registeredOfficeAddress.getPremises();
            this.registeredOfficeStreet = registeredOfficeAddress.getAddressLine1();
            this.registeredOfficeArea = registeredOfficeAddress.getAddressLine2();
            this.registeredOfficePostTown = registeredOfficeAddress.getLocality();
            this.registeredOfficeRegion = registeredOfficeAddress.getRegion();
            this.registeredOfficeCountryName = registeredOfficeAddress.getCountry();
            this.registeredOfficePostCode = registeredOfficeAddress.getPostalCode();
        }
    }


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
        CorporateTrusteeData that = (CorporateTrusteeData) o;
        return Objects.equals(trusteeId, that.trusteeId) && Objects.equals(trusteeName,
                that.trusteeName) && Objects.equals(registerLocation, that.registerLocation)
                && Objects.equals(registrationNumber, that.registrationNumber) && Objects.equals(
                lawGoverned, that.lawGoverned) && Objects.equals(legalForm, that.legalForm)
                && Objects.equals(country, that.country) && Objects.equals(
                onRegisterInCountryFormed, that.onRegisterInCountryFormed) && Objects.equals(
                corporateInd, that.corporateInd) && Objects.equals(trusteeTypeId,
                that.trusteeTypeId) && Objects.equals(appointmentDate, that.appointmentDate)
                && Objects.equals(ceasedDate, that.ceasedDate) && Objects.equals(
                serviceAddressHouseNameNumber, that.serviceAddressHouseNameNumber)
                && Objects.equals(serviceAddressStreet, that.serviceAddressStreet)
                && Objects.equals(serviceAddressArea, that.serviceAddressArea) && Objects.equals(
                serviceAddressPostTown, that.serviceAddressPostTown) && Objects.equals(
                serviceAddressRegion, that.serviceAddressRegion) && Objects.equals(
                serviceAddressCountryName, that.serviceAddressCountryName) && Objects.equals(
                serviceAddressPostCode, that.serviceAddressPostCode) && Objects.equals(
                registeredOfficeHouseNameNumber, that.registeredOfficeHouseNameNumber)
                && Objects.equals(registeredOfficeStreet, that.registeredOfficeStreet)
                && Objects.equals(registeredOfficeArea, that.registeredOfficeArea)
                && Objects.equals(registeredOfficePostTown, that.registeredOfficePostTown)
                && Objects.equals(registeredOfficeRegion, that.registeredOfficeRegion)
                && Objects.equals(registeredOfficeCountryName, that.registeredOfficeCountryName)
                && Objects.equals(registeredOfficePostCode, that.registeredOfficePostCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trusteeId, trusteeName, registerLocation, registrationNumber,
                lawGoverned, legalForm, country, onRegisterInCountryFormed, corporateInd,
                trusteeTypeId, appointmentDate, ceasedDate, serviceAddressHouseNameNumber,
                serviceAddressStreet, serviceAddressArea, serviceAddressPostTown,
                serviceAddressRegion, serviceAddressCountryName, serviceAddressPostCode,
                registeredOfficeHouseNameNumber, registeredOfficeStreet, registeredOfficeArea,
                registeredOfficePostTown, registeredOfficeRegion, registeredOfficeCountryName,
                registeredOfficePostCode);
    }
}

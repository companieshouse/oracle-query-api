package uk.gov.ch.model.officer.bankrupt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;


@Entity
@Table(name = "SCOTTISH_BANKRUPT_OFFICER")
public class ScottishBankruptOfficerDataModel {

    @Id
    @Column(name = "EPHEMERAL_KEY")
    private String ephemeralKey;
    @Column(name = "FORENAME_1")
    private String forename1;
    @Column(name = "FORENAME_2")
    private String forename2;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "ADDRESS_LINE_1")
    private String addressLine1;
    @Column(name = "ADDRESS_LINE_2")
    private String addressLine2;
    @Column(name = "ADDRESS_LINE_3")
    private String addressLine3;
    @Column(name = "ADDRESS_TOWN")
    private String addressTown;
    @Column(name = "ADDRESS_COUNTY")
    private String addressCounty;
    @Column(name = "ADDRESS_POSTCODE")
    private String addressPostcode;
    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;
    @Column(name= "ALIAS")
    private String alias;
    @Column(name= "CASE_REFERENCE")
    private String caseReference;
    @Column(name= "CASE_TYPE")
    private String caseType;
    @Column(name= "BANKRUPTCY_TYPE")
    private String bankruptcyType;
    @Column(name= "START_DATE")
    private LocalDate startDate;
    @Column(name= "DEBTOR_DISCHARGE_DATE")
    private LocalDate debtorDischargeDate;
    @Column(name= "TRUSTEE_DISCHARGE_DATE")
    private LocalDate trusteeDischargeDate;

    public String getEphemeralKey() {
        return ephemeralKey;
    }

    public void setEphemeralKey(String ephemeralKey) {
        this.ephemeralKey = ephemeralKey;
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

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getAddressTown() {
        return addressTown;
    }

    public void setAddressTown(String addressTown) {
        this.addressTown = addressTown;
    }

    public String getAddressCounty() {
        return addressCounty;
    }

    public void setAddressCounty(String addressCounty) {
        this.addressCounty = addressCounty;
    }

    public String getAddressPostcode() {
        return addressPostcode;
    }

    public void setAddressPostcode(String addressPostcode) {
        this.addressPostcode = addressPostcode;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCaseReference() {
        return caseReference;
    }

    public void setCaseReference(String caseReference) {
        this.caseReference = caseReference;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getBankruptcyType() {
        return bankruptcyType;
    }

    public void setBankruptcyType(String bankruptcyType) {
        this.bankruptcyType = bankruptcyType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDebtorDischargeDate() {
        return debtorDischargeDate;
    }

    public void setDebtorDischargeDate(LocalDate debtorDischargeDate) {
        this.debtorDischargeDate = debtorDischargeDate;
    }

    public LocalDate getTrusteeDischargeDate() {
        return trusteeDischargeDate;
    }

    public void setTrusteeDischargeDate(LocalDate trusteeDischargeDate) {
        this.trusteeDischargeDate = trusteeDischargeDate;
    }
}

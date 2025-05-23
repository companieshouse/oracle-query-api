package uk.gov.ch.model.officer.bankrupt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDate;

@JsonInclude(Include.NON_NULL)
public class ScottishBankruptOfficerDetails {

    private String ephemeralKey;
    private String forename1;
    private String forename2;
    private String surname;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String town;
    private String county;
    private String postcode;
    private LocalDate dateOfBirth;
    private String alias;
    private String caseReference;
    private String caseType;
    private String bankruptcyType;
    private LocalDate startDate;
    private LocalDate debtorDischargeDate;
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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
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

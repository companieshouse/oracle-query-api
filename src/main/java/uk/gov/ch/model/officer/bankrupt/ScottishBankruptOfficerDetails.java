package uk.gov.ch.model.officer.bankrupt;

import java.time.LocalDate;

public class ScottishBankruptOfficerDetails extends ScottishBankruptOfficerBase {

    private String alias;
    private String caseReference;
    private String bankruptcyType;
    private LocalDate startDate;
    private LocalDate trusteeDischargeDate;

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

    public LocalDate getTrusteeDischargeDate() {
        return trusteeDischargeDate;
    }

    public void setTrusteeDischargeDate(LocalDate trusteeDischargeDate) {
        this.trusteeDischargeDate = trusteeDischargeDate;
    }
}

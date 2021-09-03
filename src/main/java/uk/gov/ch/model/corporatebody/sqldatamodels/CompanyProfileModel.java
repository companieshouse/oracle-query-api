package uk.gov.ch.model.corporatebody.sqldatamodels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyProfileModel {
    
    @JsonProperty("company_name")
    private String companyName;
    
    @JsonProperty("company_number")
    private String companyNumber;
    
    @JsonProperty("type")
    private String type;
    
    @JsonProperty("has_mortgages")
    private String hasMortgages;
    
    @JsonProperty("registered_office_is_in_dispute")
    private String registeredOfficeIsInDispute;
    
    @JsonProperty("undeliverable_registered_office_address")
    private String undeliverableRegisteredOfficeAddress;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("date_of_dissolution")
    private String dateOfDissolution;
    
    @JsonProperty("creation_date")
    private String creationDate;
    
    @JsonProperty("account_type")
    private String accountType;
    
    @JsonProperty("acc_ref_date")
    private String accRefDate;
    
    @JsonProperty("has_appointments")
    private String hasAppointments;
    
    @JsonProperty("has_insolvency_history")
    private String hasInsolvencyHistory;
    
    @JsonProperty("jurisdiction")
    private String jurisdiction;
    
    @JsonProperty("proof_status")
    private String proofStatus;
    
    @JsonProperty("cic_ind")
    private String cicInd;
    
    @JsonProperty("super_secure_psc_ind")
    private String superSecurePscInd;
    
    @JsonProperty("subtype")
    private String subtype;
    
    @JsonProperty("CreatedTime")
    private String createdTime;
    
    @JsonProperty("previous_company_names")
    private List<PreviousCompanyNames> previousCompanyNames;
    
    @JsonProperty("registered_office_address")
    private RegisteredOfficeAddress registeredOfficeAddress;
    
    @JsonProperty("accounting_dates")
    private AccountingDates accountingDates;
    
    @JsonProperty("confirmation_statement_dates")
    private ConfirmationStatementDates confirmationStatementDates;
    
    @JsonProperty("confirmation_statement_overdue")
    private String confirmationStatementOverdue;
    
    @JsonProperty("account_overdue")
    private String accountOverdue;
    
    @JsonProperty("annual_return_overdue")
    private String annualReturnOverdue;
    
    @JsonProperty("annual_return_dates")
    private AnnualReturnDates annualReturnDates;
    
    @JsonProperty("sic_codes")
    private List<SicCodes> sicCodes;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHasMortgages() {
        return hasMortgages;
    }

    public void setHasMortgages(String hasMortgages) {
        this.hasMortgages = hasMortgages;
    }

    public String getRegisteredOfficeIsInDispute() {
        return registeredOfficeIsInDispute;
    }

    public void setRegisteredOfficeIsInDispute(String registeredOfficeIsInDispute) {
        this.registeredOfficeIsInDispute = registeredOfficeIsInDispute;
    }

    public String getUndeliverableRegisteredOfficeAddress() {
        return undeliverableRegisteredOfficeAddress;
    }

    public void setUndeliverableRegisteredOfficeAddress(String undeliverableRegisteredOfficeAddress) {
        this.undeliverableRegisteredOfficeAddress = undeliverableRegisteredOfficeAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateOfDissolution() {
        return dateOfDissolution;
    }

    public void setDateOfDissolution(String dateOfDissolution) {
        this.dateOfDissolution = dateOfDissolution;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccRefDate() {
        return accRefDate;
    }

    public void setAccRefDate(String accRefDate) {
        this.accRefDate = accRefDate;
    }

    public String getHasAppointments() {
        return hasAppointments;
    }

    public void setHasAppointments(String hasAppointments) {
        this.hasAppointments = hasAppointments;
    }

    public String getHasInsolvencyHistory() {
        return hasInsolvencyHistory;
    }

    public void setHasInsolvencyHistory(String hasInsolvencyHistory) {
        this.hasInsolvencyHistory = hasInsolvencyHistory;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public String getProofStatus() {
        return proofStatus;
    }

    public void setProofStatus(String proofStatus) {
        this.proofStatus = proofStatus;
    }

    public String getCicInd() {
        return cicInd;
    }

    public void setCicInd(String cicInd) {
        this.cicInd = cicInd;
    }

    public String getSuperSecurePscInd() {
        return superSecurePscInd;
    }

    public void setSuperSecurePscInd(String superSecurePscInd) {
        this.superSecurePscInd = superSecurePscInd;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public List<PreviousCompanyNames> getPreviousCompanyNames() {
        return previousCompanyNames;
    }

    public void setPreviousCompanyNames(List<PreviousCompanyNames> previousCompanyNames) {
        this.previousCompanyNames = previousCompanyNames;
    }

    public RegisteredOfficeAddress getRegisteredOfficeAddress() {
        return registeredOfficeAddress;
    }

    public void setRegisteredOfficeAddress(RegisteredOfficeAddress registeredOfficeAddress) {
        this.registeredOfficeAddress = registeredOfficeAddress;
    }

    public AccountingDates getAccountingDates() {
        return accountingDates;
    }

    public void setAccountingDates(AccountingDates accountingDates) {
        this.accountingDates = accountingDates;
    }

    public ConfirmationStatementDates getConfirmationStatementDates() {
        return confirmationStatementDates;
    }

    public void setConfirmationStatementDates(ConfirmationStatementDates confirmationStatementDates) {
        this.confirmationStatementDates = confirmationStatementDates;
    }

    public String getConfirmationStatementOverdue() {
        return confirmationStatementOverdue;
    }

    public void setConfirmationStatementOverdue(String confirmationStatementOverdue) {
        this.confirmationStatementOverdue = confirmationStatementOverdue;
    }

    public String getAccountOverdue() {
        return accountOverdue;
    }

    public void setAccountOverdue(String accountOverdue) {
        this.accountOverdue = accountOverdue;
    }

    public String getAnnualReturnOverdue() {
        return annualReturnOverdue;
    }

    public void setAnnualReturnOverdue(String annualReturnOverdue) {
        this.annualReturnOverdue = annualReturnOverdue;
    }

    public AnnualReturnDates getAnnualReturnDates() {
        return annualReturnDates;
    }

    public void setAnnualReturnDates(AnnualReturnDates annualReturnDates) {
        this.annualReturnDates = annualReturnDates;
    }

    public List<SicCodes> getSicCodes() {
        return sicCodes;
    }

    public void setSicCodes(List<SicCodes> sicCodes) {
        this.sicCodes = sicCodes;
    }
}

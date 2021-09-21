package uk.gov.ch.transformers.corporatebody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import uk.gov.ch.model.corporatebody.sqldatamodels.CompanyProfileModel;
import uk.gov.ch.model.corporatebody.sqldatamodels.ConfirmationStatementDates;
import uk.gov.ch.model.corporatebody.sqldatamodels.PreviousCompanyNames;
import uk.gov.ch.model.corporatebody.sqldatamodels.RegisteredOfficeAddress;
import uk.gov.ch.model.corporatebody.sqldatamodels.SicCodes;
import uk.gov.companieshouse.api.model.company.AnnualReturnApi;
import uk.gov.companieshouse.api.model.company.CompanyProfileApi;
import uk.gov.companieshouse.api.model.company.ConfirmationStatementApi;
import uk.gov.companieshouse.api.model.company.PreviousCompanyNamesApi;
import uk.gov.companieshouse.api.model.company.RegisteredOfficeAddressApi;
import uk.gov.companieshouse.api.model.company.account.AccountingReferenceDateApi;
import uk.gov.companieshouse.api.model.company.account.CompanyAccountApi;
import uk.gov.companieshouse.api.model.company.account.LastAccountsApi;
import uk.gov.companieshouse.api.model.company.account.NextAccountsApi;

@Component
public class CorporateBodyTransformer {
    
    private static final List<String> COMPANY_STATUS_DETAIL = Arrays.asList("5", "Q", "R", "X", "Z", "AA", "AB");
    private static final String ARD_DEFAULT_STRING = "99";

    public CompanyProfileApi convert(CompanyProfileModel model) {
        CompanyProfileApi companyProfileApi = new CompanyProfileApi();
        companyProfileApi.setCompanyName(model.getCompanyName());
        companyProfileApi.setCompanyNumber(model.getCompanyNumber());
        companyProfileApi.setCompanyStatus(CompanyStatusEnum.fromString(model.getStatus()).getDescription());
        if(COMPANY_STATUS_DETAIL.contains(model.getStatus().toUpperCase())) {
            companyProfileApi.setCompanyStatusDetail(CompanyStatusDetailEnum.fromString(model.getStatus()).getDescription());
        }
        companyProfileApi.setDateOfCreation(getLocalDateFromString(model.getCreationDate()));
        companyProfileApi.setDateOfCessation(getLocalDateFromString(model.getDateOfDissolution()));
        companyProfileApi.setCommunityInterestCompany(getBooleanFromString(model.getCicInd()));
        companyProfileApi.setHasInsolvencyHistory(getBooleanFromString(model.getHasInsolvencyHistory()));
        if (model.getPreviousCompanyNames() != null) {
            companyProfileApi.setPreviousCompanyNames(getPreviousCompanyNames(model.getPreviousCompanyNames()));
        }
        companyProfileApi.setType(CorporateBodyTypeEnum.fromString(model.getType()).getDescription());
        if (model.getRegisteredOfficeAddress() != null) {
            companyProfileApi
                    .setRegisteredOfficeAddress(getRegisteredOfficeAddress(model.getRegisteredOfficeAddress()));
        }
        companyProfileApi.setRegisteredOfficeIsInDispute(getBooleanFromString(model.getRegisteredOfficeIsInDispute()));
        companyProfileApi.setUndeliverableRegisteredOfficeAddress(
                getBooleanFromString(model.getUndeliverableRegisteredOfficeAddress()));
        companyProfileApi.setJurisdiction(JurisdictionEnum.fromString(model.getJurisdiction()).getDescription());
        if (model.getConfirmationStatementDates() != null) {
            companyProfileApi
                    .setConfirmationStatement(getConfirmationStatementDates(model.getConfirmationStatementDates(),
                            getBooleanFromString(model.getConfirmationStatementOverdue())));
        }
        if (model.getAnnualReturnDates() != null) {
            companyProfileApi.setAnnualReturn(getAnnualReturn(model));
        }
        companyProfileApi.setAccounts(getAccounts(model));
        if (model.getSicCodes() != null && !model.getSicCodes().isEmpty()) {
            companyProfileApi.setSicCodes(getSicCodes(model.getSicCodes()));
        }
        companyProfileApi.setHasCharges(getBooleanFromString(model.getHasMortgages()));
        companyProfileApi.setLastFullMembersListDate(getLocalDateFromString(model.getFullMembersListDate()));
        return companyProfileApi;
    }

    private LocalDate getLocalDateFromString(String dateString) {
        if (dateString != null && !dateString.isEmpty()) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            return LocalDate.parse(dateString, dateTimeFormatter);
        }
        return null;
    }

    private boolean getBooleanFromString(String boolString) {
        if(boolString != null) {            
            return boolString.equals("1");
        }
        return false;
    }

    private List<PreviousCompanyNamesApi> getPreviousCompanyNames(List<PreviousCompanyNames> originalList) {
        List<PreviousCompanyNamesApi> previousNames = new ArrayList<>();
        for (PreviousCompanyNames name : originalList) {
            PreviousCompanyNamesApi previousCompanyName = new PreviousCompanyNamesApi();
            previousCompanyName.setName(name.getName());
            previousCompanyName.setEffectiveFrom(getLocalDateFromString(name.getEffectiveFrom()));
            previousCompanyName.setCeasedOn(getLocalDateFromString(name.getCeasedOn()));
            previousNames.add(previousCompanyName);
        }
        return previousNames;
    }

    private RegisteredOfficeAddressApi getRegisteredOfficeAddress(RegisteredOfficeAddress registeredOfficeAddress) {
        RegisteredOfficeAddressApi registeredOfficeAddressApi = new RegisteredOfficeAddressApi();
        registeredOfficeAddressApi.setAddressLine1(registeredOfficeAddress.getAddressLine1());
        registeredOfficeAddressApi.setAddressLine2(registeredOfficeAddress.getAddressLine2());
        registeredOfficeAddressApi.setCareOf(registeredOfficeAddress.getCareOf());
        registeredOfficeAddressApi.setCountry(registeredOfficeAddress.getCountry());
        registeredOfficeAddressApi.setLocality(registeredOfficeAddress.getLocality());
        registeredOfficeAddressApi.setPoBox(registeredOfficeAddress.getPoBox());
        registeredOfficeAddressApi.setPostalCode(registeredOfficeAddress.getPostalCode());
        registeredOfficeAddressApi.setPremises(registeredOfficeAddress.getPremises());
        registeredOfficeAddressApi.setRegion(registeredOfficeAddress.getRegion());
        return registeredOfficeAddressApi;
    }

    private ConfirmationStatementApi getConfirmationStatementDates(ConfirmationStatementDates csDates,
            boolean overdue) {
        ConfirmationStatementApi confirmationStatementApi = new ConfirmationStatementApi();
        confirmationStatementApi.setNextDue(getLocalDateFromString(csDates.getNextDue()));
        confirmationStatementApi.setNextMadeUpTo(getLocalDateFromString(csDates.getNextMadeUpTo()));
        confirmationStatementApi.setOverdue(overdue);

        return confirmationStatementApi;
    }

    private CompanyAccountApi getAccounts(CompanyProfileModel model) {
        CompanyAccountApi companyAccountApi = new CompanyAccountApi();
        if(model.getAccRefDate() != null && !model.getAccRefDate().isEmpty()) {
            if(model.getAccRefDate().substring(0,2).equalsIgnoreCase(ARD_DEFAULT_STRING) ||
                    model.getAccRefDate().substring(2).equalsIgnoreCase(ARD_DEFAULT_STRING)) {
                companyAccountApi.setAccountingReferenceDate(null);
            } else {                
                AccountingReferenceDateApi accountingReferenceDateApi = new AccountingReferenceDateApi();
                accountingReferenceDateApi.setDay(model.getAccRefDate().substring(0,2));
                accountingReferenceDateApi.setMonth(model.getAccRefDate().substring(2));
                companyAccountApi.setAccountingReferenceDate(accountingReferenceDateApi);
            }
        }
        if (model.getAccountingDates() != null) {
            companyAccountApi.setNextDue(getLocalDateFromString(model.getAccountingDates().getNextDue()));
            companyAccountApi.setNextMadeUpTo(getLocalDateFromString(model.getAccountingDates().getNextPeriodEndOn()));

            LastAccountsApi lastAccountApi = new LastAccountsApi();
            lastAccountApi.setPeriodStartOn(getLocalDateFromString(model.getAccountingDates().getLastPeriodStartOn()));
            lastAccountApi.setPeriodEndOn(getLocalDateFromString(model.getAccountingDates().getLastPeriodEndOn()));
            lastAccountApi.setMadeUpTo(getLocalDateFromString(model.getAccountingDates().getLastPeriodEndOn()));
            if (model.getAccountType() != null && !model.getAccountType().equals("0")) {
                lastAccountApi.setType(CompanyAccountTypeEnum.fromString(model.getAccountType()).getDescription());
            }
            companyAccountApi.setLastAccounts(lastAccountApi);

            NextAccountsApi nextAccountApi = new NextAccountsApi();
            nextAccountApi.setPeriodStartOn(getLocalDateFromString(model.getAccountingDates().getNextPeriodStartOn()));
            nextAccountApi.setPeriodEndOn(getLocalDateFromString(model.getAccountingDates().getNextPeriodEndOn()));
            nextAccountApi.setOverdue(getBooleanFromString(model.getAccountOverdue()));
            nextAccountApi.setDueOn(getLocalDateFromString(model.getAccountingDates().getNextPeriodEndOn()));
            companyAccountApi.setNextAccounts(nextAccountApi);
        }
        companyAccountApi.setOverdue(getBooleanFromString(model.getAccountOverdue()));
        return companyAccountApi;
    }

    private AnnualReturnApi getAnnualReturn(CompanyProfileModel model) {
        AnnualReturnApi annualReturnApi = new AnnualReturnApi();
        annualReturnApi.setOverdue(getBooleanFromString(model.getAnnualReturnOverdue()));
        annualReturnApi.setLastMadeUpTo(getLocalDateFromString(model.getAnnualReturnDates().getLatestMadeUpTo()));
        return annualReturnApi;
    }

    private String[] getSicCodes(List<SicCodes> sicCodeList) {
        List<String> sicCodes = new ArrayList<>();
        if (sicCodeList.get(0) != null) {
            sicCodes = getSicCodeString(sicCodeList.get(0));
        }
        return sicCodes.toArray(new String[sicCodes.size()]);
    }

    private List<String> getSicCodeString(SicCodes sicCode) {
        List<String> sicCodes = new ArrayList<>();
        if (sicCode.getSic1() != null) {
            sicCodes.add(sicCode.getSic1());
        }
        if (sicCode.getSic2() != null) {
            sicCodes.add(sicCode.getSic2());
        }
        if (sicCode.getSic3() != null) {
            sicCodes.add(sicCode.getSic3());
        }
        if (sicCode.getSic4() != null) {
            sicCodes.add(sicCode.getSic4());
        }
        if (sicCode.getSic5() != null) {
            sicCodes.add(sicCode.getSic5());
        }
        return sicCodes;
    }

}

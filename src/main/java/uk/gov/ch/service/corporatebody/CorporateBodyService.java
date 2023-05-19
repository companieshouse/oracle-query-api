package uk.gov.ch.service.corporatebody;

import uk.gov.ch.exception.CompanyProfileMappingException;
import uk.gov.ch.exception.CorporateBodyDetailsEmailAddressNotFoundException;
import uk.gov.ch.exception.CorporateBodyNotFoundException;
import uk.gov.ch.model.corporatebody.sqldatamodels.RegisteredEmailAddressJson;
import uk.gov.companieshouse.api.model.company.CompanyProfileApi;

public interface CorporateBodyService {

    Long getActionCode(String companyNumber) throws CorporateBodyNotFoundException;

    Long getTradedStatus(String companyNumber) throws CorporateBodyNotFoundException;
    
    CompanyProfileApi getCompanyProfile(String companyNumber) throws CorporateBodyNotFoundException, CompanyProfileMappingException;

    RegisteredEmailAddressJson getRegisteredEmailAddress(String incorporationNumber) throws CorporateBodyDetailsEmailAddressNotFoundException;
}

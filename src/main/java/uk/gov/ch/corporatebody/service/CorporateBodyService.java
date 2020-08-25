package uk.gov.ch.corporatebody.service;

import uk.gov.ch.corporatebody.exception.CorporateBodyNotFoundException;

public interface CorporateBodyService {

    int getActionCode(String companyNumber) throws CorporateBodyNotFoundException;
}

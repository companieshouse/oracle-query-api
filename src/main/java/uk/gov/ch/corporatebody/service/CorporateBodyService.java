package uk.gov.ch.corporatebody.service;

import uk.gov.ch.corporatebody.exception.CorporateBodyNotFoundException;

public interface CorporateBodyService {

    long getActionCode(String companyNumber) throws CorporateBodyNotFoundException;
}

package uk.gov.ch.service.corporatebody;

import uk.gov.ch.exception.CorporateBodyNotFoundException;

public interface CorporateBodyService {

    Long getActionCode(String companyNumber) throws CorporateBodyNotFoundException;

    Long getTradedStatus(String companyNumber) throws CorporateBodyNotFoundException;
}

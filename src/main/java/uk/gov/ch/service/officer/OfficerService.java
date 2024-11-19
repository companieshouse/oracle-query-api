package uk.gov.ch.service.officer;

import uk.gov.ch.exception.NoOfficersExistingException;
import uk.gov.ch.exception.OfficersMappingException;
import uk.gov.companieshouse.api.model.officers.OfficersApi;

public interface OfficerService {

    OfficersApi getOfficers(String companyNumber)
            throws OfficersMappingException, NoOfficersExistingException;

}

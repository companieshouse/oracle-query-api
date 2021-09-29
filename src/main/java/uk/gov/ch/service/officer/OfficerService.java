package uk.gov.ch.service.officer;

import uk.gov.companieshouse.api.model.officers.OfficersApi;

public interface OfficerService {
    
    OfficersApi getOfficers(String companyNumber);

}

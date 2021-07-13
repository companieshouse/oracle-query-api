package uk.gov.ch.service.officer.active;

import uk.gov.ch.exception.InvalidActiveOfficersCountFoundException;
import uk.gov.ch.model.officer.active.ActiveOfficerDetails;

public interface ActiveOfficerDetailsService {
    ActiveOfficerDetails getActiveOfficerDetails(String companyNumber) throws InvalidActiveOfficersCountFoundException;
}

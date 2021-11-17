package uk.gov.ch.service.officer.active;

import org.springframework.data.domain.Pageable;
import uk.gov.ch.exception.InvalidActiveOfficersCountFoundException;
import uk.gov.ch.model.officer.active.ActiveDirectorDetails;

public interface ActiveDirectorDetailsService {
    ActiveDirectorDetails getActiveDirectorDetails(String companyNumber, Pageable pageable) throws InvalidActiveOfficersCountFoundException;
}

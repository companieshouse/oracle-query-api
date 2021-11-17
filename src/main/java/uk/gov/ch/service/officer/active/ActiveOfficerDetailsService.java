package uk.gov.ch.service.officer.active;

import org.springframework.data.domain.Pageable;
import uk.gov.ch.exception.InvalidActiveOfficersCountFoundException;
import uk.gov.ch.model.officer.active.ActiveOfficerDetails;

import java.util.List;

public interface ActiveOfficerDetailsService {
    List<ActiveOfficerDetails> getActiveOfficersDetails(String companyNumber, Pageable pageable) throws InvalidActiveOfficersCountFoundException;

}

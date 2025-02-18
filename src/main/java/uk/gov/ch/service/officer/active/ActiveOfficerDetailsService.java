package uk.gov.ch.service.officer.active;

import java.util.List;
import org.springframework.data.domain.Pageable;
import uk.gov.ch.exception.InvalidActiveOfficersCountFoundException;
import uk.gov.ch.model.officer.active.ActiveOfficerDetails;

public interface ActiveOfficerDetailsService {

    List<ActiveOfficerDetails> getActiveOfficersDetails(String companyNumber, Pageable pageable)
            throws InvalidActiveOfficersCountFoundException;

}

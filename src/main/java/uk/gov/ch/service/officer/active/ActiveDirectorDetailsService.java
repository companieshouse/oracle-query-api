package uk.gov.ch.service.officer.active;

import javassist.NotFoundException;
import org.springframework.data.domain.Pageable;
import uk.gov.ch.exception.InvalidActiveDirectorsCountFoundException;
import uk.gov.ch.model.officer.active.ActiveDirectorDetails;

import java.util.List;

public interface ActiveDirectorDetailsService {
    ActiveDirectorDetails getActiveDirectorDetails(String companyNumber, Pageable pageable) throws InvalidActiveDirectorsCountFoundException;

    List<ActiveDirectorDetails> getActiveOfficers(String companyNumber, Pageable pageable) throws NotFoundException;

}

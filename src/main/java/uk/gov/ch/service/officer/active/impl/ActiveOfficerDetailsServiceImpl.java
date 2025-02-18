package uk.gov.ch.service.officer.active.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.InvalidActiveOfficersCountFoundException;
import uk.gov.ch.model.officer.active.ActiveOfficerDetails;
import uk.gov.ch.repository.officers.ActiveOfficersDetailsRepository;
import uk.gov.ch.service.officer.active.ActiveOfficerDetailsService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@Service
public class ActiveOfficerDetailsServiceImpl implements ActiveOfficerDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OracleQueryApplication.APPLICATION_NAME_SPACE);

    private static final String NO_OFFICERS_FOUND_MESSAGE = "No active officers were found.";

    @Autowired
    private ActiveOfficersDetailsRepository activeOfficersDetailsRepository;

    @Override
    public List<ActiveOfficerDetails> getActiveOfficersDetails(String companyNumber,
            Pageable pageable) throws InvalidActiveOfficersCountFoundException {
        List<ActiveOfficerDetails> list = activeOfficersDetailsRepository.getActiveOfficersDetails(
                companyNumber, pageable).toList();
        LOGGER.debug(String.format("getActiveOfficersDetails Query list size returned = %s",
                list.size()));
        if (list.isEmpty()) {
            LOGGER.info(NO_OFFICERS_FOUND_MESSAGE);
            throw new InvalidActiveOfficersCountFoundException(NO_OFFICERS_FOUND_MESSAGE);
        } else {
            return list;
        }
    }
}

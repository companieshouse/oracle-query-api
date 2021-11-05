package uk.gov.ch.service.officer.active.impl;

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

import java.util.List;

@Service
public class ActiveOfficerDetailsServiceImpl implements ActiveOfficerDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    private static final String WRONG_OFFICER_COUNT_MESSAGE = "Incorrect number of officers found must be single active director company.";
    private static final String NO_OFFICERS_FOUND_MESSAGE = "No active officers were found.";

    @Autowired
    private ActiveOfficersDetailsRepository activeOfficersDetailsRepository;

    @Override
    public ActiveOfficerDetails getActiveDirectorDetails(String companyNumber, Pageable pageable) throws InvalidActiveOfficersCountFoundException {
        List<ActiveOfficerDetails> list =  activeOfficersDetailsRepository.getActiveDirectorDetails(companyNumber, pageable).toList();
        if (list.size() == 1 ) {
            return list.get(0);
        } else {
            LOGGER.info(WRONG_OFFICER_COUNT_MESSAGE + companyNumber);
            throw new InvalidActiveOfficersCountFoundException(WRONG_OFFICER_COUNT_MESSAGE);
        }
    }

    @Override
    public List<ActiveOfficerDetails> getActiveOfficersDetails(String companyNumber, Pageable pageable) throws InvalidActiveOfficersCountFoundException {
        List<ActiveOfficerDetails> list =  activeOfficersDetailsRepository.getActiveOfficersDetails(companyNumber, pageable).toList();
        LOGGER.debug(String.format("getActiveOfficersDetails Query list size returned = %s", list.size()));
        if(list.isEmpty() ) {
            LOGGER.info(NO_OFFICERS_FOUND_MESSAGE);
            throw new InvalidActiveOfficersCountFoundException(NO_OFFICERS_FOUND_MESSAGE);
        } else {
            return list;
        }
    }
}

package uk.gov.ch.service.officer.active.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.InvalidActiveOfficersCountFoundException;
import uk.gov.ch.model.officer.active.ActiveOfficerDetails;
import uk.gov.ch.repository.officers.ActiveOfficerDetailsRepository;
import uk.gov.ch.service.officer.active.ActiveOfficerDetailsService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

import java.util.List;

@Service
public class ActiveOfficerDetailsServiceImpl implements ActiveOfficerDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    private static final String WRONG_OFFICER_COUNT_MESSAGE = "Incorrect number of officers found must be single active officer company  ";

    @Autowired
    private ActiveOfficerDetailsRepository activeOfficerDetailsRepository;

    @Override
    public ActiveOfficerDetails getActiveOfficerDetails(String companyNumber) throws InvalidActiveOfficersCountFoundException {
        List<ActiveOfficerDetails> list =  activeOfficerDetailsRepository.getActiveOfficerDetails(companyNumber);
        if (list.size() == 1 ) {
            return list.get(0);
        } else {
            LOGGER.error(WRONG_OFFICER_COUNT_MESSAGE + companyNumber);
            throw new InvalidActiveOfficersCountFoundException(WRONG_OFFICER_COUNT_MESSAGE);
        }
    }
}

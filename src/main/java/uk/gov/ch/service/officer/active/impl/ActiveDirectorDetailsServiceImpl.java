package uk.gov.ch.service.officer.active.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.InvalidActiveOfficersCountFoundException;
import uk.gov.ch.model.officer.active.ActiveDirectorDetails;
import uk.gov.ch.repository.officers.ActiveDirectorDetailsRepository;
import uk.gov.ch.service.officer.active.ActiveDirectorDetailsService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

import java.util.List;

@Service
public class ActiveDirectorDetailsServiceImpl implements ActiveDirectorDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    private static final String WRONG_OFFICER_COUNT_MESSAGE = "Incorrect number of officers found must be single active director company.";

    @Autowired
    private ActiveDirectorDetailsRepository activeDirectorDetailsRepository;

    @Override
    public ActiveDirectorDetails getActiveDirectorDetails(String companyNumber, Pageable pageable) throws InvalidActiveOfficersCountFoundException {
        List<ActiveDirectorDetails> list =  activeDirectorDetailsRepository.getActiveDirectorDetails(companyNumber, pageable).toList();
        if (list.size() == 1 ) {
            return list.get(0);
        } else {
            LOGGER.info(WRONG_OFFICER_COUNT_MESSAGE + companyNumber);
            throw new InvalidActiveOfficersCountFoundException(WRONG_OFFICER_COUNT_MESSAGE);
        }
    }
}

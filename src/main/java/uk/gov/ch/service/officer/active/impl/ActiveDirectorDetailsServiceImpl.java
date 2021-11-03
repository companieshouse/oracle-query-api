package uk.gov.ch.service.officer.active.impl;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.InvalidActiveDirectorsCountFoundException;
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
    private static final String NO_OFFICERS_FOUND_MESSAGE = "No active officers were found.";

    @Autowired
    private ActiveDirectorDetailsRepository activeDirectorDetailsRepository;

    @Override
    public ActiveDirectorDetails getActiveDirectorDetails(String companyNumber, Pageable pageable) throws InvalidActiveDirectorsCountFoundException {
        List<ActiveDirectorDetails> list =  activeDirectorDetailsRepository.getActiveDirectorDetails(companyNumber, pageable).toList();
        if (list.size() == 1 ) {
            return list.get(0);
        } else {
            LOGGER.error(WRONG_OFFICER_COUNT_MESSAGE + companyNumber);
            throw new InvalidActiveDirectorsCountFoundException(WRONG_OFFICER_COUNT_MESSAGE);
        }
    }

    @Override
    public List<ActiveDirectorDetails> getActiveOfficers(String companyNumber, Pageable pageable) throws NotFoundException {
        List<ActiveDirectorDetails> list =  activeDirectorDetailsRepository.getActiveDirectorDetails(companyNumber, pageable).toList();
        LOGGER.debug(String.format("Query list size returned = ") + list.size());
        if(list.size() == 0 ) {
            LOGGER.error(WRONG_OFFICER_COUNT_MESSAGE + companyNumber);
            throw new NotFoundException(NO_OFFICERS_FOUND_MESSAGE);
        } else {
            return list;
        }
    }
}

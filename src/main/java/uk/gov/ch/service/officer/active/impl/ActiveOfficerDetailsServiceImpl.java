package uk.gov.ch.service.officer.active.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.NoActiveOfficersFoundException;
import uk.gov.ch.exception.ServiceException;
import uk.gov.ch.model.officer.active.ActiveOfficerDetails;
import uk.gov.ch.repository.officers.ActiveOfficerDetailsRepository;
import uk.gov.ch.service.officer.active.ActiveOfficerDetailsService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

import java.util.List;

@Service
public class ActiveOfficerDetailsServiceImpl implements ActiveOfficerDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    private static final String NOT_FOUND_MESSAGE = "No results were found when getting Active Officers for company number ";

    @Autowired
    private ActiveOfficerDetailsRepository activeOfficerDetailsRepository;

    @Override
    public ActiveOfficerDetails getActiveOfficerDetails(String companyNumber) throws NoActiveOfficersFoundException, ServiceException {
        List<ActiveOfficerDetails> list =  activeOfficerDetailsRepository.getActiveOfficerDetails(companyNumber);
        if (list.size() == 1 ) {
            return list.get(0);
        } else {
            if (list.isEmpty()) {
                LOGGER.error(NOT_FOUND_MESSAGE + companyNumber);
                throw new NoActiveOfficersFoundException(NOT_FOUND_MESSAGE);
            } else {
                throw new ServiceException("Single result not returned");
            }
        }
    }
}

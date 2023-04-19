package uk.gov.ch.service.update.impl;

import uk.gov.ch.service.update.OverseasEntityManagingOfficersService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.ManagingOfficerCountNotFoundException;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;
import uk.gov.companieshouse.logging.util.DataMap;
import uk.gov.ch.model.update.OverseasEntityManagingOfficerData;
import uk.gov.ch.repository.update.OverseasEntityManagingOfficersRepository;

@Service
public class OverseasEntityManagingOfficersServiceImpl implements OverseasEntityManagingOfficersService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    private static final String NO_MANAGING_OFFICERS_FOUND_MESSAGE = "No managing officers were found for this overseas entity";

    @Autowired
    private OverseasEntityManagingOfficersRepository overseasEntityManagingOfficersRepository;

    @Override
    public List<OverseasEntityManagingOfficerData> getOverseasEntityManagingOfficers(String companyNumber)
            throws ManagingOfficerCountNotFoundException {
        DataMap dataMap = new DataMap.Builder().build();
        dataMap.getLogMap().put("companyName", companyNumber);

        LOGGER.infoContext(companyNumber, "Searching for managing officers for overseas entity", dataMap.getLogMap());

        List<OverseasEntityManagingOfficerData> moList = overseasEntityManagingOfficersRepository
                .getOverseasEntityManagingOfficers(companyNumber);

        LOGGER.infoContext(companyNumber,
                String.format("getManagingOfficerData Query list size returned = %s", moList.size()),
                dataMap.getLogMap());
        if (moList.isEmpty()) {
            LOGGER.infoContext(companyNumber, NO_MANAGING_OFFICERS_FOUND_MESSAGE, dataMap.getLogMap());
            throw new ManagingOfficerCountNotFoundException(NO_MANAGING_OFFICERS_FOUND_MESSAGE);
        } else {
            return moList;
        }
    }

}

package uk.gov.ch.update.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.EntityEmailNotFoundException;
import uk.gov.ch.model.update.EntityData;
import uk.gov.ch.repository.update.EntityDataRepository;
import uk.gov.ch.update.EntityDataService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@Service
public class EntityDataServiceImpl implements EntityDataService {

    @Autowired
    private EntityDataRepository entityDataRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);
    private static final String NOT_FOUND_MESSAGE = "Entity email not found for company number: ";

    @Override
    public EntityData getEntityEmail(String incorporationNumber) throws EntityEmailNotFoundException {

        LOGGER.info("Calling database during Update process to retrieve entity email for company: " + incorporationNumber);
        EntityData entityData = entityDataRepository.getEmailAddress(incorporationNumber);

        if (entityData.getEmailAddress() == null) {
            LOGGER.error(NOT_FOUND_MESSAGE);
            throw new EntityEmailNotFoundException(NOT_FOUND_MESSAGE + incorporationNumber);
        }

        return entityData;
    }
}

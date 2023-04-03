package uk.gov.ch.controller.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.EntityEmailNotFoundException;
import uk.gov.ch.model.update.EntityData;
import uk.gov.ch.update.impl.EntityDataServiceImpl;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@RestController
public class EntityDataController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private EntityDataServiceImpl entityDataService;

    @GetMapping("/company/{incorporationNumber}/update/entityData")
    public ResponseEntity<EntityData> getEntityEmail(@PathVariable String incorporationNumber) {
        try {
            return ResponseEntity.ok(entityDataService.getEntityEmail(incorporationNumber));
        } catch (EntityEmailNotFoundException e) {
            LOGGER.error("No entity email address found for company " + incorporationNumber, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
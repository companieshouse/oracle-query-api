package uk.gov.ch.update;

import uk.gov.ch.exception.EntityEmailNotFoundException;
import uk.gov.ch.model.update.EntityData;

public interface EntityDataService {
    EntityData getEntityEmail(String incorporationNumber) throws EntityEmailNotFoundException;
}

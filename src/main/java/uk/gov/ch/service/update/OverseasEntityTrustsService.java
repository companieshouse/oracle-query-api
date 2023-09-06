package uk.gov.ch.service.update;

import uk.gov.ch.exception.TrustsCountNotFoundException;
import uk.gov.ch.model.update.OverseasEntityTrust;

import java.util.List;

public interface OverseasEntityTrustsService {
    List<OverseasEntityTrust> getTrusts(String companyNumber)
            throws TrustsCountNotFoundException;
}

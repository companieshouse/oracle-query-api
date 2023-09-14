package uk.gov.ch.service.update;

import java.util.List;
import uk.gov.ch.exception.TrustDataCountNotFoundException;
import uk.gov.ch.model.update.CorporateTrusteeData;

public interface CorporateTrusteesService {
    List<CorporateTrusteeData> getCorporateTrusteeData(String trustId)
            throws TrustDataCountNotFoundException;
}

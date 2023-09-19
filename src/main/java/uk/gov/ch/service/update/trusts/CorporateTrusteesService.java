package uk.gov.ch.service.update.trusts;

import java.util.List;
import uk.gov.ch.exception.TrustDataCountNotFoundException;
import uk.gov.ch.model.update.trusts.CorporateTrusteeData;

public interface CorporateTrusteesService {
    List<CorporateTrusteeData> getCorporateTrusteeData(String trustId)
            throws TrustDataCountNotFoundException;
}

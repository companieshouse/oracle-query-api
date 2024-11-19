package uk.gov.ch.service.update.trusts;

import java.util.List;
import uk.gov.ch.exception.TrustDataCountNotFoundException;
import uk.gov.ch.model.update.trusts.IndividualTrusteeData;

public interface IndividualTrusteesService {

    List<IndividualTrusteeData> getIndividualTrustees(String trustId)
            throws TrustDataCountNotFoundException;
}

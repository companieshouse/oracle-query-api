package uk.gov.ch.service.update.trusts;

import uk.gov.ch.exception.TrustDataCountNotFoundException;
import uk.gov.ch.model.update.trusts.IndividualTrusteeData;

import java.util.List;

public interface IndividualTrusteesService {
    List<IndividualTrusteeData> getIndividualTrustees(String trustId) throws TrustDataCountNotFoundException;
}

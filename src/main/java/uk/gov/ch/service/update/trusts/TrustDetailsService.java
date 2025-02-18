package uk.gov.ch.service.update.trusts;

import java.util.List;
import uk.gov.ch.exception.TrustDataCountNotFoundException;
import uk.gov.ch.model.update.trusts.TrustDetails;

public interface TrustDetailsService {

    List<TrustDetails> getTrustDetails(String companyNumber)
            throws TrustDataCountNotFoundException;
}

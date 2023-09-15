package uk.gov.ch.service.update.trusts;

import uk.gov.ch.exception.TrustDataCountNotFoundException;
import uk.gov.ch.model.update.trusts.TrustDetails;

import java.util.List;

public interface TrustDetailsService {
    List<TrustDetails> getTrustDetails(String companyNumber)
            throws TrustDataCountNotFoundException;
}

package uk.gov.ch.service.update.trusts;

import java.util.List;
import org.springframework.stereotype.Service;
import uk.gov.ch.exception.TrustDataCountNotFoundException;
import uk.gov.ch.model.update.trusts.TrustLinkData;

@Service
public interface TrustLinksService {
    List<TrustLinkData> getTrustLinkData(String oeNumber) throws TrustDataCountNotFoundException;
}

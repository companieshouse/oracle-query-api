package uk.gov.ch.service.shareholder;
import java.util.List;

import uk.gov.ch.model.shareholder.Shareholder;

public interface ShareholderService {

    int getShareholderCount(String incorporationNumber);

    List<Shareholder> getShareholders(String incorporationNumber);
}

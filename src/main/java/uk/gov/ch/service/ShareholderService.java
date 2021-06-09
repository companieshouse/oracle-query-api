package uk.gov.ch.service;
import java.util.List;

import uk.gov.ch.model.Shareholder;

public interface ShareholderService {

    int getShareholderCount(String incorporationNumber);

    List<Shareholder> getShareholders(String incorporationNumber);
}

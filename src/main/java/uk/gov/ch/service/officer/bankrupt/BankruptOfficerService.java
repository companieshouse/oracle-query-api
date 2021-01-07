package uk.gov.ch.service.officer.bankrupt;

import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearch;
import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerSearchResults;


/**
 * Contract defining the methods available to handle bankrupt officer data
 */
public interface BankruptOfficerService {

  /**
   * Perform a search for a bankrupt officer using filtering, pagination and sorting
   * @return
   */
  ScottishBankruptOfficerSearchResults getScottishBankruptOfficers(ScottishBankruptOfficerSearch search);
}

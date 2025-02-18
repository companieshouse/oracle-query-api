package uk.gov.ch.service.transaction;

import uk.gov.ch.model.transaction.jsondatamodels.Gaz2Transaction;

public interface Gaz2RequestedService {

    Gaz2Transaction getRequestedGaz2(String companyNumber);
}

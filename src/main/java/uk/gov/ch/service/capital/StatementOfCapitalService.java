package uk.gov.ch.service.capital;

import uk.gov.ch.exception.StatementOfCapitalNotFoundException;
import uk.gov.ch.model.capital.StatementOfCapital;

public interface StatementOfCapitalService {
    StatementOfCapital getStatementOfCapital(String incorporationNumber) throws StatementOfCapitalNotFoundException;
}

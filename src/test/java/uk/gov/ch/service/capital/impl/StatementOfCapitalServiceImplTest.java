package uk.gov.ch.service.capital.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.ch.exception.ServiceException;
import uk.gov.ch.exception.StatementOfCapitalNotFoundException;
import uk.gov.ch.model.capital.StatementOfCapital;
import uk.gov.ch.repository.capital.StatementOfCapitalRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatementOfCapitalServiceImplTest {

    private static final String COMPANY_NUMBER = "12345678";

    @InjectMocks
    StatementOfCapitalServiceImpl statementOfCapitalServiceImpl;

    @Mock
    StatementOfCapitalRepository statementOfCapitalRepository;

    @Test
    void testGetStatementOfCapital() throws StatementOfCapitalNotFoundException, ServiceException {
        List<StatementOfCapital> socList = new ArrayList<StatementOfCapital>();
        socList.add(new StatementOfCapital());
        when(statementOfCapitalRepository.getStatementOfCapital(COMPANY_NUMBER)).thenReturn(socList);
        StatementOfCapital statementOfCapital = statementOfCapitalServiceImpl.getStatementOfCapital(COMPANY_NUMBER);
        assertNotNull(statementOfCapital);
    }

    @Test
    void testGetStatementOfCapitalSingleResultNotFoundZero()  throws StatementOfCapitalNotFoundException {
        List<StatementOfCapital> socList = new ArrayList<StatementOfCapital>();
        when(statementOfCapitalRepository.getStatementOfCapital(COMPANY_NUMBER)).thenReturn(socList);
        assertThrows(StatementOfCapitalNotFoundException.class, () ->
                statementOfCapitalServiceImpl.getStatementOfCapital(COMPANY_NUMBER));
    }

    @Test
    void testGetStatementOfCapitalSingleResultNotFoundMoreThanOne() throws StatementOfCapitalNotFoundException {
        List<StatementOfCapital> socList = new ArrayList<StatementOfCapital>();
        socList.add(new StatementOfCapital());
        socList.add(new StatementOfCapital());
        when(statementOfCapitalRepository.getStatementOfCapital(COMPANY_NUMBER)).thenReturn(socList);
        assertThrows(ServiceException.class, () ->
                statementOfCapitalServiceImpl.getStatementOfCapital(COMPANY_NUMBER));
    }

    @Test
    void testGetStatementOfCapitalEmptyResult() throws StatementOfCapitalNotFoundException {
        when(statementOfCapitalRepository.getStatementOfCapital(COMPANY_NUMBER)).thenThrow(StatementOfCapitalNotFoundException.class);
        assertThrows(StatementOfCapitalNotFoundException.class, () ->
                statementOfCapitalServiceImpl.getStatementOfCapital(COMPANY_NUMBER));
    }
}

package uk.gov.ch.service.transaction.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import uk.gov.ch.model.transaction.jsondatamodels.Gaz2Transaction;
import uk.gov.ch.model.transaction.sqldatamodels.Gaz2TransactionDataModel;
import uk.gov.ch.repository.transaction.Gaz2TransactionRepository;
import uk.gov.ch.transformers.transaction.TransactionTransformer;

@ExtendWith(MockitoExtension.class)
class Gaz2RequestedServiceImplTest {

    private static final String COMPANY_NUMBER = "12345678";

    @InjectMocks
    private Gaz2RequestedServiceImpl gaz2RequestedService;

    @Mock
    private Gaz2TransactionRepository gaz2TransactionRepository;

    @Mock
    private TransactionTransformer transactionTransformer;

    @Test
    @DisplayName("Get Requested Gaz2 for company - no transaction found")
    void testGetGaz2RequestedNoTransactionFound() {
        when(gaz2TransactionRepository.findRequestedGaz2(COMPANY_NUMBER)).thenReturn(null);
        Gaz2Transaction serviceResult = gaz2RequestedService.getRequestedGaz2(COMPANY_NUMBER);
        assertNull(serviceResult);
    }

    @Test
    @DisplayName("Get Requested Gaz2 for company - success path")
    void testGetGaz2Requested() {
        Gaz2TransactionDataModel gaz2TransactionDataModel = gaz2TransactionDataModel();
        Gaz2Transaction gaz2Transaction = gaz2Transaction();
        when(gaz2TransactionRepository.findRequestedGaz2(COMPANY_NUMBER)).thenReturn(gaz2TransactionDataModel);
        when(transactionTransformer.convert(any(Gaz2TransactionDataModel.class))).thenReturn(gaz2Transaction);
        Gaz2Transaction serviceResult = gaz2RequestedService.getRequestedGaz2(COMPANY_NUMBER);
        assertEquals(gaz2Transaction, serviceResult);
    }

    private Gaz2TransactionDataModel gaz2TransactionDataModel() {
        Gaz2TransactionDataModel gaz2TransactionDataModel = new Gaz2TransactionDataModel();
        gaz2TransactionDataModel.setTransactionTypeId(4321L);
        gaz2TransactionDataModel.setTransactionStatusTypeId(5678L);
        gaz2TransactionDataModel.setTransactionId(1234L);

        return gaz2TransactionDataModel;
    }

    private Gaz2Transaction gaz2Transaction() {
        Gaz2Transaction gaz2Transaction = new Gaz2Transaction();
        gaz2Transaction.setType("type");
        gaz2Transaction.setStatusType("status_type");
        gaz2Transaction.setId("id");

        return gaz2Transaction;
    }
}
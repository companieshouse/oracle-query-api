package uk.gov.ch.service.corporatebody.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.ch.exception.CorporateBodyNotFoundException;
import uk.gov.ch.repository.corporatebody.CorporateBodyRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CorporateBodyServiceImplTest {

    @Mock
    private CorporateBodyRepository repository;

    @InjectMocks
    private CorporateBodyServiceImpl corporateBodyService;

    private static final String INCORPORATION_NUMBER = "12345678";

    @Test
    @DisplayName("Get action code - company found")
    void testGetActionCodeCompanyFound() throws CorporateBodyNotFoundException {
        when(repository.getActionCode(INCORPORATION_NUMBER)).thenReturn(0L);

        assertEquals(0L, corporateBodyService.getActionCode(INCORPORATION_NUMBER));
    }

    @Test
    @DisplayName("Get action code - company not found")
    void testGetActionCodeCompanyNotFound() throws CorporateBodyNotFoundException {
        when(repository.getActionCode(INCORPORATION_NUMBER)).thenThrow(new CorporateBodyNotFoundException("error"));

        assertThrows(CorporateBodyNotFoundException.class, () -> corporateBodyService.getActionCode(INCORPORATION_NUMBER));
    }

    @Test
    @DisplayName("Get traded status - company found")
    void testGetTradedStatusTypeCompanyFound() throws CorporateBodyNotFoundException {
        when(repository.getTradedStatus(INCORPORATION_NUMBER)).thenReturn(0L);

        assertEquals(0L, corporateBodyService.getTradedStatus(INCORPORATION_NUMBER));
    }

    @Test
    @DisplayName("Get traded status - company not found")
    void testGetTradedStatusTypeCompanyNotFound() throws CorporateBodyNotFoundException {
        when(repository.getTradedStatus(INCORPORATION_NUMBER)).thenThrow(new CorporateBodyNotFoundException("error"));

        assertThrows(CorporateBodyNotFoundException.class, () -> corporateBodyService.getTradedStatus(INCORPORATION_NUMBER));
    }
}

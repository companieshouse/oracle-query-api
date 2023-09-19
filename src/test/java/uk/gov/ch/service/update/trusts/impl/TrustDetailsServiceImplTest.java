package uk.gov.ch.service.update.trusts.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.ch.exception.TrustDataCountNotFoundException;
import uk.gov.ch.model.update.trusts.TrustDetails;
import uk.gov.ch.repository.update.trusts.TrustDetailsRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrustDetailsServiceImplTest {
    @InjectMocks
    private TrustDetailsServiceImpl trustDetailsService;

    @Mock
    private TrustDetailsRepository trustDetailsRepository;

    private static final String OE_NUMBER = "OE123456";
    private static final TrustDetails TRUST_1 = new TrustDetails() {{
        setTrustId(1L);
        setTrustName("My trust");
    }};

    private static final TrustDetails TRUST_2 = new TrustDetails() {{
        setTrustId(2L);
        setTrustName("My other trust");
    }};

    @Test
    void testGetTrustDetailsSingleItemReturned() throws TrustDataCountNotFoundException {
        List<TrustDetails> trusts = Collections.singletonList(TRUST_1);

        when(trustDetailsRepository.getTrustDetails(OE_NUMBER)).thenReturn(trusts);
        List<TrustDetails> result = trustDetailsService.getTrustDetails(OE_NUMBER);

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getTrustId());
        assertEquals("My trust", result.get(0).getTrustName());
    }

    @Test
    void testGetTrustDetailsMultipleItemsReturned() throws TrustDataCountNotFoundException {
        List<TrustDetails> trusts = Arrays.asList(TRUST_1, TRUST_2);

        when(trustDetailsRepository.getTrustDetails(OE_NUMBER)).thenReturn(trusts);
        List<TrustDetails> result = trustDetailsService.getTrustDetails(OE_NUMBER);

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getTrustId());
        assertEquals("My trust", result.get(0).getTrustName());
        assertEquals(2L, result.get(1).getTrustId());
        assertEquals("My other trust", result.get(1).getTrustName());
    }

    @Test
    void testGetTrustDetailsNoDataReturned() {
        List<TrustDetails> trusts = Collections.emptyList();

        when(trustDetailsRepository.getTrustDetails(OE_NUMBER)).thenReturn(trusts);

        Assertions.assertThrows(TrustDataCountNotFoundException.class,
                () -> trustDetailsService.getTrustDetails(OE_NUMBER));
    }

    @Test
    void testGetTrustDetailsNullDataReturned() {
        List<TrustDetails> trusts = null;

        when(trustDetailsRepository.getTrustDetails(OE_NUMBER)).thenReturn(trusts);

        Assertions.assertThrows(TrustDataCountNotFoundException.class,
                () -> trustDetailsService.getTrustDetails(OE_NUMBER));
    }
}

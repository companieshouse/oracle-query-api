package uk.gov.ch.controller.update.trusts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.ch.exception.TrustDataCountNotFoundException;
import uk.gov.ch.model.update.trusts.TrustDetails;
import uk.gov.ch.service.update.trusts.impl.TrustDetailsServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrustDetailsControllerTest {
    @Mock
    private TrustDetailsServiceImpl trustDetailsService;

    @InjectMocks
    private TrustDetailsController trustDetailsController;

    private static final String OE_NUMBER = "OE123456";
    private static final TrustDetails TRUST_1 = new TrustDetails() {{
        setTrustId("1");
        setTrustName("My trust");
    }};

    private static final TrustDetails TRUST_2 = new TrustDetails() {{
        setTrustId("2");
        setTrustName("My other trust");
    }};

    @Test
    void testGetTrustDetailsMethodReturnsCountError() throws TrustDataCountNotFoundException {
        when(trustDetailsService.getTrustDetails(OE_NUMBER))
                .thenThrow(new TrustDataCountNotFoundException("Test"));

        ResponseEntity responseEntity = trustDetailsController.getTrustDetails(OE_NUMBER);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testGetTrustDetailsMethodReturnsExpectedData() throws TrustDataCountNotFoundException {
        List<TrustDetails> trusts = Arrays.asList(TRUST_1, TRUST_2);

        when(trustDetailsService.getTrustDetails(OE_NUMBER)).thenReturn(trusts);

        ResponseEntity responseEntity = trustDetailsController.getTrustDetails(OE_NUMBER);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(trusts, responseEntity.getBody());
    }
}

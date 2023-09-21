package uk.gov.ch.controller.update.trusts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.ch.exception.TrustDataCountNotFoundException;
import uk.gov.ch.model.update.trusts.TrustLinkData;
import uk.gov.ch.service.update.trusts.TrustLinksService;

@ExtendWith(MockitoExtension.class)
class TrustLinksControllerTest {

    private static final String OE_NUMBER = "OE123456";
    private static final TrustLinkData TRUST_LINK_DATA_1 = new TrustLinkData() {{
        setTrustId("1");
        setCorporateBodyAppointmentId("123");
    }};
    private static final TrustLinkData TRUST_LINK_DATA_2 = new TrustLinkData() {{
        setTrustId("2");
        setCorporateBodyAppointmentId("456");
    }};
    @Mock
    private TrustLinksService trustLinksService;
    @InjectMocks
    private TrustLinksController trustLinksController;

    @Test
    @DisplayName("Get Trust Links - trust with no trust links")
    void testGetCorporateTrusteesReturnsCountError() throws TrustDataCountNotFoundException {
        when(trustLinksService.getTrustLinkData(OE_NUMBER)).thenThrow(
                new TrustDataCountNotFoundException("No trust link data found."));

        ResponseEntity<List<TrustLinkData>> responseEntity = trustLinksController.getTrustLinkData(
                OE_NUMBER);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }


    @Test
    @DisplayName("Get trust links - trust links returned for trust")
    void testGetCorporateTrusteesReturnsExpectedData() throws TrustDataCountNotFoundException {
        List<TrustLinkData> trustees = Arrays.asList(TRUST_LINK_DATA_1, TRUST_LINK_DATA_2);

        when(trustLinksService.getTrustLinkData(OE_NUMBER)).thenReturn(trustees);

        ResponseEntity<List<TrustLinkData>> responseEntity = trustLinksController.getTrustLinkData(
                OE_NUMBER);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(trustees, responseEntity.getBody());
    }

}
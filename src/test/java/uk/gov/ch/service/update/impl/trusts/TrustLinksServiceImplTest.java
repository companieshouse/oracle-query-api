package uk.gov.ch.service.update.impl.trusts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.ch.exception.TrustDataCountNotFoundException;
import uk.gov.ch.model.update.trusts.TrustLinkData;
import uk.gov.ch.repository.update.trusts.TrustLinksRepository;
import uk.gov.ch.service.update.trusts.impl.TrustLinksServiceImpl;

@ExtendWith(MockitoExtension.class)
class TrustLinksServiceImplTest {

    private static final String OE_NUMBER = "OE123456";
    private static final TrustLinkData TRUST_LINK_DATA_1 = new TrustLinkData() {{
        setTrustId("1");
        setCorporateBodyAppointmentId("123");
    }};
    private static final TrustLinkData TRUST_LINK_DATA_2 = new TrustLinkData() {{
        setTrustId("2");
        setCorporateBodyAppointmentId("456");
    }};
    @InjectMocks
    private TrustLinksServiceImpl trustLinksService;
    @Mock
    private TrustLinksRepository trustLinksRepository;

    @Test
    @DisplayName("Get trust links - multiple trust links returned")
    void testGetMultipleCorporateTrusteesData() throws TrustDataCountNotFoundException {
        List<TrustLinkData> trustees = Arrays.asList(TRUST_LINK_DATA_1, TRUST_LINK_DATA_2);

        when(trustLinksRepository.getTrustLinks(OE_NUMBER)).thenReturn(trustees);
        List<TrustLinkData> result = trustLinksService.getTrustLinkData(OE_NUMBER);

        assertEquals(TRUST_LINK_DATA_1, result.get(0));
        assertEquals(TRUST_LINK_DATA_2, result.get(1));
    }


    @Test
    @DisplayName("Get trust links - No trust links returned")
    void testGetCorporateTrusteesNoData() {
        List<TrustLinkData> trustees = Collections.emptyList();
        when(trustLinksRepository.getTrustLinks(OE_NUMBER)).thenReturn(trustees);

        assertThrows(TrustDataCountNotFoundException.class,
                () -> trustLinksService.getTrustLinkData(OE_NUMBER));
    }

    @Test
    @DisplayName("Get trust links - Null Check")
    void testGetCorporateTrusteesNullCheck() {
        List<TrustLinkData> trustees = null;
        when(trustLinksRepository.getTrustLinks(OE_NUMBER)).thenReturn(trustees);

        assertThrows(TrustDataCountNotFoundException.class,
                () -> trustLinksService.getTrustLinkData(OE_NUMBER));
    }
}

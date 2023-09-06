package uk.gov.ch.service.update.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.ch.exception.TrustsCountNotFoundException;
import uk.gov.ch.model.update.OverseasEntityTrust;
import uk.gov.ch.repository.update.OverseasEntityTrustsRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OverseasEntityTrustsServiceImplTest {
    @InjectMocks
    private OverseasEntityTrustsServiceImpl overseasEntityTrustsService;

    @Mock
    private OverseasEntityTrustsRepository overseasEntityTrustsRepository;

    private static final String COMPANY_NUMBER = "OE123456";
    private static final OverseasEntityTrust TRUST_1 = new OverseasEntityTrust() {{
        setTrustId("123");
        setTrustName("My trust");
        setTrustCreationDate("2023/01/01");
        setUnableToObtainAllInfoIndicator("Y");
    }};
    private static final OverseasEntityTrust TRUST_2 = new OverseasEntityTrust() {{
        setTrustId("456");
        setTrustName("My other trust");
        setTrustCreationDate("2023/02/01");
        setUnableToObtainAllInfoIndicator("N");
    }};

    @Test
    @DisplayName("Get trusts - multiple trusts returned")
    void testGetMultipleTrustsData() throws TrustsCountNotFoundException {
        List<OverseasEntityTrust> trusts = Arrays.asList(TRUST_1, TRUST_2);

        when(overseasEntityTrustsRepository.getTrusts(COMPANY_NUMBER)).thenReturn(trusts);
        List<OverseasEntityTrust> result = overseasEntityTrustsService.getTrusts(COMPANY_NUMBER);

        assertEquals(2, result.size());
        assertEquals("123", result.get(0).getTrustId());
        assertEquals("My trust", result.get(0).getTrustName());
        assertEquals("2023/01/01", result.get(0).getTrustCreationDate());
        assertEquals("Y", result.get(0).getUnableToObtainAllInfoIndicator());
        assertEquals("456", result.get(1).getTrustId());
        assertEquals("My other trust", result.get(1).getTrustName());
        assertEquals("2023/02/01", result.get(1).getTrustCreationDate());
        assertEquals("N", result.get(1).getUnableToObtainAllInfoIndicator());
    }

    @Test
    @DisplayName("Get trusts - no trusts returned")
    void testGetTrustsNoData() {
        List<OverseasEntityTrust> trusts = Collections.emptyList();
        when(overseasEntityTrustsRepository.getTrusts(COMPANY_NUMBER)).thenReturn(trusts);

        Assertions.assertThrows(TrustsCountNotFoundException.class,
                () -> overseasEntityTrustsService.getTrusts(COMPANY_NUMBER));
    }
}

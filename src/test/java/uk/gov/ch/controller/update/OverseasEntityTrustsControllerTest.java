package uk.gov.ch.controller.update;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.ch.exception.TrustsCountNotFoundException;
import uk.gov.ch.model.update.OverseasEntityTrust;
import uk.gov.ch.service.update.impl.OverseasEntityTrustsServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OverseasEntityTrustsControllerTest {
    @Mock
    private OverseasEntityTrustsServiceImpl overseasEntityTrustsService;

    @InjectMocks
    private OverseasEntityTrustsController overseasEntityTrustsController;

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
    @DisplayName("Get trusts - overseas entity with no trusts")
    void testGetOverseasEntityTrustsMethodReturnsCountError() throws TrustsCountNotFoundException {
        when(overseasEntityTrustsService.getTrusts(COMPANY_NUMBER))
                .thenThrow(new TrustsCountNotFoundException("No trusts were found."));

        ResponseEntity responseEntity = overseasEntityTrustsController.getOverseasEntityTrusts(COMPANY_NUMBER);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Get trusts - trusts returned for overseas entity")
    void testGetOverseasEntityTrustsMethodReturnsExpectedData() throws TrustsCountNotFoundException {
        List<OverseasEntityTrust> trusts = Arrays.asList(TRUST_1, TRUST_2);

        when(overseasEntityTrustsService.getTrusts(COMPANY_NUMBER)).thenReturn(trusts);

        ResponseEntity responseEntity = overseasEntityTrustsController.getOverseasEntityTrusts(COMPANY_NUMBER);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(trusts, responseEntity.getBody());
    }
}

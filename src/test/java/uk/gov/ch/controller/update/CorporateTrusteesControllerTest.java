package uk.gov.ch.controller.update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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
import uk.gov.ch.model.update.CorporateTrusteeData;
import uk.gov.ch.service.update.CorporateTrusteesService;

@ExtendWith(MockitoExtension.class)
class CorporateTrusteesControllerTest {

    @Mock
    private CorporateTrusteesService corporateTrusteesService;

    @InjectMocks
    private CorporateTrusteesController corporateTrusteesController;

    private static final String TRUST_ID = "OE123456";
    private static final CorporateTrusteeData TRUSTEE_1 = new CorporateTrusteeData() {{
        setTrusteeId(123L);
        setTrusteeName("My trust");
        setRegisterLocation("London, UK");
        setRegistrationNumber("R12345");
        setLawGoverned("UK Law");
        setLegalForm("LLP");
        setOnRegisterInCountryFormed(true);
        setCorporateInd("Y");
        setTrusteeTypeId(1L);
        setAppointmentDate(LocalDate.of(2023, 1, 1)); // Note: This way of constructing Date is deprecated; consider using LocalDate and adjust your entity accordingly.
        setCeasedDate(LocalDate.of(2023, 12, 31));
    }};

    private static final CorporateTrusteeData TRUSTEE_2 = new CorporateTrusteeData() {{
        setTrusteeId(456L);
        setTrusteeName("My other trust");
        setRegisterLocation("Manchester, UK");
        setRegistrationNumber("R67890");
        setLawGoverned("UK Law");
        setLegalForm("LLC");
        setOnRegisterInCountryFormed(false);
        setCorporateInd("N");
        setTrusteeTypeId(2L);
        setAppointmentDate(LocalDate.of(2023, 2, 1));
        setCeasedDate(LocalDate.of(2023, 11, 30));
    }};

    @Test
    @DisplayName("Get corporate trustees - trust with no corporate trustees")
    void testGetCorporateTrusteesReturnsCountError() throws TrustDataCountNotFoundException {
        when(corporateTrusteesService.getCorporateTrusteeData(TRUST_ID))
                .thenThrow(new TrustDataCountNotFoundException("No corporate trustees were found."));

        ResponseEntity<List<CorporateTrusteeData>> responseEntity = corporateTrusteesController.getCorporateTrusteeData(TRUST_ID);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Get corporate trustees - trustees returned for trust")
    void testGetCorporateTrusteesReturnsExpectedData() throws TrustDataCountNotFoundException {
        List<CorporateTrusteeData> trustees = Arrays.asList(TRUSTEE_1, TRUSTEE_2);

        when(corporateTrusteesService.getCorporateTrusteeData(TRUST_ID)).thenReturn(trustees);

        ResponseEntity<List<CorporateTrusteeData>> responseEntity = corporateTrusteesController.getCorporateTrusteeData(TRUST_ID);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(trustees, responseEntity.getBody());
    }
}

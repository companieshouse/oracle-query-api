package uk.gov.ch.service.update.impl.trusts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.ch.exception.TrustDataCountNotFoundException;
import uk.gov.ch.model.update.trusts.CorporateTrusteeData;
import uk.gov.ch.repository.update.trusts.CorporateTrusteesRepository;
import uk.gov.ch.service.update.trusts.impl.CorporateTrusteesServiceImpl;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CorporateTrusteesServiceImplTest {

    @InjectMocks
    private CorporateTrusteesServiceImpl corporateTrusteesService;

    @Mock
    private CorporateTrusteesRepository corporateTrusteesRepository;

    private static final String TRUST_ID = "OE123456";
    private static final CorporateTrusteeData TRUSTEE_1 = new CorporateTrusteeData() {{
        setTrusteeId("123");
        setTrusteeName("My trust");
        setRegisterLocation("London, UK");
        setRegistrationNumber("R12345");
        setLawGoverned("UK Law");
        setLegalForm("LLP");
        setOnRegisterInCountryFormed(true);
        setCorporateInd("Y");
        setTrusteeTypeId("1");
        setAppointmentDate("2023-01-01");
        setCeasedDate("2023-12-31");
    }};

    private static final CorporateTrusteeData TRUSTEE_2 = new CorporateTrusteeData() {{
        setTrusteeId("456");
        setTrusteeName("My other trust");
        setRegisterLocation("Manchester, UK");
        setRegistrationNumber("R67890");
        setLawGoverned("UK Law");
        setLegalForm("LLC");
        setOnRegisterInCountryFormed(false);
        setCorporateInd("N");
        setTrusteeTypeId("2");

        setAppointmentDate("1985-01-01");
        setCeasedDate("2023-11-30");
    }};

    @Test
    @DisplayName("Get corporate trustees - multiple trustees returned")
    void testGetMultipleCorporateTrusteesData() throws TrustDataCountNotFoundException {
        List<CorporateTrusteeData> trustees = Arrays.asList(TRUSTEE_1, TRUSTEE_2);

        when(corporateTrusteesRepository.getCorporateTrustees(TRUST_ID)).thenReturn(trustees);
        List<CorporateTrusteeData> result = corporateTrusteesService.getCorporateTrusteeData(TRUST_ID);

        assertEquals(TRUSTEE_1, result.get(0));
        assertEquals(TRUSTEE_2, result.get(1));
    }

    @Test
    @DisplayName("Get corporate trustees - no trustees returned")
    void testGetCorporateTrusteesNoData() {
        List<CorporateTrusteeData> trustees = Collections.emptyList();
        when(corporateTrusteesRepository.getCorporateTrustees(TRUST_ID)).thenReturn(trustees);

        Assertions.assertThrows(TrustDataCountNotFoundException.class,
                () -> corporateTrusteesService.getCorporateTrusteeData(TRUST_ID));
    }

    @Test
    @DisplayName("Get corporate trustees - Null Check")
    void testGetCorporateTrusteesNullCheck() {
        List<CorporateTrusteeData> trustees = null;
        when(corporateTrusteesRepository.getCorporateTrustees(TRUST_ID)).thenReturn(trustees);

        Assertions.assertThrows(TrustDataCountNotFoundException.class,
                () -> corporateTrusteesService.getCorporateTrusteeData(TRUST_ID));
    }
}

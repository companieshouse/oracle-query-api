package uk.gov.ch.controller.update.trusts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.ch.exception.TrustDataCountNotFoundException;
import uk.gov.ch.model.update.trusts.IndividualTrusteeData;
import uk.gov.ch.service.update.trusts.impl.IndividualTrusteesServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IndividualTrusteesControllerTest {
    @Mock
    private IndividualTrusteesServiceImpl individualTrusteesService;

    @InjectMocks
    private IndividualTrusteesController individualTrusteesController;

    private static final String TRUST_ID = "123";
    private static final IndividualTrusteeData TRUSTEE_1 = new IndividualTrusteeData() {{
        setTrusteeId(1L);
        setTrusteeForename1("Name 1");
        setTrusteeSurname("Surname");
    }};

    private static final IndividualTrusteeData TRUSTEE_2 = new IndividualTrusteeData() {{
        setTrusteeId(2L);
        setTrusteeForename1("Other Name 1");
        setTrusteeSurname("Other Surname");
    }};

    @Test
    void testGetIndividualTrusteesMethodReturnsCountError() throws TrustDataCountNotFoundException {
        when(individualTrusteesService.getIndividualTrustees(TRUST_ID))
                .thenThrow(new TrustDataCountNotFoundException("Test"));

        ResponseEntity responseEntity = individualTrusteesController.getIndividualTrustees(TRUST_ID);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testGetIndividualTrusteesMethodReturnsExpectedData() throws TrustDataCountNotFoundException {
        List<IndividualTrusteeData> individualTrustees = Arrays.asList(TRUSTEE_1, TRUSTEE_2);

        when(individualTrusteesService.getIndividualTrustees(TRUST_ID)).thenReturn(individualTrustees);

        ResponseEntity responseEntity = individualTrusteesController.getIndividualTrustees(TRUST_ID);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(individualTrustees, responseEntity.getBody());
    }
}

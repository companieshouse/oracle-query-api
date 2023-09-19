package uk.gov.ch.service.update.trusts.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.ch.exception.TrustDataCountNotFoundException;
import uk.gov.ch.model.update.trusts.IndividualTrusteeData;
import uk.gov.ch.repository.update.trusts.IndividualTrusteesRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IndividualTrusteesServiceImplTest {
    @InjectMocks
    private IndividualTrusteesServiceImpl individualTrusteesService;

    @Mock
    private IndividualTrusteesRepository individualTrusteesRepository;

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
    void testGetIndividualTrusteesSingleItemReturned() throws TrustDataCountNotFoundException {
        List<IndividualTrusteeData> individualTrustees = Collections.singletonList(TRUSTEE_1);

        when(individualTrusteesRepository.getIndividualTrustees(TRUST_ID)).thenReturn(individualTrustees);
        List<IndividualTrusteeData> result = individualTrusteesService.getIndividualTrustees(TRUST_ID);

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getTrusteeId());
        assertEquals("Name 1", result.get(0).getTrusteeForename1());
        assertEquals("Surname", result.get(0).getTrusteeSurname());
    }

    @Test
    void testGetIndividualTrusteesMultipleItemsReturned() throws TrustDataCountNotFoundException {
        List<IndividualTrusteeData> individualTrustees = Arrays.asList(TRUSTEE_1, TRUSTEE_2);

        when(individualTrusteesRepository.getIndividualTrustees(TRUST_ID)).thenReturn(individualTrustees);
        List<IndividualTrusteeData> result = individualTrusteesService.getIndividualTrustees(TRUST_ID);

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getTrusteeId());
        assertEquals("Name 1", result.get(0).getTrusteeForename1());
        assertEquals("Surname", result.get(0).getTrusteeSurname());
        assertEquals(2L, result.get(1).getTrusteeId());
        assertEquals("Other Name 1", result.get(1).getTrusteeForename1());
        assertEquals("Other Surname", result.get(1).getTrusteeSurname());
    }

    @Test
    void testGetIndividualTrusteesNoData() {
        List<IndividualTrusteeData> individualTrustees = Collections.emptyList();

        when(individualTrusteesRepository.getIndividualTrustees(TRUST_ID)).thenReturn(individualTrustees);

        Assertions.assertThrows(TrustDataCountNotFoundException.class,
                () -> individualTrusteesService.getIndividualTrustees(TRUST_ID));
    }

    @Test
    void testGetIndividualTrusteesNullData() {
        List<IndividualTrusteeData> individualTrustees = null;

        when(individualTrusteesRepository.getIndividualTrustees(TRUST_ID)).thenReturn(individualTrustees);

        Assertions.assertThrows(TrustDataCountNotFoundException.class,
                () -> individualTrusteesService.getIndividualTrustees(TRUST_ID));
    }
}

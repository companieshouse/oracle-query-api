package uk.gov.ch.service.psc.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import uk.gov.ch.model.psc.PersonWithSignificantControl;
import uk.gov.ch.repository.psc.PersonsWithSignificantControlRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonsWithSignificantControlServiceImplTest {

    private static final String COMPANY_NUMBER = "12345678";
    private static final int START_INDEX = 0;
    private static final int ITEMS_PER_PAGE = 10;

    @InjectMocks
    PersonsWithSignificantControlServiceImpl personsWithSignificantControlServiceImpl;

    @Mock
    PersonsWithSignificantControlRepository personsWithSignificantControlRepository;

    Pageable pageable = PageRequest.of(START_INDEX, ITEMS_PER_PAGE);

    @Test
    @DisplayName("Get persons with significant control service")
    void testPersonsWithSignificantControl() {
        Page<PersonWithSignificantControl> testPscPagess = getMockPscRepo();
        when(personsWithSignificantControlRepository
                .findPersonsWithSignificantControl(COMPANY_NUMBER, pageable))
                .thenReturn(testPscPagess);
        List<PersonWithSignificantControl> response = personsWithSignificantControlServiceImpl.getPersonsWithSignificantControl(COMPANY_NUMBER);
        assertFalse(response.isEmpty());
        assertEquals(3, response.size());
    }

    private Page<PersonWithSignificantControl> getMockPscRepo() {
        List<PersonWithSignificantControl> personsWithSignificantControl = new ArrayList<>();
        personsWithSignificantControl.add(new PersonWithSignificantControl());
        personsWithSignificantControl.add(new PersonWithSignificantControl());
        personsWithSignificantControl.add(new PersonWithSignificantControl());

        return new PageImpl<>(personsWithSignificantControl, pageable, 2);
    }
}

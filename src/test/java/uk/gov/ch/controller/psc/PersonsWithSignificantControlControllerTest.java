package uk.gov.ch.controller.psc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import uk.gov.ch.model.psc.PersonWithSignificantControl;
import uk.gov.ch.service.psc.PersonsWithSignificantControlService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PersonsWithSignificantControlController.class)
class PersonsWithSignificantControlControllerTest {
    private static final String COMPANY_NUMBER = "12345678";
    private static final String INVALID_COMPANY_NUMBER = "123#5678";
    private static final int START_INDEX = 0;
    private static final int ITEMS_PER_PAGE = 10;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    PersonsWithSignificantControlService personsWithSignificantControlService;

    PersonsWithSignificantControlController personsWithSignificantControlController;

    @BeforeEach
    void setUp() {
        personsWithSignificantControlController = new PersonsWithSignificantControlController(personsWithSignificantControlService);
    }

    @Test
    @DisplayName("Get persons with significant control controller")
    void testGetPeopleWithSignificantControl() {
        Pageable pageable = PageRequest.of(START_INDEX, ITEMS_PER_PAGE);
        List<PersonWithSignificantControl> testPscs = new ArrayList<>();
        when(personsWithSignificantControlService.
                getPersonsWithSignificantControl(COMPANY_NUMBER, pageable)).thenReturn(testPscs);
        ResponseEntity<List<PersonWithSignificantControl>> response =
                personsWithSignificantControlController.getPeopleWithSignificantControl(COMPANY_NUMBER, START_INDEX, ITEMS_PER_PAGE);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Get persons with significant control - invalid company number")
    void testGetPeopleWithSignificantControlInvalidCompanyNumber() throws Exception {

        mockMvc.perform(get("/company/{companyNumber}/corporate-body-appointments/persons-of-significant-control", INVALID_COMPANY_NUMBER))
            .andExpect(status().isBadRequest());
    }
}

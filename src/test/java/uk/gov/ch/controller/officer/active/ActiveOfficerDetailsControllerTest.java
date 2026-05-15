package uk.gov.ch.controller.officer.active;

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
import uk.gov.ch.exception.InvalidActiveOfficersCountFoundException;
import uk.gov.ch.model.officer.active.ActiveOfficerDetails;
import uk.gov.ch.service.officer.active.ActiveOfficerDetailsService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ActiveOfficerDetailsController.class)
class ActiveOfficerDetailsControllerTest {

    private static final String COMPANY_NUMBER = "12345678";
    private static final String INVALID_COMPANY_NUMBER = "1234567#";
    private static final int START_INDEX = 0;
    private static final int ITEMS_PER_PAGE = 10;
    private Pageable pageable = PageRequest.of(START_INDEX, ITEMS_PER_PAGE);

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ActiveOfficerDetailsService service;

    private ActiveOfficerDetailsController controller;

    @BeforeEach
    void setUp() {
        controller = new ActiveOfficerDetailsController(service);
    }

    @Test
    @DisplayName("Get Active Officers - Company With No Active Officers")
    void testGetActiveOfficersDetailsForCompanyWithNoActiveOfficers() throws InvalidActiveOfficersCountFoundException {
        when(service.getActiveOfficersDetails(COMPANY_NUMBER, pageable)).thenThrow(new InvalidActiveOfficersCountFoundException("No results were found when getting Active Officers for company number "));
        ResponseEntity responseEntity = controller.getActiveOfficersDetails(COMPANY_NUMBER, START_INDEX, ITEMS_PER_PAGE);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Get Active Officers - Company With Active Officers")
    void testGetActiveOfficersDetailsForCompanyWithMultipleActiveOfficers() throws InvalidActiveOfficersCountFoundException {
        List<ActiveOfficerDetails> officers = new ArrayList();
        officers.add(new ActiveOfficerDetails());
        officers.add(new ActiveOfficerDetails());
        when(service.getActiveOfficersDetails(COMPANY_NUMBER, pageable)).thenReturn(officers);
        ResponseEntity responseEntity = controller.getActiveOfficersDetails(COMPANY_NUMBER, START_INDEX, ITEMS_PER_PAGE);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(officers, responseEntity.getBody());
    }

    @Test
    @DisplayName("Get active officer details - invalid company number")
    void testGetActiveOfficersDetailsInvalidCompanyNumber() throws Exception {

        mockMvc.perform(get("/company/{companyNumber}/officers/active", INVALID_COMPANY_NUMBER))
            .andExpect(status().isBadRequest());
    }

}

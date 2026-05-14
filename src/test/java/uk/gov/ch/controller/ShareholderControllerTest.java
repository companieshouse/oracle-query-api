package uk.gov.ch.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import uk.gov.ch.controller.shareholder.ShareholderController;
import uk.gov.ch.model.shareholder.Shareholder;
import uk.gov.ch.service.shareholder.ShareholderService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ShareholderController.class)
class ShareholderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ShareholderService service;

    private ShareholderController controller;

    private static final String COMPANY_NUMBER = "12345678";
    private static final String INVALID_COMPANY_NUMBER = "123#5678";

    private static final int START_INDEX = 0;
    private static final int ITEMS_PER_PAGE = 10;
    Pageable pageable = PageRequest.of(START_INDEX, ITEMS_PER_PAGE);

    @BeforeEach
    void setUp() {
        controller = new ShareholderController(service);
    }

    @Test
    @DisplayName(("Get shareholders - company with shareholders"))
    void testGetShareholdersFromCorporateBodyWithShareholders() {
        List<Shareholder> shareholders = new ArrayList<Shareholder>();
        shareholders.add(new Shareholder());

        when(service.getShareholders(COMPANY_NUMBER, pageable)).thenReturn(shareholders);

        ResponseEntity<List<Shareholder>> responseEntity = controller.getShareholders(COMPANY_NUMBER, START_INDEX, ITEMS_PER_PAGE);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(shareholders, responseEntity.getBody());
        List<Shareholder> sharers = responseEntity.getBody();
        assertEquals(1, sharers.size());
    }

    @Test
    @DisplayName(("Get shareholders - company with no shareholders"))
    void testGetShareholdersFromCorporateBodyWithNoShareholders() {
        List<Shareholder> shareholders = new ArrayList<Shareholder>();

        when(service.getShareholders(COMPANY_NUMBER, pageable)).thenReturn(shareholders);

        ResponseEntity<List<Shareholder>>  responseEntity = controller.getShareholders(COMPANY_NUMBER,START_INDEX, ITEMS_PER_PAGE);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(shareholders, responseEntity.getBody());
        List<Shareholder> sharers = responseEntity.getBody();
        assertEquals(0, sharers.size());
    }

    @Test
    @DisplayName(("Get shareholders count - company with no shareholders"))
    void testGetShareholdersCountFromCorporateBodyWithNoShareholders() {

        when(service.getShareholderCount(COMPANY_NUMBER)).thenReturn(0);

        ResponseEntity<Integer> responseEntity = controller.getShareholdersCount(COMPANY_NUMBER);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, responseEntity.getBody());
    }

    @Test
    @DisplayName(("Get shareholders count - company with shareholders"))
    void testGetShareholdersCountFromCorporateBodyWithShareholders() {

        when(service.getShareholderCount(COMPANY_NUMBER)).thenReturn(2);

        ResponseEntity<Integer> responseEntity = controller.getShareholdersCount(COMPANY_NUMBER);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody());
    }

    @Test
    @DisplayName("Get shareholders count - invalid company number")
    void testGetShareholdersCountInvalidCompanyNumber() throws Exception {

        mockMvc.perform(get("/company/{companyNumber}/shareholders/count", INVALID_COMPANY_NUMBER))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Get shareholders - invalid company number")
    void testGetShareholdersInvalidCompanyNumber() throws Exception {

        mockMvc.perform(get("/company/{companyNumber}/shareholders", INVALID_COMPANY_NUMBER))
            .andExpect(status().isBadRequest());
    }

}

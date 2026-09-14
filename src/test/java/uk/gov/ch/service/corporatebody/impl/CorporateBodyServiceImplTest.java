package uk.gov.ch.service.corporatebody.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;
import uk.gov.ch.exception.CompanyProfileMappingException;
import uk.gov.ch.exception.CorporateBodyNotFoundException;
import uk.gov.ch.model.corporatebody.sqldatamodels.CompanyProfileModel;
import uk.gov.ch.repository.corporatebody.CorporateBodyDetailsRepository;
import uk.gov.ch.repository.corporatebody.CorporateBodyRepository;
import uk.gov.ch.transformers.corporatebody.CorporateBodyTransformer;
import uk.gov.companieshouse.api.model.company.CompanyProfileApi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CorporateBodyServiceImplTest {

    @Mock
    private CorporateBodyRepository repository;

    @Mock
    private JsonMapper mockJsonMapper;

    @Mock
    private CorporateBodyTransformer mockTransformer;

    @Mock
    private JsonNode mockJsonNode;

    @Mock
    private CorporateBodyDetailsRepository corporateBodyDetailsRepository;

    private CorporateBodyServiceImpl corporateBodyService;

    private static final String INCORPORATION_NUMBER = "12345678";

    @BeforeEach
    void setUp() {
        corporateBodyService = new CorporateBodyServiceImpl(repository, mockJsonMapper,
                mockTransformer, corporateBodyDetailsRepository);
    }

    @Test
    @DisplayName("Get action code - company found")
    void testGetActionCodeCompanyFound() throws CorporateBodyNotFoundException {
        when(repository.getActionCode(INCORPORATION_NUMBER)).thenReturn(0L);

        assertEquals(0L, corporateBodyService.getActionCode(INCORPORATION_NUMBER));
    }

    @Test
    @DisplayName("Get action code - company not found")
    void testGetActionCodeCompanyNotFound() throws CorporateBodyNotFoundException {
        when(repository.getActionCode(INCORPORATION_NUMBER)).thenThrow(new CorporateBodyNotFoundException("error"));

        assertThrows(CorporateBodyNotFoundException.class, () -> corporateBodyService.getActionCode(INCORPORATION_NUMBER));
    }

    @Test
    @DisplayName("Get traded status - company found")
    void testGetTradedStatusTypeCompanyFound() throws CorporateBodyNotFoundException {
        when(repository.getTradedStatus(INCORPORATION_NUMBER)).thenReturn(0L);

        assertEquals(0L, corporateBodyService.getTradedStatus(INCORPORATION_NUMBER));
    }

    @Test
    @DisplayName("Get traded status - company not found")
    void testGetTradedStatusTypeCompanyNotFound() throws CorporateBodyNotFoundException {
        when(repository.getTradedStatus(INCORPORATION_NUMBER)).thenThrow(new CorporateBodyNotFoundException("error"));

        assertThrows(CorporateBodyNotFoundException.class, () -> corporateBodyService.getTradedStatus(INCORPORATION_NUMBER));
    }

    @Test
    @DisplayName("Get company profile - company profile successfully returned")
    void testGetCompanyProfileFound() throws JacksonException, CorporateBodyNotFoundException, CompanyProfileMappingException {
        CompanyProfileApi companyProfileApi = new CompanyProfileApi();
        companyProfileApi.setCompanyNumber(INCORPORATION_NUMBER);
        String resultString = "{\"company_number\":\"" + INCORPORATION_NUMBER + "\"}";
        CompanyProfileModel model = new CompanyProfileModel();
        model.setCompanyNumber(INCORPORATION_NUMBER);

        when(repository.getCompanyProfile(INCORPORATION_NUMBER)).thenReturn(resultString);
        when(mockJsonMapper.readValue(resultString, JsonNode.class)).thenReturn(mockJsonNode);
        when(mockJsonMapper.convertValue(any(JsonNode.class),
                ArgumentMatchers.<TypeReference<CompanyProfileModel>>any())).thenReturn(model);
        when(mockTransformer.convert(model)).thenReturn(companyProfileApi);

        CompanyProfileApi result = corporateBodyService.getCompanyProfile(INCORPORATION_NUMBER);
        assertNotNull(result);
        assertEquals(companyProfileApi.getCompanyNumber(), result.getCompanyNumber());
    }

    @Test
    @DisplayName("String returned from repository includes Company Not Found")
    void testGetCompanyProfileNotFound() {
        when(repository.getCompanyProfile(INCORPORATION_NUMBER)).thenReturn("{ 12345678 Company Not Found }");
        assertThrows(CorporateBodyNotFoundException.class, () -> corporateBodyService.getCompanyProfile(INCORPORATION_NUMBER));
    }

    @Test
    @DisplayName("Get company profile has a json mapping exception, returns a CompanyProfileMappingException")
    void testGetCompanyProfileDatabindException() throws JacksonException {
        String resultString = "{\"company_number\":\"" + INCORPORATION_NUMBER + "\"}";
        when(repository.getCompanyProfile(INCORPORATION_NUMBER)).thenReturn(resultString);
        when(mockJsonMapper.readValue(resultString, JsonNode.class)).thenThrow(DatabindException.class);
        assertThrows(CompanyProfileMappingException.class, () -> corporateBodyService.getCompanyProfile(INCORPORATION_NUMBER));
    }

    @Test
    @DisplayName("Get company profile has a json mapping exception, returns a CompanyProfileMappingException")
    void testGetCompanyProfileJacksonException() throws JacksonException {
        String resultString = "{\"company_number\":\"" + INCORPORATION_NUMBER + "\"}";
        when(repository.getCompanyProfile(INCORPORATION_NUMBER)).thenReturn(resultString);
        when(mockJsonMapper.readValue(resultString, JsonNode.class)).thenThrow(JacksonException.class);
        assertThrows(CompanyProfileMappingException.class, () -> corporateBodyService.getCompanyProfile(INCORPORATION_NUMBER));
    }
}

package uk.gov.ch.service.officer.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.gov.ch.exception.NoOfficersExistingException;
import uk.gov.ch.exception.OfficersMappingException;
import uk.gov.ch.model.officer.OfficerDataModel;
import uk.gov.ch.repository.officers.OfficersRepository;
import uk.gov.ch.transformers.officer.OfficersApiTransformer;
import uk.gov.companieshouse.api.model.officers.OfficersApi;

@ExtendWith(MockitoExtension.class)
public class OfficerServiceImplTest {

    @InjectMocks
    private OfficerServiceImpl officerServiceImpl;

    @Mock
    ObjectMapper mockObjectMapper;

    @Mock
    OfficersApiTransformer mockTransformer;

    @Mock
    OfficersRepository mockRepository;

    @Mock
    JsonNode mockNode;

    private static final String COMP_NO = "12345678";

    @Test
    @DisplayName("Test get officers returns a null String")
    void testGetOfficersReturnsIsNull() {
        when(mockRepository.getOfficers(COMP_NO)).thenReturn(null);
        assertThrows(NoOfficersExistingException.class, () -> {
            officerServiceImpl.getOfficers(COMP_NO);
        });
    }

    @Test
    @DisplayName("Test get officers returns an empty String")
    void testGetOfficersReturnsEmptyString() {
        when(mockRepository.getOfficers(COMP_NO)).thenReturn("");
        assertThrows(NoOfficersExistingException.class, () -> {
            officerServiceImpl.getOfficers(COMP_NO);
        });
    }

    @Test
    @DisplayName("Test get officers returns a company not found string")
    void testGetOfficersReturnsCompanyNotFoundString() {
        when(mockRepository.getOfficers(COMP_NO)).thenReturn("Company Not Found");
        assertThrows(NoOfficersExistingException.class, () -> {
            officerServiceImpl.getOfficers(COMP_NO);
        });
    }

    @Test
    @DisplayName("Test get officers returns a string indicating no officers found")
    void testGetOfficersNoOfficersFoundString() {
        when(mockRepository.getOfficers(COMP_NO)).thenReturn("], \"CreatedTime\": \"04-OCT-21 11.17.50.000000\"}");
        assertThrows(NoOfficersExistingException.class, () -> {
            officerServiceImpl.getOfficers(COMP_NO);
        });

    }

    @Test
    @DisplayName("Test get officers json processing exception thrown")
    void testGetOfficersJsonProcessingExceptionThrown() throws Exception {
        String resultString = "this is the result string";
        when(mockRepository.getOfficers(COMP_NO)).thenReturn(resultString);
        when(mockObjectMapper.readValue(resultString, JsonNode.class)).thenThrow(JsonProcessingException.class);
        assertThrows(OfficersMappingException.class, () -> {
            officerServiceImpl.getOfficers(COMP_NO);
        });
    }

    @Test
    @DisplayName("Test get officers is successful")
    void testGetOfficersIsSuccessful() throws Exception {
        String resultString = "this is the result string";
        List<OfficerDataModel> officerDataModelList = new ArrayList<>();
        when(mockRepository.getOfficers(COMP_NO)).thenReturn(resultString);
        when(mockObjectMapper.readValue(resultString, JsonNode.class)).thenReturn(mockNode);
        when(mockNode.get("officers")).thenReturn(mockNode);
        when(mockObjectMapper.convertValue(any(JsonNode.class),
                ArgumentMatchers.<TypeReference<List<OfficerDataModel>>>any())).thenReturn(officerDataModelList);
        when(mockTransformer.convert(officerDataModelList)).thenReturn(new OfficersApi());
        assertNotNull(officerServiceImpl.getOfficers(COMP_NO));
    }

}

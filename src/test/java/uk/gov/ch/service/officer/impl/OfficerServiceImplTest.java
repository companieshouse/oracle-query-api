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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.gov.ch.exception.NoOfficersExistingException;
import uk.gov.ch.exception.OfficersMappingException;
import uk.gov.ch.model.officer.OfficerDataModel;
import uk.gov.ch.repository.officers.OfficersRepository;
import uk.gov.ch.transformers.officer.OfficersApiTransformer;
import uk.gov.companieshouse.api.model.officers.OfficersApi;

@ExtendWith(MockitoExtension.class)
class OfficerServiceImplTest {

    @InjectMocks
    private OfficerServiceImpl officerServiceImpl;

    @Mock
    private ObjectMapper mockObjectMapper;

    @Mock
    private OfficersApiTransformer mockTransformer;

    @Mock
    private OfficersRepository mockRepository;

    @Mock
    private JsonNode mockNode;

    private static final String COMP_NO = "12345678";

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
            "Company Not Found",
            "], \"CreatedTime\": \"04-OCT-21 11.17.50.000000\"}"})
    @DisplayName("Test NoOfficersExistingException with repository.getOfficers")
    void testNoOfficersExistingExceptionOnConditions(String condition) {
        when(mockRepository.getOfficers(COMP_NO)).thenReturn(condition);
        assertThrows(NoOfficersExistingException.class, () ->
            officerServiceImpl.getOfficers(COMP_NO)
        );
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

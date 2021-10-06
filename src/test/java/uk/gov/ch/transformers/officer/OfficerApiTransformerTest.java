package uk.gov.ch.transformers.officer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import uk.gov.ch.model.officer.Identification;
import uk.gov.ch.model.officer.OfficerDataModel;
import uk.gov.ch.model.officer.OfficerIdentification;
import uk.gov.ch.model.officer.PreviousNameModel;
import uk.gov.ch.model.officer.ServiceAddress;
import uk.gov.companieshouse.api.model.common.Address;
import uk.gov.companieshouse.api.model.officers.CompanyOfficerApi;
import uk.gov.companieshouse.api.model.officers.FormerNamesApi;
import uk.gov.companieshouse.api.model.officers.IdentificationApi;
import uk.gov.companieshouse.api.model.officers.OfficersApi;

@ExtendWith(MockitoExtension.class)
class OfficerApiTransformerTest {
    
    private static final String ADDRESS_LINE_1 = "Address line 1";
    private static final String ADDRESS_LINE_2 = "Address line 2";
    private static final String APPOINTMENT_DATE = "20200101";
    private static final String CARE_OF_NAME = "Care of name";
    private static final String CORPORATE_OFFICER = "Corporate Officer";
    private static final String COUNTRY = "Country";
    private static final String DATE_OF_BIRTH = "20030303";
    private static final String EEA = "eea";
    private static final String FORENAME = "Forename";
    private static final String LEGAL_AUTHORITY = "Legal Authority";
    private static final String LEGAL_FORM = "Legal Form";
    private static final String LOCALITY = "Locality";
    private static final String MIDDLE_NAME = "Middle name";
    private static final String NATIONALITY = "Nationality";
    private static final String NON_EEA = "non-eea";
    private static final String OCCUPATION = "Occupation";
    private static final String OTHER_CORPORATE_BODY_OR_FIRM = "other-corporate-body-or-firm";
    private static final String PLACE_REGISTERED = "Place Registered";
    private static final String PO_BOX = "PO Box";
    private static final String POSTCODE = "POSTCODE";
    private static final String PREMISES = "Premises";
    private static final String PREVIOUS_FORENAME = "Previous Forename";
    private static final String PREVIOUS_SURNAME = "Previous surname";
    private static final String REGISTRATION_NUMBER = "Reg 12345";
    private static final String RESIGNATION_DATE = "20200202";
    private static final String REGION = "Region";
    private static final String SURNAME = "Surname";
    private static final String UK_LIMITED_COMPANY = "uk-limited-company";
    private static final String EMPTY_STRING = " ";
    private static final String ADDRESS_LINE_1_WHITE_SPACE = " Address line 1 ";
    private static final String ADDRESS_LINE_2_WHITE_SPACE = " Address line 2 ";
    private static final String CARE_OF_NAME_WHITE_SPACE = " Care of name ";
    private static final String COUNTRY_WHITE_SPACE = " Country ";
    private static final String LOCALITY_WHITE_SPACE = " Locality ";
    private static final String PO_BOX_WHITE_SPACE = " PO Box ";
    private static final String POSTCODE_WHITE_SPACE = " POSTCODE ";
    private static final String PREMISES_WHITE_SPACE = " Premises ";
    private static final String REGION_WHITE_SPACE = " Region ";
    private static final String USUAL_COUNTRY_WHITE_SPACE = " Usual Country ";

    
    private OfficersApiTransformer transformer;
    
    @BeforeEach
    void setUp() {
        transformer = new OfficersApiTransformer();
    }   

    @Test
    @DisplayName("Test conversion with human and EEA corporate officer")
    void testConvertWithHumanAndEeaCorporateOfficer() {
        OfficerDataModel humanOfficer = createHumanOfficerDataModel();
        OfficerDataModel corporateOfficer = createCorporateOfficerDataModel();
        corporateOfficer.getIdentification().setNonEea(null);
        corporateOfficer.getIdentification().setUkLimitedCompany(null);
        corporateOfficer.getIdentification().setOtherCorporateBodyOrFirm(null);
        
        List<OfficerDataModel> officerList = new ArrayList<>();
        officerList.add(humanOfficer);
        officerList.add(corporateOfficer);
        
        OfficersApi officersApi = transformer.convert(officerList);
        assertGenericValues(officersApi);
        assertEquals(2, officersApi.getResignedCount());
        
        // assert Human Officer Values
        assertHumanValues(officersApi.getItems().get(0));
        assertServiceAddress(officersApi.getItems().get(0).getAddress());
        assertPreviousNames(officersApi.getItems().get(0).getFormerNames());

        // assert Corporate Officer Values
        assertCompanyValues(officersApi.getItems().get(1));
        assertServiceAddress(officersApi.getItems().get(1).getAddress());
        assertIdentification(EEA, officersApi.getItems().get(1).getIdentification());
    }

    @Test
    @DisplayName("Test conversion with human and Non-EEA corporate officer")
    void testConvertWithHumanAndNonEeaCorporateOfficer() {
        OfficerDataModel humanOfficer = createHumanOfficerDataModel();
        OfficerDataModel corporateOfficer = createCorporateOfficerDataModel();
        corporateOfficer.getIdentification().setEea(null);
        corporateOfficer.getIdentification().setUkLimitedCompany(null);
        corporateOfficer.getIdentification().setOtherCorporateBodyOrFirm(null);
        
        List<OfficerDataModel> officerList = new ArrayList<>();
        officerList.add(humanOfficer);
        officerList.add(corporateOfficer);
        
        OfficersApi officersApi = transformer.convert(officerList);
        assertGenericValues(officersApi);
        assertEquals(2, officersApi.getResignedCount());
        
        // assert Human Officer Values
        assertHumanValues(officersApi.getItems().get(0));
        assertServiceAddress(officersApi.getItems().get(0).getAddress());
        assertPreviousNames(officersApi.getItems().get(0).getFormerNames());

        // assert Corporate Officer Values
        assertCompanyValues(officersApi.getItems().get(1));
        assertServiceAddress(officersApi.getItems().get(1).getAddress());
        assertIdentification(NON_EEA, officersApi.getItems().get(1).getIdentification());
    }
    
    @Test
    @DisplayName("Test conversion with human and UK limited corporate officer")
    void testConvertWithHumanAndUkLimitedCorporateOfficer() {
        OfficerDataModel humanOfficer = createHumanOfficerDataModel();
        OfficerDataModel corporateOfficer = createCorporateOfficerDataModel();
        corporateOfficer.getIdentification().setEea(null);
        corporateOfficer.getIdentification().setNonEea(null);
        corporateOfficer.getIdentification().setOtherCorporateBodyOrFirm(null);
        
        List<OfficerDataModel> officerList = new ArrayList<>();
        officerList.add(humanOfficer);
        officerList.add(corporateOfficer);
        
        OfficersApi officersApi = transformer.convert(officerList);
        assertGenericValues(officersApi);
        assertEquals(2, officersApi.getResignedCount());
        
        // assert Human Officer Values
        assertHumanValues(officersApi.getItems().get(0));
        assertServiceAddress(officersApi.getItems().get(0).getAddress());
        assertPreviousNames(officersApi.getItems().get(0).getFormerNames());

        // assert Corporate Officer Values
        assertCompanyValues(officersApi.getItems().get(1));
        assertServiceAddress(officersApi.getItems().get(1).getAddress());
        assertIdentification(UK_LIMITED_COMPANY, officersApi.getItems().get(1).getIdentification());
    }
    
    @Test
    @DisplayName("Test conversion with human and other corporate firm officer")
    void testConvertWithHumanAndOtherCorporateFirmOfficer() {
        OfficerDataModel humanOfficer = createHumanOfficerDataModel();
        OfficerDataModel corporateOfficer = createCorporateOfficerDataModel();
        corporateOfficer.getIdentification().setEea(null);
        corporateOfficer.getIdentification().setNonEea(null);
        corporateOfficer.getIdentification().setUkLimitedCompany(null);
        
        List<OfficerDataModel> officerList = new ArrayList<>();
        officerList.add(humanOfficer);
        officerList.add(corporateOfficer);
        
        OfficersApi officersApi = transformer.convert(officerList);
        assertGenericValues(officersApi);
        assertEquals(2, officersApi.getResignedCount());
        
        // assert Human Officer Values
        assertHumanValues(officersApi.getItems().get(0));
        assertServiceAddress(officersApi.getItems().get(0).getAddress());
        assertPreviousNames(officersApi.getItems().get(0).getFormerNames());

        // assert Corporate Officer Values
        assertCompanyValues(officersApi.getItems().get(1));
        assertServiceAddress(officersApi.getItems().get(1).getAddress());
        assertIdentification(OTHER_CORPORATE_BODY_OR_FIRM, officersApi.getItems().get(1).getIdentification());
    }
    
    @Test
    @DisplayName("Test conversion where all identification options are null")
    void testConvertWithCorporateOfficerIdentificationOptionsNull() {
        OfficerDataModel corporateOfficer = createCorporateOfficerDataModel();
        corporateOfficer.getIdentification().setEea(null);
        corporateOfficer.getIdentification().setNonEea(null);
        corporateOfficer.getIdentification().setUkLimitedCompany(null);
        corporateOfficer.getIdentification().setOtherCorporateBodyOrFirm(null);
        
        List<OfficerDataModel> officerList = new ArrayList<>();
        officerList.add(corporateOfficer);
        
        OfficersApi officersApi = transformer.convert(officerList);
        assertNull(officersApi.getItems().get(0).getIdentification());
    }
    
    @Test
    @DisplayName("test conversion with corporate officer without identification section")
    void testConvertWithCorporateOfficerNoIdentification() {
        OfficerDataModel corporateOfficer = createCorporateOfficerDataModel();
        corporateOfficer.setIdentification(null);
        List<OfficerDataModel> officerList = new ArrayList<>();
        officerList.add(corporateOfficer);
        
        OfficersApi officersApi = transformer.convert(officerList);
        assertCompanyValues(officersApi.getItems().get(0));
        assertServiceAddress(officersApi.getItems().get(0).getAddress());
        assertNull(officersApi.getItems().get(0).getIdentification());
        
    }
    
    @Test
    @DisplayName("Test conversion where the service address is null")
    void testConversionWhereAddressIsNull() {
        OfficerDataModel humanOfficer = createHumanOfficerDataModel();
        humanOfficer.setServiceAddress(null);
        OfficerDataModel corporateOfficer = createCorporateOfficerDataModel();
        corporateOfficer.setServiceAddress(null);
        
        List<OfficerDataModel> officerList = new ArrayList<>();
        officerList.add(humanOfficer);
        officerList.add(corporateOfficer);
        
        OfficersApi officersApi = transformer.convert(officerList);
        assertNull(officersApi.getItems().get(0).getAddress());
        assertNull(officersApi.getItems().get(1).getAddress());
    }

    @Test
    @DisplayName("Test conversion where the service address holds empty string values")
    void testConversionWhereAddressFieldsValuesEmptyStrings() {
        OfficerDataModel humanOfficer = createHumanOfficerDataModel();
        humanOfficer.setServiceAddress(createServiceAddressWithEmptyStrings());

        List<OfficerDataModel> officerList = new ArrayList<>();
        officerList.add(humanOfficer);

        OfficersApi officersApi = transformer.convert(officerList);
        assertNull(officersApi.getItems().get(0).getAddress().getAddressLine1());
        assertNull(officersApi.getItems().get(0).getAddress().getAddressLine2());
        assertNull(officersApi.getItems().get(0).getAddress().getCareOf());
        assertNull(officersApi.getItems().get(0).getAddress().getCountry());
        assertNull(officersApi.getItems().get(0).getAddress().getLocality());
        assertNull(officersApi.getItems().get(0).getAddress().getPoBox());
        assertNull(officersApi.getItems().get(0).getAddress().getPostalCode());
        assertNull(officersApi.getItems().get(0).getAddress().getPremises());
        assertNull(officersApi.getItems().get(0).getAddress().getRegion());
    }

    @Test
    @DisplayName("Test conversion where the service address values are trimmed of whitespace ")
    void testConversionWhereAddressFieldsValuesTrailingWhiteSpace() {
        OfficerDataModel humanOfficer = createHumanOfficerDataModel();
        humanOfficer.setServiceAddress(createServiceAddressWithTrailingWhiteSpace());

        List<OfficerDataModel> officerList = new ArrayList<>();
        officerList.add(humanOfficer);

        OfficersApi officersApi = transformer.convert(officerList);
        assertEquals(ADDRESS_LINE_1, officersApi.getItems().get(0).getAddress().getAddressLine1());
        assertEquals(ADDRESS_LINE_2, officersApi.getItems().get(0).getAddress().getAddressLine2());
        assertEquals(CARE_OF_NAME, officersApi.getItems().get(0).getAddress().getCareOf());
        assertEquals(COUNTRY, officersApi.getItems().get(0).getAddress().getCountry());
        assertEquals(LOCALITY, officersApi.getItems().get(0).getAddress().getLocality());
        assertEquals(PO_BOX, officersApi.getItems().get(0).getAddress().getPoBox());
        assertEquals(POSTCODE, officersApi.getItems().get(0).getAddress().getPostalCode());
        assertEquals(PREMISES, officersApi.getItems().get(0).getAddress().getPremises());
        assertEquals(REGION, officersApi.getItems().get(0).getAddress().getRegion());

    }

    @Test
    @DisplayName("Test conversion with human officers null previous names")
    void testConvertWithHumanOfficerNullPreviousName() {
        OfficerDataModel humanOfficer = createHumanOfficerDataModel();
        humanOfficer.setPreviousNameArray(null);
        List<OfficerDataModel> officerList = new ArrayList<>();
        officerList.add(humanOfficer);
        
        OfficersApi officersApi = transformer.convert(officerList);
        assertEquals(1, officersApi.getResignedCount());
        assertNull(officersApi.getItems().get(0).getFormerNames());
        
    }
    
    @Test
    @DisplayName("Test conversion with human officers with null name sections")
    void testConvertWithHumanOfficerNameCombinations() {
        OfficerDataModel nullForenameOfficer = createHumanOfficerDataModel();
        OfficerDataModel nullMiddleNameOfficer = createHumanOfficerDataModel();
        OfficerDataModel nullSurnameOfficer = createHumanOfficerDataModel();
        nullForenameOfficer.setForename(null);
        nullMiddleNameOfficer.setMiddleName(null);
        nullSurnameOfficer.setSurname(null);
        List<OfficerDataModel> officerList = new ArrayList<>();
        officerList.add(nullForenameOfficer);
        officerList.add(nullMiddleNameOfficer);
        officerList.add(nullSurnameOfficer);
        
        OfficersApi officersApi = transformer.convert(officerList);
        assertEquals(MIDDLE_NAME + " " + SURNAME, officersApi.getItems().get(0).getName());
        assertEquals(FORENAME + " " + SURNAME, officersApi.getItems().get(1).getName());
        assertEquals(FORENAME + " " + MIDDLE_NAME, officersApi.getItems().get(2).getName());
    }
    
    @Test
    @DisplayName("Test conversion with human officer null date of birth")
    void testConvertWithHumanOfficersNullDateOfBirth() {
        OfficerDataModel humanOfficer = createHumanOfficerDataModel();
        humanOfficer.setDateOfBirth(null);
        List<OfficerDataModel> officerList = new ArrayList<>();
        officerList.add(humanOfficer);
        OfficersApi officersApi = transformer.convert(officerList);
        assertNull(officersApi.getItems().get(0).getDateOfBirth());
    }
    
    @Test
    @DisplayName("Test conversion with human officer empty string date of birth")
    void testConvertWithHumanOfficersEmptyStringDateOfBirth() {
        OfficerDataModel humanOfficer = createHumanOfficerDataModel();
        humanOfficer.setDateOfBirth("");
        List<OfficerDataModel> officerList = new ArrayList<>();
        officerList.add(humanOfficer);
        OfficersApi officersApi = transformer.convert(officerList);
        assertNull(officersApi.getItems().get(0).getDateOfBirth());
    }
    
    @Test
    @DisplayName("Test conversion with human officers empty previous names list")
    void testConvertWithHumanOfficerEmptyPreviousNames() {
        OfficerDataModel humanOfficer = createHumanOfficerDataModel();
        humanOfficer.setPreviousNameArray(new ArrayList<PreviousNameModel>());
        List<OfficerDataModel> officerList = new ArrayList<>();
        officerList.add(humanOfficer);
        OfficersApi officersApi = transformer.convert(officerList);
        assertEquals(1, officersApi.getResignedCount());
        assertNull(officersApi.getItems().get(0).getFormerNames());
    }
    
    @Test
    @DisplayName("Test conversion where the resignation dates are null")
    void testConvertWithNullResignationDates() {
        OfficerDataModel humanOfficer = createHumanOfficerDataModel();
        humanOfficer.setResignationDate(null);
        OfficerDataModel corporateOfficer = createCorporateOfficerDataModel();
        corporateOfficer.setResignationDate(null);
        
        List<OfficerDataModel> officerList = new ArrayList<>();
        officerList.add(humanOfficer);
        officerList.add(corporateOfficer);
        
        OfficersApi officersApi = transformer.convert(officerList);
        assertEquals(2, officersApi.getTotalResults());
        assertEquals(0, officersApi.getResignedCount());
        assertEquals(2, officersApi.getItemsPerPage());
        
    }
    
    private void assertCompanyValues(CompanyOfficerApi companyOfficerApi) {
        assertEquals(CORPORATE_OFFICER, companyOfficerApi.getName());
        
    }

    private void assertGenericValues(OfficersApi officersApi) {
        assertEquals(2, officersApi.getItemsPerPage());
        assertEquals(0, officersApi.getStartIndex());
        assertEquals(2, officersApi.getTotalResults());
        assertEquals("officer-list", officersApi.getKind());
    }
    
    private void assertHumanValues(CompanyOfficerApi officerApi) {
        assertEquals(NATIONALITY, officerApi.getNationality());
        assertEquals(OCCUPATION, officerApi.getOccupation());
    }
    
    private void assertIdentification(String type, IdentificationApi identification) {
        assertEquals(type, identification.getIdentificationType());
        assertEquals(LEGAL_AUTHORITY, identification.getLegalAuthority());
        assertEquals(LEGAL_FORM, identification.getLegalForm());
        assertEquals(PLACE_REGISTERED, identification.getPlaceRegistered());
        assertEquals(REGISTRATION_NUMBER, identification.getRegistrationNumber());      
    }
    
    private void assertPreviousNames(List<FormerNamesApi> formerNames) {
        assertEquals(PREVIOUS_FORENAME, formerNames.get(0).getForenames());
        assertEquals(PREVIOUS_SURNAME, formerNames.get(0).getSurname());
    }
    
    private void assertServiceAddress(Address address) {
        assertEquals(ADDRESS_LINE_1, address.getAddressLine1());
        assertEquals(ADDRESS_LINE_2, address.getAddressLine2());
        assertEquals(CARE_OF_NAME, address.getCareOf());
        assertEquals(COUNTRY, address.getCountry());
        assertEquals(LOCALITY, address.getLocality());
        assertEquals(POSTCODE, address.getPostalCode());
        assertEquals(PO_BOX, address.getPoBox());
        assertEquals(PREMISES, address.getPremises());
        assertEquals(REGION, address.getRegion());
    }
    
    private OfficerDataModel createBaseOfficerDataModel() {
        OfficerDataModel officerDataModel = new OfficerDataModel();
        officerDataModel.setAppointmentDate(APPOINTMENT_DATE);
        officerDataModel.setResignationDate(RESIGNATION_DATE);
        officerDataModel.setServiceAddress(createServiceAddress());
        
        return officerDataModel;
    }
    
    private OfficerDataModel createCorporateOfficerDataModel() {
        OfficerDataModel officerDataModel = createBaseOfficerDataModel();
        officerDataModel.setCorporateInd("TRUE ");
        officerDataModel.setSurname(CORPORATE_OFFICER);
        officerDataModel.setIdentification(createIdentification());
        
        return officerDataModel;
        
    }
    
    private OfficerDataModel createHumanOfficerDataModel() {
        OfficerDataModel officerDataModel = createBaseOfficerDataModel();
        officerDataModel.setDateOfBirth(DATE_OF_BIRTH);
        officerDataModel.setCorporateInd("FALSE");
        officerDataModel.setForename(FORENAME);
        officerDataModel.setMiddleName(MIDDLE_NAME);
        officerDataModel.setSurname(SURNAME);
        officerDataModel.setNationality(NATIONALITY);
        officerDataModel.setOccupation(OCCUPATION);
        officerDataModel.setPreviousNameArray(createPreviousNamesList());
        
        return officerDataModel;
    }
    
    private ServiceAddress createServiceAddress() {
        ServiceAddress serviceAddress = new ServiceAddress();
        serviceAddress.setAddressLine1(ADDRESS_LINE_1);
        serviceAddress.setAddressLine2(ADDRESS_LINE_2);
        serviceAddress.setCareOfName(CARE_OF_NAME);
        serviceAddress.setCountry(COUNTRY);
        serviceAddress.setLocality(LOCALITY);
        serviceAddress.setPoBox(PO_BOX);
        serviceAddress.setPostalCode(POSTCODE);
        serviceAddress.setPremises(PREMISES);
        serviceAddress.setRegion(REGION);
        serviceAddress.setUsualCountryOfResidence("Usual Country");
        
        return serviceAddress;
    }

    private ServiceAddress createServiceAddressWithEmptyStrings() {
        ServiceAddress serviceAddress = new ServiceAddress();
        serviceAddress.setAddressLine1(EMPTY_STRING);
        serviceAddress.setAddressLine2(EMPTY_STRING);
        serviceAddress.setCareOfName(EMPTY_STRING);
        serviceAddress.setCountry(EMPTY_STRING);
        serviceAddress.setLocality(EMPTY_STRING);
        serviceAddress.setPoBox(EMPTY_STRING);
        serviceAddress.setPostalCode(EMPTY_STRING);
        serviceAddress.setPremises(EMPTY_STRING);
        serviceAddress.setRegion(EMPTY_STRING);
        serviceAddress.setUsualCountryOfResidence(EMPTY_STRING);

        return serviceAddress;
    }

    private ServiceAddress createServiceAddressWithTrailingWhiteSpace() {
        ServiceAddress serviceAddress = new ServiceAddress();
        serviceAddress.setAddressLine1(ADDRESS_LINE_1_WHITE_SPACE);
        serviceAddress.setAddressLine2(ADDRESS_LINE_2_WHITE_SPACE);
        serviceAddress.setCareOfName(CARE_OF_NAME_WHITE_SPACE);
        serviceAddress.setCountry(COUNTRY_WHITE_SPACE);
        serviceAddress.setLocality(LOCALITY_WHITE_SPACE);
        serviceAddress.setPoBox(PO_BOX_WHITE_SPACE);
        serviceAddress.setPostalCode(POSTCODE_WHITE_SPACE);
        serviceAddress.setPremises(PREMISES_WHITE_SPACE);
        serviceAddress.setRegion(REGION_WHITE_SPACE);
        serviceAddress.setUsualCountryOfResidence(USUAL_COUNTRY_WHITE_SPACE);

        return serviceAddress;
    }
    
    private Identification createIdentification() {
        Identification identification = new Identification();
        OfficerIdentification officerIdentification = new OfficerIdentification();
        officerIdentification.setLegalAuthority(LEGAL_AUTHORITY);
        officerIdentification.setLegalForm(LEGAL_FORM);
        officerIdentification.setPlaceRegistered(PLACE_REGISTERED);
        officerIdentification.setRegistrationNumber(REGISTRATION_NUMBER);
        
        identification.setEea(officerIdentification);
        identification.setNonEea(officerIdentification);
        identification.setOtherCorporateBodyOrFirm(officerIdentification);
        identification.setUkLimitedCompany(officerIdentification);
        
        return identification;
    }
    
    private List<PreviousNameModel> createPreviousNamesList() {
        List<PreviousNameModel> previousNamesList = new ArrayList<>();
        PreviousNameModel previousName1 = new PreviousNameModel();
        previousName1.setPreviousForename(PREVIOUS_FORENAME);
        previousName1.setPreviousSurname(PREVIOUS_SURNAME);
        previousNamesList.add(previousName1);
        return previousNamesList;
    }
}

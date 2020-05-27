package uk.gov.ch.transformers.emergencyauthcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.ch.model.emergencyauthcode.jsonDataModels.CorporateBodyAppointment;
import uk.gov.ch.model.emergencyauthcode.sqlDataModels.CorporateBodyAppointmentDataModel;
import uk.gov.ch.model.emergencyauthcode.sqlDataModels.OfficerDetailDataModel;
import uk.gov.ch.model.emergencyauthcode.sqlDataModels.UsualResidentialAddressDataModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmergencyOfficersTransformerTest {

    @InjectMocks
    EmergencyOfficersTransformer transformer;

    @Test
    @DisplayName("Convert incoming officers details from Oracle to outgoing officers details for JSON")
    public void testConvertReturnJsonModel() {
        List<CorporateBodyAppointment> convertedResult = transformer.convert(getMockEmergencyAuthCodeRepo());

        assertEquals(1, convertedResult.size());
        assertEquals("United Kingdom", convertedResult.get(0).getCountryOfResidence());
        assertEquals("1", convertedResult.get(0).getDateOfBirth().getMonth());
        assertEquals("1990", convertedResult.get(0).getDateOfBirth().getYear());
    }

    private List<CorporateBodyAppointmentDataModel> getMockEmergencyAuthCodeRepo() {
        List<CorporateBodyAppointmentDataModel> corporateBodyAppointments = new ArrayList<>();
        corporateBodyAppointments.add(new CorporateBodyAppointmentDataModel() {{
            setCorporateBodyAppointmentId(Long.parseLong("123"));
            setOccupationDescription("Description 1");
            setAppointmentDate(LocalDate.of(2010, 01, 01));

            setOfficerDetail(new OfficerDetailDataModel() {{
                setOfficerId(Long.parseLong("123"));
                setForename1("Forename");
                setSurname("Surname");
                setDateOfBirth(LocalDate.of(1990, 01, 01));
                setOfficerNationality("British");
                setUsualResidentialCountry("United Kingdom");
                setOccupationDescription("Occupation");

                setUsualResidentialAddress(new UsualResidentialAddressDataModel() {{
                    setUsualResidentialAddressId(Long.parseLong("123"));
                    setPremises("123");
                    setAddressLine1("AddressLine1");
                    setLocality("Locality");
                    setPostCode("Postcode");
                }});
            }});
        }});

        return corporateBodyAppointments;
    }
}

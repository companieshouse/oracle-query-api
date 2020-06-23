package uk.gov.ch.service.emergencyauthcode;

import org.springframework.data.domain.Pageable;
import uk.gov.ch.model.emergencyauthcode.jsondatamodels.CorporateBodyAppointment;
import uk.gov.ch.model.emergencyauthcode.jsondatamodels.CorporateBodyAppointments;
import uk.gov.ch.model.emergencyauthcode.jsondatamodels.CorporateBodyEFilingStatus;

public interface EmergencyOfficersService {

    CorporateBodyAppointments getEligibleOfficersEmergencyAuthCode(String incorporationNumber, Pageable pageable);

    CorporateBodyAppointment getEligibleOfficer(String incorporationNumber, String officerId);

    CorporateBodyEFilingStatus checkIfEFiledLastThirtyDays(String incorporationNumber);
}

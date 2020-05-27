package uk.gov.ch.service.emergencyauthcode;

import uk.gov.ch.model.emergencyauthcode.jsondatamodels.CorporateBodyAppointment;
import uk.gov.ch.model.emergencyauthcode.jsondatamodels.CorporateBodyAppointments;

public interface EmergencyOfficersService {

    CorporateBodyAppointments getEligibleOfficersEmergencyAuthCode(String incorporationNumber);

    CorporateBodyAppointment getEligibleOfficer(String incorporationNumber, String officerId);
}

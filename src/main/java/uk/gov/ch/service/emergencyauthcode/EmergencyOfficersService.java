package uk.gov.ch.service.emergencyauthcode;

import uk.gov.ch.model.emergencyauthcode.jsonDataModels.CorporateBodyAppointment;
import uk.gov.ch.model.emergencyauthcode.jsonDataModels.CorporateBodyAppointments;

public interface EmergencyOfficersService {

    CorporateBodyAppointments getEligibleOfficersEmergencyAuthCode(String incorporationNumber);

    CorporateBodyAppointment getEligibleOfficer(String incorporationNumber, String officerId);
}

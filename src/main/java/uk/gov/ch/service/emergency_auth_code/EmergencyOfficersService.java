package uk.gov.ch.service.emergency_auth_code;

import uk.gov.ch.model.emergency_auth_code.jsonDataModels.CorporateBodyAppointment;
import uk.gov.ch.model.emergency_auth_code.jsonDataModels.CorporateBodyAppointments;

public interface EmergencyOfficersService {

    CorporateBodyAppointments getEligibleOfficersEmergencyAuthCode(String incorporationNumber);

    CorporateBodyAppointment getEligibleOfficer(String incorporationNumber, String officerId);
}

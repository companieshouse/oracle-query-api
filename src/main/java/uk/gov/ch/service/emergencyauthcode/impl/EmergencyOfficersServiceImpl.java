package uk.gov.ch.service.emergencyauthcode.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.model.emergencyauthcode.jsondatamodels.CorporateBodyAppointment;
import uk.gov.ch.model.emergencyauthcode.jsondatamodels.CorporateBodyAppointments;
import uk.gov.ch.model.emergencyauthcode.sqldatamodels.CorporateBodyAppointmentDataModel;
import uk.gov.ch.repository.officers.EmergencyAuthCodeEligibleOfficersRepository;
import uk.gov.ch.service.emergencyauthcode.EmergencyOfficersService;
import uk.gov.ch.transformers.emergencyauthcode.EmergencyOfficersTransformer;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

import java.util.List;

@Service
public class EmergencyOfficersServiceImpl implements EmergencyOfficersService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private EmergencyAuthCodeEligibleOfficersRepository emergencyAuthCodeEligibleOfficersRepository;

    @Autowired
    private EmergencyOfficersTransformer emergencyOfficersTransformer;

    public CorporateBodyAppointments getEligibleOfficersEmergencyAuthCode(String incorporationNumber) {

        LOGGER.info("Calling repository to retrieve eligible officers for " + incorporationNumber);
        List<CorporateBodyAppointment> corporateBodyAppointmentList =
                emergencyOfficersTransformer.convert(emergencyAuthCodeEligibleOfficersRepository.findEligibleOfficersEmergencyAuthCode(incorporationNumber));

        CorporateBodyAppointments corporateBodyAppointments = new CorporateBodyAppointments();

        corporateBodyAppointments.setItemsPerPage(50);
        corporateBodyAppointments.setTotalResults(corporateBodyAppointmentList.size());
        corporateBodyAppointments.setStartIndex(0);
        corporateBodyAppointments.setItems(corporateBodyAppointmentList);

        return corporateBodyAppointments;
    }

    public CorporateBodyAppointment getEligibleOfficer(String incorporationNumber, String officerId) {

        LOGGER.info("Calling repository to retrieve officer " + officerId + " for company number " + incorporationNumber);

        CorporateBodyAppointmentDataModel eligibleOfficersDataModel = emergencyAuthCodeEligibleOfficersRepository.findEligibleOfficer(incorporationNumber, officerId);

        if (eligibleOfficersDataModel == null) {
            return null;
        }

        return emergencyOfficersTransformer.convert(eligibleOfficersDataModel);
    }
}

package uk.gov.ch.service.emergencyauthcode.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.model.emergencyauthcode.jsondatamodels.CorporateBodyAppointment;
import uk.gov.ch.model.emergencyauthcode.jsondatamodels.CorporateBodyAppointments;
import uk.gov.ch.model.emergencyauthcode.jsondatamodels.CorporateBodyEFilingStatus;
import uk.gov.ch.model.emergencyauthcode.sqldatamodels.CorporateBodyAppointmentDataModel;
import uk.gov.ch.repository.officers.EmergencyAuthCodeEligibleOfficersRepository;
import uk.gov.ch.service.emergencyauthcode.EmergencyOfficersService;
import uk.gov.ch.transformers.emergencyauthcode.EmergencyOfficersTransformer;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@Service
public class EmergencyOfficersServiceImpl implements EmergencyOfficersService {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private EmergencyAuthCodeEligibleOfficersRepository emergencyAuthCodeEligibleOfficersRepository;

    @Autowired
    private EmergencyOfficersTransformer emergencyOfficersTransformer;

    public CorporateBodyAppointments getEligibleOfficersEmergencyAuthCode(
            String incorporationNumber, Pageable pageable) {

        LOGGER.info("Calling repository to retrieve eligible officers for " + incorporationNumber);

        Page<CorporateBodyAppointmentDataModel> officersPage = emergencyAuthCodeEligibleOfficersRepository.findEligibleOfficersEmergencyAuthCode(
                incorporationNumber, pageable);

        List<CorporateBodyAppointment> corporateBodyAppointmentList =
                emergencyOfficersTransformer.convert(officersPage);

        CorporateBodyAppointments corporateBodyAppointments = new CorporateBodyAppointments();

        corporateBodyAppointments.setItemsPerPage(officersPage.getSize());
        corporateBodyAppointments.setTotalResults((int) officersPage.getTotalElements());
        corporateBodyAppointments.setStartIndex(officersPage.getNumber());
        corporateBodyAppointments.setItems(corporateBodyAppointmentList);

        return corporateBodyAppointments;
    }

    public CorporateBodyAppointment getEligibleOfficer(String incorporationNumber,
            String officerId) {

        LOGGER.info("Calling repository to retrieve officer " + officerId + " for company number "
                + incorporationNumber);

        CorporateBodyAppointmentDataModel eligibleOfficersDataModel = emergencyAuthCodeEligibleOfficersRepository.findEligibleOfficer(
                incorporationNumber, officerId);

        if (eligibleOfficersDataModel == null) {
            return null;
        }

        return emergencyOfficersTransformer.convert(eligibleOfficersDataModel);
    }

    public CorporateBodyEFilingStatus checkIfEFiledLastThirtyDays(String incorporationNumber) {

        LOGGER.info("Checking if the company has filed in the last thirty days: "
                + incorporationNumber);

        Boolean eFilingStatus =
                emergencyAuthCodeEligibleOfficersRepository.findEFilingsInLastThirtyDays(
                        incorporationNumber) > 0L;

        CorporateBodyEFilingStatus corporateBodyEFilingStatus = new CorporateBodyEFilingStatus();
        corporateBodyEFilingStatus.setEfilingFoundInPeriod(eFilingStatus);

        return corporateBodyEFilingStatus;
    }
}

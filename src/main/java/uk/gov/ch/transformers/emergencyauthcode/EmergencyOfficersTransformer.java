package uk.gov.ch.transformers.emergencyauthcode;

import org.springframework.stereotype.Component;
import uk.gov.ch.model.emergencyauthcode.jsondatamodels.CorporateBodyAppointment;
import uk.gov.ch.model.emergencyauthcode.jsondatamodels.CorporateBodyAppointmentDateOfBirth;
import uk.gov.ch.model.emergencyauthcode.jsondatamodels.CorporateBodyAppointmentURA;
import uk.gov.ch.model.emergencyauthcode.sqldatamodels.CorporateBodyAppointmentDataModel;
import uk.gov.ch.model.emergencyauthcode.sqldatamodels.UsualResidentialAddressDataModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmergencyOfficersTransformer {

    private static final String NULL_VALUE = "null";

    public CorporateBodyAppointment convert(CorporateBodyAppointmentDataModel appointmentDataModel) {
        CorporateBodyAppointment appointment = new CorporateBodyAppointment();

        appointment.setId(appointmentDataModel.getOfficerDetail().getOfficerId().toString());
        appointment.setForename(appointmentDataModel.getOfficerDetail().getForename1());
        appointment.setSurname(appointmentDataModel.getOfficerDetail().getSurname());
        appointment.setOfficerRole("DIRECTOR"); // Hardcoded to director for this release
        appointment.setDateOfBirth(getDateOfBirthJsonModel(appointmentDataModel.getOfficerDetail().getDateOfBirth()));
        appointment.setAppointedOn(appointmentDataModel.getAppointmentDate());
        appointment.setNationality(appointmentDataModel.getOfficerDetail().getOfficerNationality());
        appointment.setCountryOfResidence(appointmentDataModel.getOfficerDetail().getUsualResidentialCountry());
        appointment.setOccupation(appointmentDataModel.getOccupationDescription());
        appointment.setUsualResidentialAddress(getUraJsonModel(appointmentDataModel.getOfficerDetail().getUsualResidentialAddress()));

        return appointment;
    }

    public List<CorporateBodyAppointment> convert(List<CorporateBodyAppointmentDataModel> appointmentsDataModel) {

        List<CorporateBodyAppointment> appointmentsJsonModelList = new ArrayList<>();

        for (CorporateBodyAppointmentDataModel appointmentDataModel : appointmentsDataModel) {
            appointmentsJsonModelList.add(convert(appointmentDataModel));
        }

        return appointmentsJsonModelList;
    }

    private CorporateBodyAppointmentDateOfBirth getDateOfBirthJsonModel(LocalDate dateOfBirth) {
        CorporateBodyAppointmentDateOfBirth dateOfBirthJson = new CorporateBodyAppointmentDateOfBirth();

        dateOfBirthJson.setMonth(String.valueOf(dateOfBirth.getMonthValue()));
        dateOfBirthJson.setYear(String.valueOf(dateOfBirth.getYear()));

        return dateOfBirthJson;
    }

    private CorporateBodyAppointmentURA getUraJsonModel(UsualResidentialAddressDataModel uraDataModel) {
        CorporateBodyAppointmentURA corporateBodyUra = new CorporateBodyAppointmentURA();

        corporateBodyUra.setId(uraDataModel.getUsualResidentialAddressId().toString());
        if (uraDataModel.getPoBox() != NULL_VALUE) {
            corporateBodyUra.setPoBox(uraDataModel.getPoBox());
        }
        corporateBodyUra.setPremises(uraDataModel.getPremises());
        corporateBodyUra.setAddressLine1(uraDataModel.getAddressLine1());
        if (uraDataModel.getAddressLine2() != NULL_VALUE) {
            corporateBodyUra.setAddressLine2(uraDataModel.getAddressLine2());
        }
        corporateBodyUra.setLocality(uraDataModel.getLocality());
        if (uraDataModel.getRegion() != NULL_VALUE) {
            corporateBodyUra.setRegion(uraDataModel.getRegion());
        }
        if (uraDataModel.getCountry() != NULL_VALUE) {
            corporateBodyUra.setCountry(uraDataModel.getCountry());
        }
        corporateBodyUra.setPostcode(uraDataModel.getPostCode());

        return corporateBodyUra;
    }
}

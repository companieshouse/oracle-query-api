package uk.gov.ch.repository.officers;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.gov.ch.model.emergencyauthcode.sqldatamodels.CorporateBodyAppointmentDataModel;

import java.util.List;

public interface EmergencyAuthCodeEligibleOfficersRepository extends PagingAndSortingRepository<CorporateBodyAppointmentDataModel, Long> {

    @Query(value = "select cba.CORPORATE_BODY_APPOINTMENT_ID, cba.APPOINTMENT_DATE, cba.OCCUPATION_DESC, cba.OFFICER_DETAIL_ID " +
            "from CORPORATE_BODY cb, CORPORATE_BODY_APPOINTMENT cba, OFFICER o, OFFICER_DETAIL od " +
            "where cb.INCORPORATION_NUMBER = ?1 " +
            "and cb.CORPORATE_BODY_ID = cba.CORPORATE_BODY_ID " +
            "and cba.OFFICER_ID = od.OFFICER_ID " +
            "and cba.OFFICER_ID = o.OFFICER_ID " +
            "and cba.APPOINTMENT_TYPE_ID = 2 " +
            "and cba.RESIGNATION_IND = 'N' " +
            "and od.OFFICER_DISQUALIFICATION_IND = 'N' " +
            "and o.CORPORATE_OFFICER_IND = 'N'",
            nativeQuery = true)
    List<CorporateBodyAppointmentDataModel> findEligibleOfficersEmergencyAuthCode(
            String incorporationNumber);

    @Query(value = "select cba.CORPORATE_BODY_APPOINTMENT_ID, cba.APPOINTMENT_DATE, cba.OCCUPATION_DESC, cba.OFFICER_DETAIL_ID " +
            "from CORPORATE_BODY cb, CORPORATE_BODY_APPOINTMENT cba, OFFICER o, OFFICER_DETAIL od " +
            "where cb.INCORPORATION_NUMBER = ?1 " +
            "and cb.CORPORATE_BODY_ID = cba.CORPORATE_BODY_ID " +
            "and cba.OFFICER_ID = od.OFFICER_ID " +
            "and cba.OFFICER_ID = o.OFFICER_ID " +
            "and cba.APPOINTMENT_TYPE_ID = 2 " +
            "and cba.RESIGNATION_IND = 'N' " +
            "and od.OFFICER_DISQUALIFICATION_IND = 'N' " +
            "and o.CORPORATE_OFFICER_IND = 'N' " +
            "and od.OFFICER_DETAIL_ID = ?2",
            nativeQuery = true)
    CorporateBodyAppointmentDataModel findEligibleOfficer(
            String incorporationNumber, String officerId);
}
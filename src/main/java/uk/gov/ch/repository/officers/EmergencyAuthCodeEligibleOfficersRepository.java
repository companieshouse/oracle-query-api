package uk.gov.ch.repository.officers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.gov.ch.model.emergencyauthcode.sqldatamodels.CorporateBodyAppointmentDataModel;

public interface EmergencyAuthCodeEligibleOfficersRepository extends
        PagingAndSortingRepository<CorporateBodyAppointmentDataModel, Long> {

    @Query(value =
            "select cba.CORPORATE_BODY_APPOINTMENT_ID, cba.APPOINTMENT_DATE, cba.OCCUPATION_DESC, cba.OFFICER_DETAIL_ID "
                    +
                    "from CORPORATE_BODY cb, CORPORATE_BODY_APPOINTMENT cba, OFFICER o, OFFICER_DETAIL od "
                    +
                    "where cb.INCORPORATION_NUMBER = ?1 " +
                    "and cb.CORPORATE_BODY_ID = cba.CORPORATE_BODY_ID " +
                    "and cba.OFFICER_DETAIL_ID = od.OFFICER_DETAIL_ID " +
                    "and cba.OFFICER_ID = o.OFFICER_ID " +
                    "and cba.APPOINTMENT_TYPE_ID IN (2, 3, 4) " +
                    "and cba.RESIGNATION_IND = 'N' " +
                    "and od.OFFICER_DISQUALIFICATION_IND = 'N' " +
                    "and o.CORPORATE_OFFICER_IND = 'N' ",
            countQuery = "select count(*)" +
                    "from CORPORATE_BODY cb, CORPORATE_BODY_APPOINTMENT cba, OFFICER o, OFFICER_DETAIL od "
                    +
                    "where cb.INCORPORATION_NUMBER = ?1 " +
                    "and cb.CORPORATE_BODY_ID = cba.CORPORATE_BODY_ID " +
                    "and cba.OFFICER_DETAIL_ID = od.OFFICER_DETAIL_ID " +
                    "and cba.OFFICER_ID = o.OFFICER_ID " +
                    "and cba.APPOINTMENT_TYPE_ID IN (2, 3, 4) " +
                    "and cba.RESIGNATION_IND = 'N' " +
                    "and od.OFFICER_DISQUALIFICATION_IND = 'N' " +
                    "and o.CORPORATE_OFFICER_IND = 'N' ",
            nativeQuery = true)
    Page<CorporateBodyAppointmentDataModel> findEligibleOfficersEmergencyAuthCode(
            String incorporationNumber, Pageable pageable);

    @Query(value =
            "select cba.CORPORATE_BODY_APPOINTMENT_ID, cba.APPOINTMENT_DATE, cba.OCCUPATION_DESC, cba.OFFICER_DETAIL_ID "
                    +
                    "from CORPORATE_BODY cb, CORPORATE_BODY_APPOINTMENT cba, OFFICER o, OFFICER_DETAIL od "
                    +
                    "where cb.INCORPORATION_NUMBER = ?1 " +
                    "and cb.CORPORATE_BODY_ID = cba.CORPORATE_BODY_ID " +
                    "and cba.OFFICER_DETAIL_ID = od.OFFICER_DETAIL_ID " +
                    "and cba.OFFICER_ID = o.OFFICER_ID " +
                    "and cba.APPOINTMENT_TYPE_ID IN (2, 3, 4) " +
                    "and cba.RESIGNATION_IND = 'N' " +
                    "and od.OFFICER_DISQUALIFICATION_IND = 'N' " +
                    "and o.CORPORATE_OFFICER_IND = 'N' " +
                    "and od.OFFICER_DETAIL_ID = ?2 " +
                    "and rownum=1",
            nativeQuery = true)
    CorporateBodyAppointmentDataModel findEligibleOfficer(
            String incorporationNumber, String officerId);

    @Query(value = "select count(*) " +
            "from corporate_body cb, transaction tr " +
            "where cb.incorporation_number = ?1 " +
            "and tr.CORPORATE_BODY_ID = cb.CORPORATE_BODY_ID " +
            "and tr.SUBMISSION_TYPE_ID in (1, 2, 13, 14) " +
            "and tr.TRANSACTION_TYPE_ID not in (592, 5543, 943)" +
            "and tr.DATE_RECD_TIMESTAMP > sysdate-30",
            nativeQuery = true)
    Long findEFilingsInLastThirtyDays(String incorporationNumber);
}

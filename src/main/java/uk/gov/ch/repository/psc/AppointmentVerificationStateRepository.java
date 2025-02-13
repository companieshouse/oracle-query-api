package uk.gov.ch.repository.psc;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uk.gov.ch.model.psc.AppointmentVerificationState;

@Repository
public interface AppointmentVerificationStateRepository extends JpaRepository<AppointmentVerificationState, Long> {
    // @formatter:off
    @Query(value = "select " + "CBA.CORPORATE_BODY_APPOINTMENT_ID, "
                   + "APV.APPT_VERIF_STATUS_TYPE_ID, "
                   + "IDV.IDV_START_DATE, "
                   + "IDV.IDV_VERIFI_STATEMENT_DUE_DATE "
                   + "from CORPORATE_BODY_APPOINTMENT CBA "
                   + "left inner join APPT_VERIFICATION APV on CBA.APPT_VERIFICATION_ID = APV.APPT_VERIFICATION_ID "
                   + "left inner join IDV_DETAILS IDV on CBA.CORPORATE_BODY_APPOINTMENT_ID = IDV.CORPORATE_BODY_APPOINTMENT_ID "
                   + "where "
                   + "CBA.CORPORATE_BODY_APPOINTMENT_ID = ?1 "
                   + "AND CBA.RESIGNATION_IND = 'N' "
                   + "AND CBA.SUPER_SECURE_PSC_IND = 'N' "
                   + "AND CBA.APPOINTMENT_TYPE_ID IN (5007, 5008, 5009) "
                   + "group by CBA.CORPORATE_BODY_APPOINTMENT_ID, "
                   + "APV.APPT_VERIF_STATUS_TYPE_ID, "
                   + "IDV.IDV_START_DATE, "
                   + "IDV.IDV_VERIFI_STATEMENT_DUE_DATE ",
        countQuery = "select count(*) "
                     + "from CORPORATE_BODY_APPOINTMENT CBA "
                     + "left inner join APPT_VERIFICATION APV on CBA.APPT_VERIFICATION_ID = APV.APPT_VERIFICATION_ID "
                     + "left inner join IDV_DETAILS IDV on CBA.CORPORATE_BODY_APPOINTMENT_ID = IDV.CORPORATE_BODY_APPOINTMENT_ID "
                     + "where "
                     + "CBA.CORPORATE_BODY_APPOINTMENT_ID = ?1 "
                     + "AND CBA.RESIGNATION_IND = 'N' "
                     + "AND CBA.SUPER_SECURE_PSC_IND = 'N' "
                     + "AND CBA.APPOINTMENT_TYPE_ID IN (5007, 5008, 5009) "
                     + "group by CBA.CORPORATE_BODY_APPOINTMENT_ID, "
                     + "APV.APPT_VERIF_STATUS_TYPE_ID, "
                     + "IDV.IDV_START_DATE, "
                     + "IDV.IDV_VERIFI_STATEMENT_DUE_DATE ",
        nativeQuery = true)
    // @formatter:on
    Optional<AppointmentVerificationState> findAppointmentVerificationState(final Long appointmentId);
}

package uk.gov.ch.repository.psc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uk.gov.ch.model.psc.IdentityVerificationExtensionDetails;

import java.util.List;

@Repository
public interface IdentityVerificationExtensionDetailsRepository extends JpaRepository<IdentityVerificationExtensionDetails, Long> {
    @Query(value = """
        select ER.EXTENSION_REQUEST_ID
              ,AVP.CORPORATE_BODY_APPOINTMENT_ID
              ,ER.EXTENSION_REQUEST_DATE
              ,EST.EXT_STATUS_TYPE_DESCRIPTION
              ,ERT.EXTENSION_REASON_TYPE_DESCRIPT
              ,ER.VERIFICATION_STMT_DUE_DATE_OLD
              ,ER.VERIFICATION_STMT_DUE_DATE_NEW
              ,ER.CHS_USER_ID
          from EXTENSION_REQUEST ER
          inner join APPT_VERIFICATION_PERIOD AVP
            on AVP.APPT_VERIFICATION_PERIOD_ID = ER.APPT_VERIFICATION_PERIOD_ID
          inner join EXTENSION_STATUS_TYPE EST
            on EST.EXTENSION_STATUS_TYPE_ID = ER.EXTENSION_STATUS_TYPE_ID
          inner join EXTENSION_REASON_TYPE ERT
            on ERT.EXTENSION_REASON_TYPE_ID = ER.EXTENSION_REASON_TYPE_ID
         where AVP.CORPORATE_BODY_APPOINTMENT_ID = ?1
         order by ER.EXTENSION_REQUEST_DATE desc
        """, nativeQuery = true)
    List<IdentityVerificationExtensionDetails> findExtensionRequest(final Long appointmentId);
}

package uk.gov.ch.repository.psc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uk.gov.ch.model.psc.IdentityVerificationExtensionDetails;

import java.util.Optional;

@Repository
public interface IdentityVerificationExtensionDetailsRepository extends JpaRepository<IdentityVerificationExtensionDetails, Long> {
    // TODO : UPDATE WITH CORRECT DB DETAILS
    @Query(value = """
        select CBA.CORPORATE_BODY_APPOINTMENT_ID
              ,APV.APPT_VERIFICATION_EFF_DATE
              ,APV.APPT_VERIFICATION_END_DATE
              ,APP.VERIFICATION_STMT_DATE
              ,APP.VERIFICATION_STMT_DUE_DATE
          from CORPORATE_BODY_APPOINTMENT CBA
          inner join APPT_VERIFICATION_PERIOD APP
        on APP.CORPORATE_BODY_APPOINTMENT_ID = CBA.CORPORATE_BODY_APPOINTMENT_ID
          left join APPT_VERIFICATION APV
        on APV.CORPORATE_BODY_APPOINTMENT_ID = CBA.CORPORATE_BODY_APPOINTMENT_ID
           and ( APV.APPT_VERIFICATION_END_DATE >= SYSDATE
             or APV.APPT_VERIFICATION_END_DATE is null )
         where CBA.CORPORATE_BODY_APPOINTMENT_ID = ?1
           and CBA.RESIGNATION_IND = 'N'
           and CBA.SUPER_SECURE_PSC_IND = 'N'
           and CBA.APPOINTMENT_TYPE_ID = 5007
        """, nativeQuery = true)
    Optional<IdentityVerificationExtensionDetails> findExtensionRequest(final Long extensionRequestId);
}

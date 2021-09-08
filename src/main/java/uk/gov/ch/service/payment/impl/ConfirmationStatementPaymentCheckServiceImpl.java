package uk.gov.ch.service.payment.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.model.payment.ConfirmationStatementPayment;
import uk.gov.ch.model.payment.ConfirmationStatementPaymentJson;
import uk.gov.ch.repository.payment.ConfirmationStatementPaymentCheckRepository;
import uk.gov.ch.service.payment.ConfirmationStatementPaymentCheckService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

import java.util.Optional;

@Service
public class ConfirmationStatementPaymentCheckServiceImpl implements ConfirmationStatementPaymentCheckService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private ConfirmationStatementPaymentCheckRepository confirmationStatementPaymentCheckRepository;

    @Override
    public ConfirmationStatementPaymentJson isConfirmationStatementPaid(String companyNumber, String madeUpToDate) {
        Optional<ConfirmationStatementPayment> confirmationStatementPayment
                = confirmationStatementPaymentCheckRepository.findPaymentsForPeriod(companyNumber, madeUpToDate);

        ConfirmationStatementPaymentJson confirmationStatementPaymentJson =
                new ConfirmationStatementPaymentJson();
        if(confirmationStatementPayment.isPresent()) {
            Long paidByTransactionId = confirmationStatementPayment.get().getPaidByTransactionId();

            if(paidByTransactionId != null) {
                LOGGER.info(String.format("Company Number: %s Confirmation statement payment id %s found for made up to date %s",
                        companyNumber, paidByTransactionId, madeUpToDate));
                confirmationStatementPaymentJson.setPaid(Boolean.TRUE);
            } else {
                LOGGER.info(String.format("Company Number: %s Confirmation statement payment query returned result but no payment transaction id found for made up to date %s", companyNumber, madeUpToDate ));
                confirmationStatementPaymentJson.setPaid(Boolean.FALSE);
            }
        } else {
            LOGGER.info(String.format("Company Number: %s Confirmation statement payment query returned no result for made up to date %s", companyNumber, madeUpToDate ));
            confirmationStatementPaymentJson.setPaid(Boolean.FALSE);
        }
        return confirmationStatementPaymentJson;
    }
}

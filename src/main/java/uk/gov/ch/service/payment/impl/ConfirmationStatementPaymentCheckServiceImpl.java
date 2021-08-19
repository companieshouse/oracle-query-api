package uk.gov.ch.service.payment.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.model.payment.ConfirmationStatementPayment;
import uk.gov.ch.repository.payment.ConfirmationStatementPaymentCheckRepository;
import uk.gov.ch.service.payment.ConfirmationStatementPaymentCheckService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class ConfirmationStatementPaymentCheckServiceImpl implements ConfirmationStatementPaymentCheckService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private ConfirmationStatementPaymentCheckRepository confirmationStatementPaymentCheckRepository;

    @Override
    public boolean isConfirmationStatementPaid(String companyNumber, String dueDateString) {
        LocalDate dueDate = LocalDate.parse(dueDateString, dateTimeFormatter);
        Optional<ConfirmationStatementPayment> confirmationStatementPayment
                = confirmationStatementPaymentCheckRepository.findPaymentsForPeriod(companyNumber, Date.valueOf(dueDate));

        if(confirmationStatementPayment.isPresent()) {
            Long paidByTransactionId = confirmationStatementPayment.get().getPaidByTransactionId();

            if(paidByTransactionId != null) {
                LOGGER.info("Confirmation statement payment id " +
                        paidByTransactionId + " found for due date " + dueDateString);
                return true;
            }
            LOGGER.info("Confirmation statement payment query returned result but no payment transaction id found");
            return false;
        }
        LOGGER.info("Confirmation statement payment query returned no result");
        return false;
    }

}

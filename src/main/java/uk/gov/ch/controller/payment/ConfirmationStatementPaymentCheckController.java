package uk.gov.ch.controller.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ch.model.payment.ConfirmationStatementPaymentJson;
import uk.gov.ch.service.payment.ConfirmationStatementPaymentCheckService;

@RestController
public class ConfirmationStatementPaymentCheckController {

    @Autowired
    private ConfirmationStatementPaymentCheckService confirmationStatementPaymentCheckService;

    @GetMapping("/company/{companyNumber}/confirmation-statement/paid")
    public ResponseEntity<ConfirmationStatementPaymentJson> isConfirmationStatementPaid(
            @PathVariable String companyNumber,
            @RequestParam(name = "payment_period_made_up_to_date", required = true) String paymentPeriodMadeUpToDate) {
        ConfirmationStatementPaymentJson confirmationStatementPaymentJson = confirmationStatementPaymentCheckService.isConfirmationStatementPaid(
                companyNumber, paymentPeriodMadeUpToDate);
        return ResponseEntity.status(HttpStatus.OK).body(confirmationStatementPaymentJson);
    }
}

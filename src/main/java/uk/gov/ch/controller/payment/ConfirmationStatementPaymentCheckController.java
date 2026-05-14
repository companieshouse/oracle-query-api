package uk.gov.ch.controller.payment;

import jakarta.validation.constraints.Pattern;
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

    private ConfirmationStatementPaymentCheckService confirmationStatementPaymentCheckService;

    public ConfirmationStatementPaymentCheckController(ConfirmationStatementPaymentCheckService confirmationStatementPaymentCheckService) {
        this.confirmationStatementPaymentCheckService = confirmationStatementPaymentCheckService;
    }

    @GetMapping("/company/{companyNumber}/confirmation-statement/paid")
    public ResponseEntity<ConfirmationStatementPaymentJson> isConfirmationStatementPaid(
            @PathVariable @Pattern(regexp = "^[A-Z0-9]+$", message = "Invalid company number") String companyNumber, // NOSONAR really do want 0-9 here not any digit
            @RequestParam(name = "payment_period_made_up_to_date", required = true) String paymentPeriodMadeUpToDate) {
        ConfirmationStatementPaymentJson confirmationStatementPaymentJson = confirmationStatementPaymentCheckService.isConfirmationStatementPaid(
                companyNumber, paymentPeriodMadeUpToDate);
        return ResponseEntity.status(HttpStatus.OK).body(confirmationStatementPaymentJson);
    }
}

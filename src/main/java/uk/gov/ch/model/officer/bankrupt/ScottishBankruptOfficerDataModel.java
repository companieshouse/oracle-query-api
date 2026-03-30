package uk.gov.ch.model.officer.bankrupt;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "SCOTTISH_BANKRUPT_OFFICER")
@AttributeOverrides({
    @AttributeOverride(name = "ephemeralKey", column = @Column(name = "EPHEMERAL_KEY")),
    @AttributeOverride(name = "forename1", column = @Column(name = "FORENAME_1")),
    @AttributeOverride(name = "forename2", column = @Column(name = "FORENAME_2")),
    @AttributeOverride(name = "surname", column = @Column(name = "SURNAME")),
    @AttributeOverride(name = "addressLine1", column = @Column(name = "ADDRESS_LINE_1")),
    @AttributeOverride(name = "addressLine2", column = @Column(name = "ADDRESS_LINE_2")),
    @AttributeOverride(name = "addressLine3", column = @Column(name = "ADDRESS_LINE_3")),
    @AttributeOverride(name = "addressTown", column = @Column(name = "ADDRESS_TOWN")),
    @AttributeOverride(name = "addressCounty", column = @Column(name = "ADDRESS_COUNTY")),
    @AttributeOverride(name = "addressPostcode", column = @Column(name = "ADDRESS_POSTCODE")),
    @AttributeOverride(name = "dateOfBirth", column = @Column(name = "DATE_OF_BIRTH")),
    @AttributeOverride(name = "alias", column = @Column(name = "ALIAS")),
    @AttributeOverride(name = "caseReference", column = @Column(name = "CASE_REFERENCE")),
    @AttributeOverride(name = "caseType", column = @Column(name = "CASE_TYPE")),
    @AttributeOverride(name = "bankruptcyType", column = @Column(name = "BANKRUPTCY_TYPE")),
    @AttributeOverride(name = "startDate", column = @Column(name = "START_DATE")),
    @AttributeOverride(name = "debtorDischargeDate", column = @Column(name = "DEBTOR_DISCHARGE_DATE")),
    @AttributeOverride(name = "trusteeDischargeDate", column = @Column(name = "TRUSTEE_DISCHARGE_DATE"))
})
public class ScottishBankruptOfficerDataModel extends ScottishBankruptOfficerBase {
    @Id
    @Column(name = "EPHEMERAL_KEY")
    private String ephemeralKey;

}

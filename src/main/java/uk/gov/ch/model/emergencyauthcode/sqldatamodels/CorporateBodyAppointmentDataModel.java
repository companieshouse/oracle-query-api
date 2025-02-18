package uk.gov.ch.model.emergencyauthcode.sqldatamodels;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "CORPORATE_BODY_APPOINTMENT")
public class CorporateBodyAppointmentDataModel {

    @Id
    @Column(name = "CORPORATE_BODY_APPOINTMENT_ID")
    private Long corporateBodyAppointmentId;

    @Column(name = "APPOINTMENT_DATE")
    private LocalDate appointmentDate;

    @Column(name = "OCCUPATION_DESC")
    private String occupationDescription;

    @OneToOne
    @JoinColumn(name = "OFFICER_DETAIL_ID")
    private OfficerDetailDataModel officerDetail;

    public Long getCorporateBodyAppointmentId() {
        return corporateBodyAppointmentId;
    }

    public void setCorporateBodyAppointmentId(Long corporateBodyAppointmentId) {
        this.corporateBodyAppointmentId = corporateBodyAppointmentId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public OfficerDetailDataModel getOfficerDetail() {
        return officerDetail;
    }

    public void setOfficerDetail(OfficerDetailDataModel officerDetail) {
        this.officerDetail = officerDetail;
    }

    public String getOccupationDescription() {
        return occupationDescription;
    }

    public void setOccupationDescription(String occupationDescription) {
        this.occupationDescription = occupationDescription;
    }
}

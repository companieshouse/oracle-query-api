package uk.gov.ch.model.officer.active;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class ActiveDirectorDetails {

    @Id
    @Column(name = "officer_detail_id")
    @JsonIgnore
    private Long officerDetailId;
    @Column(name = "fore_name_1")
    @JsonProperty("fore_name_1")
    private String foreName1;
    @Column(name = "fore_name_2")
    @JsonProperty("fore_name_2")
    private String foreName2;
    @Column(name = "surname")
    private String surname;
    @Column(name = "occupation")
    private String occupation;
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "date_of_birth")
    @JsonProperty("date_of_birth")
    private String dateOfBirth;
    @Column(name = "service_address_line_1")
    @JsonProperty("service_address_line_1")
    private String serviceAddressLine1;
    @Column(name = "service_address_post_town")
    @JsonProperty("service_address_post_town")
    private String serviceAddressPostTown;
    @Column(name = "service_address_post_code")
    @JsonProperty("service_address_post_code")
    private String serviceAddressPostCode;
    @Column(name = "ura_line_1")
    @JsonProperty("ura_line_1")
    private String uraLine1;
    @Column(name = "ura_post_town")
    @JsonProperty("ura_post_town")
    private String uraPostTown;
    @Column(name = "ura_post_code")
    @JsonProperty("ura_post_code")
    private String uraPostCode;
    @Column(name = "secure_indicator")
    @JsonIgnore
    private String secureIndicator;

    public Long getOfficerDetailId() {
        return officerDetailId;
    }

    public void setOfficerDetailId(Long officerDetailId) {
        this.officerDetailId = officerDetailId;
    }

    public String getServiceAddressLine1() {
        return serviceAddressLine1;
    }

    public void setServiceAddressLine1(String serviceAddressLine1) {
        this.serviceAddressLine1 = serviceAddressLine1;
    }

    public String getServiceAddressPostTown() {
        return serviceAddressPostTown;
    }

    public void setServiceAddressPostTown(String serviceAddressPostTown) {
        this.serviceAddressPostTown = serviceAddressPostTown;
    }

    public String getServiceAddressPostCode() {
        return serviceAddressPostCode;
    }

    public void setServiceAddressPostCode(String serviceAddressPostCode) {
        this.serviceAddressPostCode = serviceAddressPostCode;
    }

    public String getUraLine1() {
        if (isSecureOfficer()) {
            return "Companies House Cannot Disclose this Home Address";
        }
        return uraLine1;
    }

    public void setUraLine1(String uraLine1) {
        this.uraLine1 = uraLine1;
    }

    public String getUraPostTown() {
        if (isSecureOfficer()) {
            return null;
        }
        return uraPostTown;
    }

    public void setUraPostTown(String uraPostTown) {
        this.uraPostTown = uraPostTown;
    }

    public String getUraPostCode() {
        if (isSecureOfficer()) {
            return null;
        }
        return uraPostCode;
    }

    public void setUraPostCode(String uraPostCode) {
        this.uraPostCode = uraPostCode;
    }

    public String getForeName1() {
        return foreName1;
    }

    public void setForeName1(String foreName1) {
        this.foreName1 = foreName1;
    }

    public String getForeName2() {
        return foreName2;
    }

    public void setForeName2(String foreName2) {
        this.foreName2 = foreName2;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDateOfBirth() throws ParseException {
        SimpleDateFormat formatFrom = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = formatFrom.parse(dateOfBirth);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMMM yyyy");
        return simpleDateFormat.format(dob);
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSecureIndicator() {
        return secureIndicator;
    }

    public void setSecureIndicator(String secureIndicator) {
        this.secureIndicator = secureIndicator;
    }

    private boolean isSecureOfficer() {
        return getSecureIndicator().equals("Y");
    }

}

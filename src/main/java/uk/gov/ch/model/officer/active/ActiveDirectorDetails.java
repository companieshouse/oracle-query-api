package uk.gov.ch.model.officer.active;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ActiveOfficerDetails {

    @Id //TODO: Get an actual unique identifier!!
    @Column(name = "fore_name_1")
    private String foreName1;
    @Column(name = "fore_name_2")
    private String foreName2;
    @Column(name = "surname")
    private String surname;
    @Column(name = "occupation")
    private String occupation;
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Column(name = "service_address_line_1")
    private String serviceAddressLine1;
    @Column(name = "service_address_post_town")
    private String serviceAddressPostTown;
    @Column(name = "service_address_post_code")
    private String serviceAddressPostCode;
    @Column(name = "ura_line_1")
    private String uraLine1;
    @Column(name = "ura_post_town")
    private String uraPostTown;
    @Column(name = "ura_post_code")
    private String uraPostCode;
    @Column(name = "secure_indicator")
    private String secureIndicator;

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
            return null;
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
        Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth);
        return new SimpleDateFormat("dd MMMMM yyyy").format(dob);
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

//    class ActiveOfficerDetailJson {
//        @JsonProperty("fore_name_1")
//        private String foreName1;
//        @JsonProperty("fore_name_2")
//        private String foreName2;
//        private String surname;
//        private String occupation;
//        private String nationality;
//        @JsonProperty("date_of_birth")
//        private String dateOfBirth;
//        @JsonProperty("service_address_line_1")
//        private String serviceAddressLine1;
//        @JsonProperty("service_address_post_town")
//        private String serviceAddressPostTown;
//        @JsonProperty("service_address_post_code")
//        private String serviceAddressPostCode;
//        @JsonProperty("ura_line_1")
//        private String uraLine1;
//        @JsonProperty("ura_post_town")
//        private String uraPostTown;
//        @JsonProperty("ura_post_code")
//        private String uraPostCode;
//
//        public ActiveOfficerDetailJson(ActiveOfficerDetails director) {
//            this.foreName1 = director.foreName1;
//            this.foreName2 = director.foreName2;
//        }
//    }

}

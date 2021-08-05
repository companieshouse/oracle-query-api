package uk.gov.ch.model.officer.active;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActiveOfficerDetails {

    @JsonProperty("fore_name_1")
    private String foreName1;
    @JsonProperty("fore_name_2")
    private String foreName2;
    private String surname;
    private String occupation;
    private String nationality;
    @JsonProperty("date_of_birth")
    private String dateOfBirth;
    @JsonProperty("service_address_line_1")
    private String serviceAddressLine1;
    @JsonProperty("service_address_post_town")
    private String serviceAddressPostTown;
    @JsonProperty("service_address_post_code")
    private String serviceAddressPostCode;
    @JsonProperty("ura_line_1")
    private String uraLine1;
    @JsonProperty("ura_post_town")
    private String uraPostTown;
    @JsonProperty("ura_post_code")
    private String uraPostCode;
    @JsonProperty("secure_indicator")
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

    public boolean isSecureOfficer() {
        return getSecureIndicator().equals("Y");
    }

}

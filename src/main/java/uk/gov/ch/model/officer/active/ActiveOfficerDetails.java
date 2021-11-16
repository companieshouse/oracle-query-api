package uk.gov.ch.model.officer.active;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.companieshouse.api.model.common.Address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class ActiveOfficerDetails {

    @Id
    @Column(name = "officer_detail_id")
    private Long officerDetailId;
    @Column(name = "fore_name_1")
    @JsonProperty("fore_name_1")
    private String foreName1;
    @Column(name = "fore_name_2")
    @JsonProperty("fore_name_2")
    private String foreName2;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "OCCUPATION")
    private String occupation;
    @Column(name = "NATIONALITY")
    private String nationality;
    @Column(name = "DATE_OF_BIRTH")
    @JsonProperty("date_of_birth")
    private String dateOfBirth;
    @Column(name = "date_of_appointment")
    @JsonProperty("date_of_appointment")
    private String dateOfAppointment;
    @Column(name = "country_of_residence")
    @JsonProperty("country_of_residence")
    private String countryOfResidence;
    @Column(name = "SERVICE_ADDRESS_LINE_1")
    private String serviceAddressLine1;
    @Column(name = "SERVICE_ADDRESS_LINE_2")
    private String serviceAddressLine2;
    @Column(name = "SERVICE_ADDRESS_CARE_OF")
    private String serviceAddressCareOf;
    @Column(name = "SERVICE_ADDRESS_COUNTRY")
    private String serviceAddressCountry;
    @Column(name = "SERVICE_ADDRESS_LOCALITY")
    private String serviceAddressLocality;
    @Column(name = "SERVICE_ADDRESS_PO_BOX")
    private String serviceAddressPoBox;
    @Column(name = "SERVICE_ADDRESS_POST_CODE")
    private String serviceAddressPostCode;
    @Column(name = "SERVICE_ADDRESS_REGION")
    private String serviceAddressRegion;
    @Column(name = "RESIDENTIAL_ADDRESS_LINE_1")
    private String residentialAddressLine1;
    @Column(name = "RESIDENTIAL_ADDRESS_LINE_2")
    private String residentialAddressLine2;
    @Column(name = "RESIDENTIAL_ADDRESS_CARE_OF")
    private String residentialAddressCareOf;
    @Column(name = "RESIDENTIAL_ADDRESS_COUNTRY")
    private String residentialAddressCountry;
    @Column(name = "RESIDENTIAL_ADDRESS_LOCALITY")
    private String residentialAddressLocality;
    @Column(name = "RESIDENTIAL_ADDRESS_PO_BOX")
    private String residentialAddressPoBox;
    @Column(name = "RESIDENTIAL_ADDRESS_POST_CODE")
    private String residentialAddressPostCode;
    @Column(name = "RESIDENTIAL_ADDRESS_REGION")
    private String residentialAddressRegion;
    @Column(name = "SECURE_INDICATOR")
    private String secureIndicator;
    @Column(name = "corporate")
    @JsonProperty("is_corporate")
    private boolean corporate;
    @Column(name = "role")
    private String role;
    @Column(name = "place_registered")
    @JsonProperty("place_registered")
    private String placeRegistered;
    @Column(name = "registration_number")
    @JsonProperty("registration_number")
    private String registrationNumber;
    @Column(name = "law_governed")
    @JsonProperty("law_governed")
    private String lawGoverned;
    @Column(name = "legal_form")
    @JsonProperty("legal_form")
    private String legalForm;
    @Column(name = "identification_type")
    @JsonProperty("identification_type")
    private String identificationType;

    @Transient
    @JsonProperty("service_address")
    private Address serviceAddress;

    @Transient
    @JsonProperty("residential_address")
    private Address residentialAddress;

    public void setOfficerDetailId(Long officerDetailId) {
        this.officerDetailId = officerDetailId;
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
        return formatDate(dateOfBirth);
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public void setSecureIndicator(String secureIndicator) {
        this.secureIndicator = secureIndicator;
    }

    private boolean isSecureOfficer() {
        return secureIndicator.equals("Y");
    }

    public void setServiceAddressLine1(String serviceAddressLine1) {
        this.serviceAddressLine1 = serviceAddressLine1;
    }

    public void setServiceAddressLine2(String serviceAddressLine2) {
        this.serviceAddressLine2 = serviceAddressLine2;
    }

    public void setServiceAddressCareOf(String serviceAddressCareOf) {
        this.serviceAddressCareOf = serviceAddressCareOf;
    }

    public void setServiceAddressCountry(String serviceAddressCountry) {
        this.serviceAddressCountry = serviceAddressCountry;
    }

    public void setServiceAddressLocality(String serviceAddressLocality) {
        this.serviceAddressLocality = serviceAddressLocality;
    }

    public void setServiceAddressPoBox(String serviceAddressPoBox) {
        this.serviceAddressPoBox = serviceAddressPoBox;
    }

    public void setServiceAddressPostCode(String serviceAddressPostCode) {
        this.serviceAddressPostCode = serviceAddressPostCode;
    }

    public void setServiceAddressRegion(String serviceAddressRegion) {
        this.serviceAddressRegion = serviceAddressRegion;
    }

    public void setResidentialAddressLine1(String residentialAddressLine1) {
        this.residentialAddressLine1 = residentialAddressLine1;
    }

    public void setResidentialAddressLine2(String residentialAddressLine2) {
        this.residentialAddressLine2 = residentialAddressLine2;
    }

    public void setResidentialAddressCareOf(String residentialAddressCareOf) {
        this.residentialAddressCareOf = residentialAddressCareOf;
    }

    public void setResidentialAddressCountry(String residentialAddressCountry) {
        this.residentialAddressCountry = residentialAddressCountry;
    }

    public void setResidentialAddressLocality(String residentialAddressLocality) {
        this.residentialAddressLocality = residentialAddressLocality;
    }

    public void setResidentialAddressPoBox(String residentialAddressPoBox) {
        this.residentialAddressPoBox = residentialAddressPoBox;
    }

    public void setResidentialAddressPostCode(String residentialAddressPostCode) {
        this.residentialAddressPostCode = residentialAddressPostCode;
    }

    public void setResidentialAddressRegion(String residentialAddressRegion) {
        this.residentialAddressRegion = residentialAddressRegion;
    }

    public String getDateOfAppointment() throws ParseException {
        return formatDate(dateOfAppointment);
    }

    public void setDateOfAppointment(String dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public void setCorporate(boolean corporate) {
        this.corporate = corporate;
    }

    public boolean isCorporate() {
        return corporate;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String getPlaceRegistered() {
        return placeRegistered;
    }

    public void setPlaceRegistered(String placeRegistered) {
        this.placeRegistered = placeRegistered;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getLawGoverned() {
        return lawGoverned;
    }

    public void setLawGoverned(String lawGoverned) {
        this.lawGoverned = lawGoverned;
    }

    public String getLegalForm() {
        return legalForm;
    }

    public void setLegalForm(String legalForm) {
        this.legalForm = legalForm;
    }

    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    public Address getServiceAddress() {
        Address address = new Address();
        address.setAddressLine1(serviceAddressLine1);
        address.setAddressLine2(serviceAddressLine2);
        address.setCareOf(serviceAddressCareOf);
        address.setCountry(serviceAddressCountry);
        address.setLocality(serviceAddressLocality);
        address.setPoBox(serviceAddressPoBox);
        address.setPostalCode(serviceAddressPostCode);
        address.setRegion(serviceAddressRegion);
        return address;
    }

    public Address getResidentialAddress() {
        Address address = new Address();
        if (isSecureOfficer()) {
            address.setAddressLine1("Companies House Cannot Disclose this Home Address");
        } else {
            address.setAddressLine1(residentialAddressLine1);
            address.setAddressLine2(residentialAddressLine2);
            address.setCareOf(residentialAddressCareOf);
            address.setCountry(residentialAddressCountry);
            address.setLocality(residentialAddressLocality);
            address.setPoBox(residentialAddressPoBox);
            address.setPostalCode(residentialAddressPostCode);
            address.setRegion(residentialAddressRegion);
        }
        return address;
    }

    private String formatDate(String dateString) throws ParseException {
        SimpleDateFormat formatFrom = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatFrom.parse(dateString);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMMM yyyy");
        return simpleDateFormat.format(date);
    }
}

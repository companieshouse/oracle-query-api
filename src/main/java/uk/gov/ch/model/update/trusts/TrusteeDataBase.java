package uk.gov.ch.model.update.trusts;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import uk.gov.companieshouse.api.model.common.Address;

public class TrusteeDataBase {

    @Column(name = "TRUSTEE_TYPE_ID")
    @JsonProperty("trusteeTypeId")
    protected String trusteeTypeId;

    @Column(name = "APPOINTMENT_DATE")
    @JsonProperty("appointmentDate")
    protected String appointmentDate;

    @Column(name = "CEASED_DATE")
    @JsonProperty("ceasedDate")
    protected String ceasedDate;

    @Column(name = "SERV_ADDR_NAME_NUMBER")
    protected String serviceAddressHouseNameNumber;

    @Column(name = "SERV_ADDR_STREET")
    protected String serviceAddressStreet;

    @Column(name = "SERV_ADDR_AREA")
    protected String serviceAddressArea;

    @Column(name = "SERV_ADDR_POST_TOWN")
    protected String serviceAddressPostTown;

    @Column(name = "SERV_ADDR_REGION")
    protected String serviceAddressRegion;

    @Column(name = "SERV_ADDR_COUNTRY_NAME")
    protected String serviceAddressCountryName;

    @Column(name = "SERV_ADDR_POST_CODE")
    protected String serviceAddressPostCode;

    @Transient
    @JsonProperty("serviceAddress")
    protected Address serviceAddress;

    public Address getServiceAddress() {
        if (this.serviceAddress == null) {
            this.serviceAddress = new Address();
            this.serviceAddress.setPremises(serviceAddressHouseNameNumber);
            this.serviceAddress.setAddressLine1(serviceAddressStreet);
            this.serviceAddress.setAddressLine2(serviceAddressArea);
            this.serviceAddress.setLocality(serviceAddressPostTown);
            this.serviceAddress.setRegion(serviceAddressRegion);
            this.serviceAddress.setCountry(serviceAddressCountryName);
            this.serviceAddress.setPostalCode(serviceAddressPostCode);
        }
        return this.serviceAddress;
    }

    public void setServiceAddress(Address serviceAddress) {
        if (serviceAddress != null) {
            this.serviceAddressHouseNameNumber = serviceAddress.getPremises();
            this.serviceAddressStreet = serviceAddress.getAddressLine1();
            this.serviceAddressArea = serviceAddress.getAddressLine2();
            this.serviceAddressPostTown = serviceAddress.getLocality();
            this.serviceAddressRegion = serviceAddress.getRegion();
            this.serviceAddressCountryName = serviceAddress.getCountry();
            this.serviceAddressPostCode = serviceAddress.getPostalCode();
        }
    }

    public String getTrusteeTypeId() {
        return trusteeTypeId;
    }

    public void setTrusteeTypeId(String trusteeTypeId) {
        this.trusteeTypeId = trusteeTypeId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getCeasedDate() {
        return ceasedDate;
    }

    public void setCeasedDate(String ceasedDate) {
        this.ceasedDate = ceasedDate;
    }

}

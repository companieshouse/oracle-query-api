package uk.gov.ch.model.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import uk.gov.companieshouse.api.model.common.Address;

@Entity
public class RegisterLocation {

    @Id
    @Column(name = "UNIQUE_ID")
    private Long uniqueId;
    @Column(name = "SAIL_ADDRESS_LINE_1")
    private String sailAddressLine1;
    @Column(name = "SAIL_ADDRESS_LINE_2")
    private String sailAddressLine2;
    @Column(name = "SAIL_ADDRESS_CARE_OF")
    private String sailAddressCareOf;
    @Column(name = "SAIL_ADDRESS_COUNTRY")
    private String sailAddressCountry;
    @Column(name = "SAIL_ADDRESS_LOCALITY")
    private String sailAddressLocality;
    @Column(name = "SAIL_ADDRESS_PO_BOX")
    private String sailAddressPoBox;
    @Column(name = "SAIL_ADDRESS_POST_CODE")
    private String sailAddressPostCode;
    @Column(name = "SAIL_ADDRESS_REGION")
    private String sailAddressRegion;
    @Column(name = "register_type_desc")
    @JsonProperty("register_type_desc")
    private String registerTypeDesc;

    @Transient
    @JsonProperty("sail_address")
    private Address sailAddress;


    public Long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(Long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void setSailAddressLine1(String sailAddressLine1) {
        this.sailAddressLine1 = sailAddressLine1;
    }

    public void setSailAddressLine2(String sailAddressLine2) {
        this.sailAddressLine2 = sailAddressLine2;
    }

    public void setSailAddressCareOf(String sailAddressCareOf) {
        this.sailAddressCareOf = sailAddressCareOf;
    }

    public void setSailAddressCountry(String sailAddressCountry) {
        this.sailAddressCountry = sailAddressCountry;
    }

    public void setSailAddressLocality(String sailAddressLocality) {
        this.sailAddressLocality = sailAddressLocality;
    }

    public void setSailAddressPoBox(String sailAddressPoBox) {
        this.sailAddressPoBox = sailAddressPoBox;
    }

    public void setSailAddressPostCode(String sailAddressPostCode) {
        this.sailAddressPostCode = sailAddressPostCode;
    }

    public void setSailAddressRegion(String sailAddressRegion) {
        this.sailAddressRegion = sailAddressRegion;
    }

    public String getRegisterTypeDesc() {
        return registerTypeDesc;
    }

    public void setRegisterTypeDesc(String registerTypeDesc) {
        this.registerTypeDesc = registerTypeDesc;
    }

    public Address getSailAddress() {
        Address address = new Address();
        address.setAddressLine1(sailAddressLine1);
        address.setAddressLine2(sailAddressLine2);
        address.setCareOf(sailAddressCareOf);
        address.setCountry(sailAddressCountry);
        address.setLocality(sailAddressLocality);
        address.setPoBox(sailAddressPoBox);
        address.setPostalCode(sailAddressPostCode);
        address.setRegion(sailAddressRegion);
        return address;
    }

    public void setSailAddress(Address sailAddress) {
        this.sailAddress = sailAddress;
    }
}

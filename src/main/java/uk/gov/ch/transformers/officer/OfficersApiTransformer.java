package uk.gov.ch.transformers.officer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import uk.gov.ch.model.officer.Identification;
import uk.gov.ch.model.officer.OfficerDataModel;
import uk.gov.ch.model.officer.OfficerIdentification;
import uk.gov.ch.model.officer.ServiceAddress;
import uk.gov.companieshouse.api.model.common.Address;
import uk.gov.companieshouse.api.model.officers.CompanyOfficerApi;
import uk.gov.companieshouse.api.model.officers.IdentificationApi;
import uk.gov.companieshouse.api.model.officers.OfficersApi;

@Component
public class OfficersApiTransformer {
    
    public OfficersApi convert(List<OfficerDataModel> officerList) {
        OfficersApi officersApi = new OfficersApi();
        officersApi.setStartIndex(0);
        officersApi.setEtag("officer-list");
        officersApi.setItemsPerPage(new Long(officerList.size()));
        officersApi.setTotalResults(officerList.size());
        int resigned = 0;
        List<CompanyOfficerApi> officers = new ArrayList<>();
        
        for(OfficerDataModel model : officerList) {
            CompanyOfficerApi officer = new CompanyOfficerApi();
            if(model.getResignationDate() != null && !model.getResignationDate().isEmpty()) {
                resigned++;
                officer.setResignedOn(getLocalDateFromString(model.getResignationDate()));
            }
            officer.setAppointedOn(getLocalDateFromString(model.getAppointmentDate()));
            if(model.getServiceAddress() != null) {
                officer.setAddress(getServiceAddress(model.getServiceAddress()));
            }
            if(model.getCorporateInd().startsWith("TRUE")) {
                setCorporateOfficerFields(model, officer);
            } else {
                setHumanOfficerFields(model, officer);
            }
            
            
            officers.add(officer);
        }
        officersApi.setItems(officers);
        officersApi.setResignedCount(resigned);
        
        return officersApi;
    }

    private void setCorporateOfficerFields(OfficerDataModel model, CompanyOfficerApi officer) {
        officer.setName(model.getSurname());
        officer.setIdentification(getIdentification(model.getIdentification()));
    }
    
    private void setHumanOfficerFields(OfficerDataModel model, CompanyOfficerApi officer) {
        // TODO map human fields - D.O.B., forename, surname, occupation etc
    }
    
    private LocalDate getLocalDateFromString(String dateString) {
        if (dateString != null && !dateString.isEmpty()) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            return LocalDate.parse(dateString, dateTimeFormatter);
        }
        return null;
    }
    
    private Address getServiceAddress(ServiceAddress serviceAddress) {
        Address address = new Address();
        address.setAddressLine1(serviceAddress.getAddressLine1());
        address.setAddressLine2(serviceAddress.getAddressLine2());
        address.setCareOf(serviceAddress.getCareOfName());
        address.setCountry(serviceAddress.getCountry());
        address.setLocality(serviceAddress.getLocality());
        address.setPoBox(serviceAddress.getPoBox());
        address.setPostalCode(serviceAddress.getPostalCode());
        address.setPremises(serviceAddress.getPremises());
        address.setRegion(serviceAddress.getRegion());
        return address;
    }
    
    private IdentificationApi getIdentification(Identification identification) {
        IdentificationApi identificationApi = new IdentificationApi();
        if(identification.getEea() != null) {
            setIdentificationDetails(identificationApi, "eea", identification.getEea());
        } else if(identification.getNonEea() != null) {
            setIdentificationDetails(identificationApi, "non-eea", identification.getEea());
        } else if(identification.getOtherCorporateBodyOrFirm() != null) {
            setIdentificationDetails(identificationApi, "other-corporate-body-or-firm", identification.getEea());
        } else if(identification.getUkLimitedCompany() != null) {
            setIdentificationDetails(identificationApi, "uk-limited-company", identification.getEea());
        }
        return identificationApi;
    }
    
    private void setIdentificationDetails(IdentificationApi identificationApi, String type, OfficerIdentification identification) {
        identificationApi.setIdentificationType(type);
        identificationApi.setLegalAuthority(identification.getLegalAuthority());
        identificationApi.setLegalForm(identification.getLegalForm());
        identificationApi.setPlaceRegistered(identification.getPlaceRegistered());
        identificationApi.setRegistrationNumber(identification.getRegistrationNumber());
    }

}

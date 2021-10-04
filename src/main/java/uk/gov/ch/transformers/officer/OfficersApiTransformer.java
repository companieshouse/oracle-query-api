package uk.gov.ch.transformers.officer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import uk.gov.ch.model.officer.Identification;
import uk.gov.ch.model.officer.OfficerDataModel;
import uk.gov.ch.model.officer.OfficerIdentification;
import uk.gov.ch.model.officer.PreviousNameModel;
import uk.gov.ch.model.officer.ServiceAddress;
import uk.gov.companieshouse.api.model.common.Address;
import uk.gov.companieshouse.api.model.common.DateOfBirth;
import uk.gov.companieshouse.api.model.officers.CompanyOfficerApi;
import uk.gov.companieshouse.api.model.officers.FormerNamesApi;
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
        if(model.getPreviousNameArray() != null && !model.getPreviousNameArray().isEmpty()) {
            List<FormerNamesApi> formerNames = new ArrayList<>();
            for(PreviousNameModel pnm : model.getPreviousNameArray()) {
                FormerNamesApi formerName = new FormerNamesApi();
                formerName.setForenames(pnm.getPreviousForename());
                formerName.setSurname(pnm.getPreviousSurname());
                formerNames.add(formerName);
            }
            officer.setFormerNames(formerNames);
        }
        officer.setName(getHumanName(model.getForename(), model.getMiddleName(), model.getSurname()));
        LocalDate dateOfBirthModel = getLocalDateFromString(model.getDateOfBirth());
        DateOfBirth dateOfBirth = new DateOfBirth();
        dateOfBirth.setMonth(new Long(dateOfBirthModel.getMonthValue()));
        dateOfBirth.setYear(new Long(dateOfBirthModel.getYear()));
        officer.setDateOfBirth(dateOfBirth);
        
        officer.setOccupation(model.getOccupation());
        officer.setNationality(model.getNationality());
    }
    
    private String getHumanName(String forename, String middleName, String surname) {
        String name = forename != null ? forename : "";
        name += middleName != null ? " " + middleName : "";
        name += surname != null ? " " + surname : "";
        return name.trim();
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

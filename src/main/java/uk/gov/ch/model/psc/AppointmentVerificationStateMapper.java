package uk.gov.ch.model.psc;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentVerificationStateMapper {
    AppointmentVerificationStateDto entityToDto(final AppointmentVerificationState entity);

    default VerificationStatusType verificationStatusTypeFromLong(final Long typeId) {
        return VerificationStatusType.getTypeFromLong(typeId);
    }

}

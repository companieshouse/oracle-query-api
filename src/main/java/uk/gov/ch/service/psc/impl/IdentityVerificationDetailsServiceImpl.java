package uk.gov.ch.service.psc.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import uk.gov.ch.model.psc.IdentityVerificationDetailsDto;
import uk.gov.ch.model.psc.IdentityVerificationDetailsMapper;
import uk.gov.ch.repository.psc.IdentityVerificationDetailsRepository;
import uk.gov.ch.service.psc.IdentityVerificationDetailsService;

@Service
@ConditionalOnProperty(prefix = "feature", name = "psc_verification_details_get", havingValue = "true")
public class IdentityVerificationDetailsServiceImpl implements IdentityVerificationDetailsService {
    private final IdentityVerificationDetailsRepository identityVerificationDetailsRepository;
    private final IdentityVerificationDetailsMapper identityVerificationDetailsMapper;

    public IdentityVerificationDetailsServiceImpl(
        @Autowired final IdentityVerificationDetailsRepository identityVerificationDetailsRepository,
        @Autowired final IdentityVerificationDetailsMapper identityVerificationDetailsMapper) {
        this.identityVerificationDetailsRepository = identityVerificationDetailsRepository;
        this.identityVerificationDetailsMapper = identityVerificationDetailsMapper;
    }

    @Override
    public Optional<IdentityVerificationDetailsDto> findIdentityVerificationDetails(final Long appointmentId) {
        return identityVerificationDetailsRepository.findIdentityVerificationDetails(appointmentId)
            .map(identityVerificationDetailsMapper::entityToDto);
    }
}

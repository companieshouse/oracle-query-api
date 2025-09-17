package uk.gov.ch.service.psc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import uk.gov.ch.model.psc.IdentityVerificationExtensionDetailsDto;
import uk.gov.ch.model.psc.IdentityVerificationExtensionDetailsMapper;
import uk.gov.ch.repository.psc.IdentityVerificationExtensionDetailsRepository;
import uk.gov.ch.service.psc.IdentityVerificationExtensionDetailsService;

import java.util.List;

@Service
@ConditionalOnProperty(prefix = "feature", name = "psc_extension_details_get", havingValue = "true")
public class IdentityVerificationExtensionDetailsServiceImpl implements IdentityVerificationExtensionDetailsService {
    private final IdentityVerificationExtensionDetailsRepository identityVerificationExtensionDetailsRepository;
    private final IdentityVerificationExtensionDetailsMapper identityVerificationExtensionDetailsMapper;

    public IdentityVerificationExtensionDetailsServiceImpl(
        @Autowired final IdentityVerificationExtensionDetailsRepository identityVerificationExtensionDetailsRepository,
        @Autowired final IdentityVerificationExtensionDetailsMapper identityVerificationExtensionDetailsMapper) {
        this.identityVerificationExtensionDetailsRepository = identityVerificationExtensionDetailsRepository;
        this.identityVerificationExtensionDetailsMapper = identityVerificationExtensionDetailsMapper;
    }

    @Override
    public List<IdentityVerificationExtensionDetailsDto> findExtensionRequest(final Long appointmentId) {
        return identityVerificationExtensionDetailsRepository.findExtensionRequest(appointmentId)
            .stream().map(identityVerificationExtensionDetailsMapper::entityToDto).toList();
    }
}

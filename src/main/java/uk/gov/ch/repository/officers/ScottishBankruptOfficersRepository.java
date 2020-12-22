package uk.gov.ch.repository.officers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import uk.gov.ch.model.officer.bankrupt.ScottishBankruptOfficerDataModel;


/**
 * Repository for querying the Scottish bankrupt officers table
 */
public interface ScottishBankruptOfficersRepository extends PagingAndSortingRepository<ScottishBankruptOfficerDataModel, String> {

    /**
     * Search the Scottish bankrupt officer database for officers matching the supplied filters.
     * 
     * Any null filters will be ignored when applying the filtering.
     * 
     * Spring data will automatically create the implementation of this interface when the
     * service is running. The pagination and sorting will be automatically handled by
     * {@link PagingAndSortingRepository} using the data in {@link Pageable}.
     * 
     * @param forename Forename filter
     * @param surname  Surname filter
     * @param dob Date of birth filter
     * @param postcode Postcode filter
     * @return page A {@link Page} containing the search results and the pagination data
     */
    @Query(value = "select * "
                 + "from SCOTTISH_BANKRUPT_OFFICER "
                 + "where (:forename is null or lower(FORENAME_1) = lower(:forename)) "
                 + "and (:surname is null or lower(SURNAME) = lower(:surname)) "
                 + "and (:dob is null or DATE_OF_BIRTH = TO_DATE(:dob, 'YYYY-MM-DD')) "
                 + "and (:postcode is null or lower(ADDRESS_POSTCODE) = (:postcode)) "
                 + "and trunc(DEBTOR_DISCHARGE_DATE) >= trunc(SYSDATE) ",
           countQuery = "select COUNT(*) "
                   + "from SCOTTISH_BANKRUPT_OFFICER "
                   + "where (:forename is null or lower(FORENAME_1) = lower(:forename)) "
                   + "and (:surname is null or lower(SURNAME) = lower(:surname)) "
                   + "and (:dob is null or DATE_OF_BIRTH = TO_DATE(:dob, 'YYYY-MM-DD')) "
                   + "and (:postcode is null or lower(ADDRESS_POSTCODE) = lower(:postcode)) "
                   + "and trunc(DEBTOR_DISCHARGE_DATE) >= trunc(SYSDATE) ",
           nativeQuery = true)
    Page<ScottishBankruptOfficerDataModel> findScottishBankruptOfficers(String forename, String surname, String dob, String postcode, Pageable pageable);
}

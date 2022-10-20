package uk.gov.ch.repository.officers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.repository.query.Param;
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
     * @param fromDateOfBirth a DOB to search in a range from filter
     * @param toDateOfBirth a DOB to search in a range to filter
     * @param alias Alias filter
     * @param dob Date of birth filter
     * @param postcode Postcode filter
     * @return page A {@link Page} containing the search results and the pagination data
     */
    @Query(value = "select * "
                 + "from SCOTTISH_BANKRUPT_OFFICER "
                 + "where (:forename is null or upper(FORENAME_1) = upper(:forename)) "
                 + "and (:surname is null or upper(SURNAME) = upper(:surname)) "
                 +  "and (:alias is null or upper(ALIAS) like '%' || upper(:alias) || '%') "
                 + "and ((:fromDob is null and :toDob is null) "
                 + "or (DATE_OF_BIRTH = TO_DATE(:fromDob, 'YYYY-MM-DD')) " + "or (DATE_OF_BIRTH = TO_DATE(:toDob, 'YYYY-MM-DD'))"
                 + "or (DATE_OF_BIRTH between TO_DATE(:fromDob, 'YYYY-MM-DD') and TO_DATE(:toDob, 'YYYY-MM-DD')))"
                 + "and (:postcode is null or upper(replace(ADDRESS_POSTCODE, ' ', '')) = upper(replace(:postcode, ' ', ''))) "
                 + "order by START_DATE desc",
           countQuery = "select COUNT(*) "
                   + "from SCOTTISH_BANKRUPT_OFFICER "
                   + "where (:forename is null or upper(FORENAME_1) = upper(:forename)) "
                   + "and (:surname is null or upper(SURNAME) = upper(:surname)) "
                   + "and (:alias is null or upper(ALIAS) like '%' || upper(:alias) || '%') "
                   + "and ((:fromDob is null and :toDob is null) "
                   + "or (DATE_OF_BIRTH = TO_DATE(:fromDob, 'YYYY-MM-DD')) " + "or (DATE_OF_BIRTH = TO_DATE(:toDob, 'YYYY-MM-DD'))"
                   + "or (DATE_OF_BIRTH between TO_DATE(:fromDob, 'YYYY-MM-DD') and TO_DATE(:toDob, 'YYYY-MM-DD')))"
                   + "and (:postcode is null or upper(replace(ADDRESS_POSTCODE, ' ', '')) = upper(replace(:postcode, ' ', ''))) ",
           nativeQuery = true)
    Page<ScottishBankruptOfficerDataModel> findScottishBankruptOfficers(@Param("forename") String forename, @Param("surname") String surname, @Param("alias") String alias,  @Param("fromDob") String fromDateOfBirth, @Param("toDob") String toDateOfBirth, @Param("postcode") String postcode, Pageable pageable);
}

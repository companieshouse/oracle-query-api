package uk.gov.ch.repository.update;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.gov.ch.model.update.OverseasEntityData;

public interface OverseasEntityDataRepository extends PagingAndSortingRepository<OverseasEntityData, String> {

    @Query(value = "select "
            + "CB.corporate_body_id, CBD.email_address "
            + "from CORPORATE_BODY CB "
            + "inner join corporate_body_detail CBD on CB.corporate_body_id=CBD.corporate_body_id "
            + "where "
            + "CB.incorporation_number = ?" ,
            nativeQuery = true)
    OverseasEntityData getOverseasEntityData(String companyNumber);

}

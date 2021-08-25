package uk.gov.ch.repository.register;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.gov.ch.model.register.RegisterLocation;

public interface RegisterLocationRepository extends PagingAndSortingRepository<RegisterLocation, Long> {

    @Query(value = "select"
            + " ad.HOUSE_NAME_NUMBER AS SAIL_ADDRESS_LINE_1,"
            + " ad.STREET AS SAIL_ADDRESS_LINE_2,"
            + " ad.CARE_OF AS SAIL_ADDRESS_CARE_OF,"
            + " ad.COUNTRY_NAME AS SAIL_ADDRESS_COUNTRY,"
            + " ad.POST_TOWN AS SAIL_ADDRESS_LOCALITY,"
            + " ad.PO_BOX AS SAIL_ADDRESS_PO_BOX,"
            + " ad.POST_CODE AS SAIL_ADDRESS_POST_CODE,"
            + " ad.REGION AS SAIL_ADDRESS_REGION,"
            + " rt.register_type_desc,"
            + " rt.register_type_id"
            + " FROM corporate_body cb"
            + " INNER JOIN corbod_address_link cbal on cbal.corporate_body_id=cb.corporate_body_id"
            + " INNER JOIN address ad on ad.address_id=cbal.address_id"
            + " INNER JOIN corporate_body_address_type cbat on cbat.address_type_id=cbal.address_type_id"
            + " INNER JOIN corbod_regtyp_link cbrl on cbrl.corporate_body_id=cb.corporate_body_id"
            + " INNER JOIN register_type rt on rt.register_type_id=cbrl.register_type_id"
            + " INNER JOIN register_link_type rlt on rlt.register_link_type_id=cbrl.register_link_type_id"
            + " WHERE cb.incorporation_number = ?"
            + " AND cbat.address_type_id = 5000"
            + " AND rlt.register_link_type_id = 1"
            + " GROUP BY"
            + " rt.register_type_id,"
            + " cbat.address_type_desc,"
            + " ad.HOUSE_NAME_NUMBER,"
            + " ad.STREET, ad.CARE_OF,"
            + " ad.COUNTRY_NAME,"
            + " ad.POST_TOWN,"
            + " ad.PO_BOX,"
            + " ad.POST_CODE,"
            + " ad.REGION,"
            + " rt.register_type_desc,"
            + " rlt.register_link_type_desc", nativeQuery = true)
    Page<RegisterLocation> getRegisterLocation(String incorporationNumber, Pageable pageable);

}

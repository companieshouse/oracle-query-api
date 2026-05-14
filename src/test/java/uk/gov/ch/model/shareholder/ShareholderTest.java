package uk.gov.ch.model.shareholder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ShareholderTest {

    private Shareholder shareholderUnderTest;

    @BeforeEach
    void setUp() {
        shareholderUnderTest = new Shareholder();
    }

    @Test
    void testShareholderIdGetterAndSetter() {
        final Long shareholderId = 0L;
        shareholderUnderTest.setShareholderId(shareholderId);
        assertThat(shareholderUnderTest.getShareholderId()).isEqualTo(shareholderId);
    }

    @Test
    void testSharesGetterAndSetter() {
        final long shares = 0L;
        shareholderUnderTest.setShares(shares);
        assertThat(shareholderUnderTest.getShares()).isEqualTo(shares);
    }

    @Test
    void testClassOfSharesGetterAndSetter() {
        final String classOfShares = "classOfShares";
        shareholderUnderTest.setClassOfShares(classOfShares);
        assertThat(shareholderUnderTest.getClassOfShares()).isEqualTo(classOfShares);
    }

    @Test
    void testCurrencyGetterAndSetter() {
        final String currency = "currency";
        shareholderUnderTest.setCurrency(currency);
        assertThat(shareholderUnderTest.getCurrency()).isEqualTo(currency);
    }
}

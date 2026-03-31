package uk.gov.ch.model.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NameBaseTest {

    private NameBase nameBaseUnderTest;

    @BeforeEach
    void setUp() {
        nameBaseUnderTest = new NameBase() {};
    }

    @Test
    void testForename1GetterAndSetter() {
        final String forename1 = "forename1";
        nameBaseUnderTest.setForename1(forename1);
        assertThat(nameBaseUnderTest.getForename1()).isEqualTo(forename1);
    }

    @Test
    void testForename2GetterAndSetter() {
        final String forename2 = "forename2";
        nameBaseUnderTest.setForename2(forename2);
        assertThat(nameBaseUnderTest.getForename2()).isEqualTo(forename2);
    }

    @Test
    void testSurnameGetterAndSetter() {
        final String surname = "surname";
        nameBaseUnderTest.setSurname(surname);
        assertThat(nameBaseUnderTest.getSurname()).isEqualTo(surname);
    }
}



package uk.gov.ch.model.corporatebody.sqldatamodels;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegisteredOfficeAddressTest {

	private RegisteredOfficeAddress registeredOfficeAddressUnderTest;

	@BeforeEach
	void setUp() {
		registeredOfficeAddressUnderTest = new RegisteredOfficeAddress();
	}

	@Test
	void testAddressLine1GetterAndSetter() {
		final String addressLine1 = "addressLine1";
		registeredOfficeAddressUnderTest.setAddressLine1(addressLine1);
		assertThat(registeredOfficeAddressUnderTest.getAddressLine1()).isEqualTo(addressLine1);
	}

	@Test
	void testAddressLine2GetterAndSetter() {
		final String addressLine2 = "addressLine2";
		registeredOfficeAddressUnderTest.setAddressLine2(addressLine2);
		assertThat(registeredOfficeAddressUnderTest.getAddressLine2()).isEqualTo(addressLine2);
	}

	@Test
	void testPostalCodeGetterAndSetter() {
		final String postalCode = "postalCode";
		registeredOfficeAddressUnderTest.setPostalCode(postalCode);
		assertThat(registeredOfficeAddressUnderTest.getPostalCode()).isEqualTo(postalCode);
	}

	@Test
	void testCareOfNameGetterAndSetter() {
		final String careOfName = "careOf";
		registeredOfficeAddressUnderTest.setCareOfName(careOfName);
		assertThat(registeredOfficeAddressUnderTest.getCareOfName()).isEqualTo(careOfName);
	}

	@Test
	void testCountryGetterAndSetter() {
		final String country = "country";
		registeredOfficeAddressUnderTest.setCountry(country);
		assertThat(registeredOfficeAddressUnderTest.getCountry()).isEqualTo(country);
	}

	@Test
	void testLocalityGetterAndSetter() {
		final String locality = "locality";
		registeredOfficeAddressUnderTest.setLocality(locality);
		assertThat(registeredOfficeAddressUnderTest.getLocality()).isEqualTo(locality);
	}

	@Test
	void testPoBoxGetterAndSetter() {
		final String poBox = "poBox";
		registeredOfficeAddressUnderTest.setPoBox(poBox);
		assertThat(registeredOfficeAddressUnderTest.getPoBox()).isEqualTo(poBox);
	}

	@Test
	void testPremisesGetterAndSetter() {
		final String premises = "premises";
		registeredOfficeAddressUnderTest.setPremises(premises);
		assertThat(registeredOfficeAddressUnderTest.getPremises()).isEqualTo(premises);
	}

	@Test
	void testRegionGetterAndSetter() {
		final String region = "region";
		registeredOfficeAddressUnderTest.setRegion(region);
		assertThat(registeredOfficeAddressUnderTest.getRegion()).isEqualTo(region);
	}
}

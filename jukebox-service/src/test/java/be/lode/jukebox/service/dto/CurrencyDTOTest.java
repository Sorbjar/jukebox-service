package be.lode.jukebox.service.dto;

import static org.junit.Assert.assertEquals;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import be.lode.setup.ResetDBSetupLiveData;
import be.lode.setup.ResetDBSetupTestData;

public class CurrencyDTOTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ResetDBSetupTestData.run();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ResetDBSetupLiveData.run();
	}

	@Test
	public void testEquals() {
		EqualsVerifier.forClass(CurrencyDTO.class).usingGetClass()
				.suppress(Warning.NONFINAL_FIELDS).verify();
	}

	@Test
	public void testToString() {
		CurrencyDTO o = new CurrencyDTO();
		String test = "";
		assertEquals(test, o.toString());
		test = "firstTestToString";
		o.setName(test);
		assertEquals(test, o.toString());
	}

}

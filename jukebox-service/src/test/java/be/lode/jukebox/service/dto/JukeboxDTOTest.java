package be.lode.jukebox.service.dto;

import static org.junit.Assert.assertEquals;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import be.lode.setup.ResetDBSetupLiveData;
import be.lode.setup.ResetDBSetupTestData;

public class JukeboxDTOTest {

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
		EqualsVerifier.forClass(JukeboxDTO.class).usingGetClass()
				.suppress(Warning.NONFINAL_FIELDS).verify();
	}

	@Test
	public void testToString() {
		JukeboxDTO o = new JukeboxDTO();
		String test = "";
		assertEquals(test, o.toString());
		test = "testToString";
		o.setName(test);
		assertEquals(test, o.toString());
	}
}
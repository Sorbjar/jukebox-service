package be.lode.jukebox.service.dto;

import static org.junit.Assert.assertEquals;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import be.lode.setup.ResetDBSetupLiveData;
import be.lode.setup.ResetDBSetupTestData;

/**
 * The Class PlaylistDTOTest.
 */
public class PlaylistDTOTest {

	/**
	 * Sets the up before class.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ResetDBSetupTestData.run();
	}

	/**
	 * Tear down after class.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ResetDBSetupLiveData.run();
	}

	/**
	 * Test equals.
	 */
	@Test
	public void testEquals() {
		EqualsVerifier.forClass(PlaylistDTO.class).usingGetClass()
				.suppress(Warning.NONFINAL_FIELDS).verify();
	}

	/**
	 * Test to string.
	 */
	@Test
	public void testToString() {
		PlaylistDTO o = new PlaylistDTO();
		String test = "";
		assertEquals(test, o.toString());
		test = "testToString";
		o.setName(test);
		assertEquals(test, o.toString());
	}

}

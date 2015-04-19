package be.lode.jukebox.service.dto;

import static org.junit.Assert.assertEquals;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import be.lode.jukebox.business.model.Jukebox;

public class AccountDTOTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEquals() {
		EqualsVerifier.forClass(Jukebox.class).usingGetClass().verify();
	}

	@Test
	public void testToString() {
		AccountDTO o = new AccountDTO();
		String test = " ";
		assertEquals(test, o.toString());
		test = "firstTestToString";
		o.setFirstName(test);
		test = "lastTestToString";
		o.setLastName(test);
		test = "firstTestToString lastTestToString";
		assertEquals(test, o.toString());
	}

}

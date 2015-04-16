package be.lode.jukebox.service.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import be.lode.jukebox.service.dto.AccountDTO;
import be.lode.jukebox.service.dto.JukeboxDTO;
import be.lode.setup.ClearThenSetupDBData;

public class JukeboxManagerTest {
	private JukeboxManager mgr;

	@Before
	public void setUp() throws Exception {
		ClearThenSetupDBData.run();
		mgr = new JukeboxManager();
	}

	@Test
	public void testGetAccount() {
		AccountDTO oDTO = new AccountDTO();
		oDTO.setEmailAddress("emailAddress");
		oDTO.setFirstName("firstName");
		oDTO.setLastName("lastName");
		oDTO.setServiceId("10153294269263586");
		oDTO.setServiceName("facebook");
		oDTO.setId("1");
		AccountDTO getO = mgr.getAccount(oDTO);

		assertEquals(oDTO.getId(), getO.getId());
		assertEquals("lode.deckers@gmail.com", getO.getEmailAddress());
		assertEquals("Lode", getO.getFirstName());
		assertEquals("Deckers", getO.getLastName());
		assertEquals(oDTO.getServiceId(), getO.getServiceId());
		assertEquals(oDTO.getServiceName(), getO.getServiceName());

		oDTO = new AccountDTO();
		oDTO.setEmailAddress("emailAddress");
		oDTO.setFirstName("firstName");
		oDTO.setLastName("lastName");
		oDTO.setServiceId("serviceId");
		oDTO.setServiceName("serviceName");
		oDTO.setId("1");
		getO = mgr.getAccount(oDTO);

		assertEquals(oDTO.getId(), getO.getId());
		assertEquals(oDTO.getEmailAddress(), getO.getEmailAddress());
		assertEquals(oDTO.getFirstName(), getO.getFirstName());
		assertEquals(oDTO.getLastName(), getO.getLastName());
		assertEquals(oDTO.getServiceId(), getO.getServiceId());
		assertEquals(oDTO.getServiceName(), getO.getServiceName());
	}

	@Test
	public void testGetJukeboxes() {
		AccountDTO oDTO = new AccountDTO();
		oDTO.setEmailAddress("emailAddress");
		oDTO.setFirstName("firstName");
		oDTO.setLastName("lastName");
		oDTO.setServiceId("10153294269263586");
		oDTO.setServiceName("facebook");
		oDTO.setId("1");
		AccountDTO getO = mgr.getAccount(oDTO);
		List<JukeboxDTO> jbList = mgr.getJukeboxes(getO);
		assertNotNull("List not null", jbList);
		assertTrue("list not empty", jbList.size() > 0);
	}

	@Test
	public void testIsValidEmailAddress() {
		String test = "";
		test = "test@test.com";
		assertTrue(test + " - valid", mgr.isValidEmailAddress(test));
		test = "te.st@test.com";
		assertTrue(test + " - valid", mgr.isValidEmailAddress(test));
		test = "test@te.st.com";
		assertTrue(test + " - valid", mgr.isValidEmailAddress(test));
		test = "tes.t@tes.t.com";
		assertTrue(test + " - valid", mgr.isValidEmailAddress(test));
		test = "test@testcom";
		assertTrue(test + " - valid", mgr.isValidEmailAddress(test));
		test = "4test@test.comcom";
		assertTrue(test + " - valid", mgr.isValidEmailAddress(test));
		test = "testtestcom";
		assertFalse(test + " - not valid", mgr.isValidEmailAddress(test));
		test = "testtest.com";
		assertFalse(test + " - not valid", mgr.isValidEmailAddress(test));
	}

	@Test
	public void testSaveAccountDTO() {
		AccountDTO oDTO = new AccountDTO();
		oDTO.setEmailAddress("newaddress");
		oDTO.setFirstName("newFirstname");
		oDTO.setLastName("newLast");
		oDTO.setServiceId("10153294269263586");
		oDTO.setServiceName("facebook");
		oDTO.setId("1");
		AccountDTO saved = mgr.save(oDTO);

		assertEquals(oDTO.getId(), saved.getId());
		assertEquals(oDTO.getEmailAddress(), saved.getEmailAddress());
		assertEquals(oDTO.getFirstName(), saved.getFirstName());
		assertEquals(oDTO.getLastName(), saved.getLastName());
		assertEquals(oDTO.getServiceId(), saved.getServiceId());
		assertEquals(oDTO.getServiceName(), saved.getServiceName());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ClearThenSetupDBData.run();
	}

}

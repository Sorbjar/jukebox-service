package be.lode.jukebox.service.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import be.lode.general.repository.Repository;
import be.lode.jukebox.business.model.Account;
import be.lode.jukebox.business.model.Jukebox;
import be.lode.jukebox.business.model.Playlist;
import be.lode.jukebox.business.model.enums.Role;
import be.lode.jukebox.business.repo.JukeboxRepository;
import be.lode.jukebox.business.repo.PlaylistRepository;
import be.lode.jukebox.service.dto.AccountDTO;
import be.lode.jukebox.service.dto.JukeboxDTO;
import be.lode.jukebox.service.mapper.JukeboxModelMapper;
import be.lode.setup.ClearThenSetupTestDBData;

public class JukeboxManagerTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ClearThenSetupTestDBData.run();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ClearThenSetupTestDBData.run();
	}

	private JukeboxModelMapper mapper;
	private JukeboxManager mgr;

	@Before
	public void setUp() throws Exception {
		ClearThenSetupTestDBData.run();
		mgr = new JukeboxManager();
		mapper = new JukeboxModelMapper();
	}

	@Test
	public void testCreateNewJukebox() {
		AccountDTO oDTO = new AccountDTO();
		oDTO.setEmailAddress("newaddress");
		oDTO.setFirstName("newFirstname");
		oDTO.setLastName("newLast");
		oDTO.setServiceId("1015329426926456");
		oDTO.setServiceName("facebook");
		oDTO = mgr.save(oDTO);
		mgr.createNewJukebox(oDTO);
		JukeboxModelMapper mapper = new JukeboxModelMapper();
		Jukebox current = mapper.map(mgr.getCurrentJukeboxDTO(), Jukebox.class);
		Repository<Jukebox> jRepo = new JukeboxRepository(mgr.getEmf());
		current = jRepo.find(current);
		assertNotNull("createJukebox - current jukebox not null", current);
		assertEquals(1, current.getAccountRoles().size());
		Account acc = mapper.map(oDTO, Account.class);
		assertTrue("new jukebox contains account", current.getAccountRoles()
				.containsKey(acc));
		assertEquals(Role.Administrator, current.getAccountRoles().get(acc));

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

	@Test
	public void testSetCurrentJukeboxJukeboxDTO() {
		AccountDTO oDTO = new AccountDTO();
		oDTO.setEmailAddress("newaddress");
		oDTO.setFirstName("newFirstname");
		oDTO.setLastName("newLast");
		oDTO.setServiceId("10153294269263586");
		oDTO.setServiceName("facebook");
		oDTO.setId("1");
		assertNull(mgr.getCurrentJukeboxDTO());
		AccountDTO getO = mgr.getAccount(oDTO);
		for (JukeboxDTO jb : mgr.getJukeboxes(getO)) {
			mgr.setCurrentJukebox(jb);
			assertNotNull(mgr.getCurrentJukeboxDTO());
			assertEquals(jb, mgr.getCurrentJukeboxDTO());
		}

	}

	@Test
	public void testGetSavedPlaylists() {
		Jukebox jb = new Jukebox();
		for (int i = 0; i < 5; i++) {
			Playlist pl = new Playlist("test" + Integer.toString(i));
			Repository<Playlist> pRepo = new PlaylistRepository(mgr.getEmf());
			pl = pRepo.save(pl);
			jb.getSavedPlaylists().add(pl);
		}
		Repository<Jukebox> jRepo = new JukeboxRepository(mgr.getEmf());
		jb = jRepo.save(jb);

		JukeboxDTO jbDTO = mapper.map(jb, JukeboxDTO.class);
		assertNotNull(mgr.getSavedPlaylists(jbDTO));

		assertEquals(5, mgr.getSavedPlaylists(jbDTO).size());
	}
}

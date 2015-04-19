package be.lode.jukebox.service.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import be.lode.jukebox.business.model.Account;
import be.lode.jukebox.business.model.Jukebox;
import be.lode.jukebox.business.model.OAuthApiInfo;
import be.lode.jukebox.business.model.Playlist;
import be.lode.jukebox.service.dto.AccountDTO;
import be.lode.jukebox.service.dto.JukeboxDTO;
import be.lode.jukebox.service.dto.OAuthApiInfoDTO;
import be.lode.jukebox.service.dto.PlaylistDTO;
import be.lode.setup.ClearThenSetupDBData;

public class JukeboxModelMapperTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ClearThenSetupDBData.run();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ClearThenSetupDBData.run();
	}

	private JukeboxModelMapper modelMapper;

	@Test
	public void mapAccountDTOToObj() {
		AccountDTO oDTO = new AccountDTO();
		oDTO.setEmailAddress("emailAddress");
		oDTO.setFirstName("firstName");
		oDTO.setLastName("lastName");
		oDTO.setServiceId("serviceId");
		oDTO.setServiceName("serviceName");
		oDTO.setId("10");

		Account o = modelMapper.map(oDTO, Account.class);

		assertEquals(Long.valueOf(oDTO.getId()).longValue(), o.getId());
		assertEquals(oDTO.getEmailAddress(), o.getEmailAddress());
		assertEquals(oDTO.getFirstName(), o.getFirstName());
		assertEquals(oDTO.getLastName(), o.getLastName());
		assertEquals(oDTO.getServiceId(), o.getServiceId());
		assertEquals(oDTO.getServiceName(), o.getServiceName());
	}

	@Test
	public void mapAccountToDTO() {

		Account o = new Account("email", "firstname", "lastName", "serviceId",
				"serviceName");
		o.setId(10);

		AccountDTO oDTO = modelMapper.map(o, AccountDTO.class);

		assertEquals(String.valueOf(o.getId()), oDTO.getId());
		assertEquals(o.getEmailAddress(), oDTO.getEmailAddress());
		assertEquals(o.getFirstName(), oDTO.getFirstName());
		assertEquals(o.getLastName(), oDTO.getLastName());
		assertEquals(o.getServiceId(), oDTO.getServiceId());
		assertEquals(o.getServiceName(), oDTO.getServiceName());
	}

	@Test
	public void mapJukeboxDTOToObj() {
		JukeboxDTO oDTO = new JukeboxDTO();
		oDTO.setId("25");
		oDTO.setName("aaa");

		Jukebox o = modelMapper.map(oDTO, Jukebox.class);

		assertEquals(Long.valueOf(oDTO.getId()).longValue(), o.getId());
		assertEquals(oDTO.getName(), o.getName());
	}

	@Test
	public void mapJukeboxToDTO() {

		Account acc = new Account("email", "firstname", "lastName",
				"serviceId", "serviceName");
		acc.setId(10);
		Jukebox o = new Jukebox("new name", acc);
		o.setId(20);

		JukeboxDTO oDTO = modelMapper.map(o, JukeboxDTO.class);

		assertEquals(String.valueOf(o.getId()), oDTO.getId());
		assertEquals(o.getName(), oDTO.getName());
	}

	@Test
	public void mapOAuthApiInfoToDTO() {

		OAuthApiInfo o = new OAuthApiInfo("TestName", "FacebookApi", "TestKey",
				"TestSecret", "TestURL");

		OAuthApiInfoDTO oDTO = modelMapper.map(o, OAuthApiInfoDTO.class);
		assertEquals(o.getApiKey(), oDTO.getApiKey());
		assertEquals(o.getApiSecret(), oDTO.getApiSecret());
		assertEquals(o.getExampleGetRequest(), oDTO.getExampleGetRequest());
		assertEquals(o.getName(), oDTO.getName());
		assertEquals(o.getScribeApiName(), oDTO.getScribeApiName());
	}

	@Test
	public void mapValidate() {
		modelMapper.validate();
	}

	@Test
	public void mapPlaylistToDTO() {
		Playlist o = new Playlist("mapPlaylistToDTO");
		o.setId(17);

		PlaylistDTO oDTO = modelMapper.map(o, PlaylistDTO.class);
		assertEquals(String.valueOf(o.getId()), oDTO.getId());
		assertEquals(o.getName(), oDTO.getName());
	}

	@Test
	public void mapPlaylistDTOToObj() {
		PlaylistDTO oDTO = new PlaylistDTO();
		oDTO.setId("17");
		oDTO.setName("mapPlaylistDTOToObj");

		Playlist o = modelMapper.map(oDTO, Playlist.class);
		assertEquals(oDTO.getId(), String.valueOf(o.getId()));
		assertEquals(oDTO.getName(), o.getName());
	}

	@Before
	public void setUp() throws Exception {
		modelMapper = new JukeboxModelMapper();
	}
}

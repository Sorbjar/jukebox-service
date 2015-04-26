package be.lode.jukebox.service.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import be.lode.general.repository.Repository;
import be.lode.jukebox.business.model.Account;
import be.lode.jukebox.business.model.Jukebox;
import be.lode.jukebox.business.model.Playlist;
import be.lode.jukebox.business.model.Song;
import be.lode.jukebox.business.model.enums.Role;
import be.lode.jukebox.business.repo.AccountRepository;
import be.lode.jukebox.business.repo.JukeboxRepository;
import be.lode.jukebox.business.repo.PlaylistRepository;
import be.lode.jukebox.business.repo.SongRepository;
import be.lode.jukebox.service.dto.AccountDTO;
import be.lode.jukebox.service.dto.JukeboxDTO;
import be.lode.jukebox.service.dto.PlaylistDTO;
import be.lode.jukebox.service.dto.SongDTO;
import be.lode.jukebox.service.mapper.JukeboxModelMapper;
import be.lode.setup.ResetDBSetupTestData;

public class JukeboxManagerTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ResetDBSetupTestData.run();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ResetDBSetupTestData.run();
	}

	private JukeboxModelMapper mapper;

	private JukeboxManager mgr;

	@Before
	public void setUp() throws Exception {
		ResetDBSetupTestData.run();
		mgr = new JukeboxManager();
		mapper = new JukeboxModelMapper();
	}

	@Test
	public void testAddSongSource() {
		Song s1 = new Song("testArtist1", "testTitle1", "testPath1");
		Song s2 = new Song("testArtist2", "testTitle2", "testPath2");
		Song s3 = new Song("testArtist3", "testTitle3", "testPath3");
		Playlist pl = new Playlist("testAddSong");
		Repository<Song> sRepo = new SongRepository(mgr.getEmf());
		s1 = sRepo.save(s1);
		s2 = sRepo.save(s2);
		s3 = sRepo.save(s3);
		pl.addSong(s1);
		pl.addSong(s2);
		pl.addSong(s3);

		Repository<Playlist> pRepo = new PlaylistRepository(mgr.getEmf());
		pl = pRepo.save(pl);
		JukeboxModelMapper mapper = new JukeboxModelMapper();
		mgr.setCurrentPlaylist(mapper.map(pl, PlaylistDTO.class));

		SongDTO dto1 = mapper.map(s1, SongDTO.class);
		dto1.setPlayListOrder("0");
		SongDTO dto2 = mapper.map(s2, SongDTO.class);
		dto2.setPlayListOrder("1");
		SongDTO dto3 = mapper.map(s3, SongDTO.class);
		dto3.setPlayListOrder("2");
		Playlist test = pRepo.find(mapper.map(mgr.getCurrentPlaylistDTO(),
				Playlist.class));
		for (Map.Entry<Integer, Song> entry : test.getSongs().entrySet()) {
			if (entry.getKey().equals(0)) {
				assertEquals(s1.getArtist(), entry.getValue().getArtist());
			} else if (entry.getKey().equals(1)) {
				assertEquals(s2.getArtist(), entry.getValue().getArtist());
			} else if (entry.getKey().equals(2)) {
				assertEquals(s3.getArtist(), entry.getValue().getArtist());
			}
		}
		SongDTO dto4 = new SongDTO();
		dto4.setArtist("dto4Art");
		dto4.setPath("dto4Path");
		dto4.setTitle("dto4titl");

		Song s4 = mapper.map(dto4, Song.class);
		s4 = sRepo.save(s4);
		dto4 = mapper.map(s4, SongDTO.class);
		mgr.addSong(dto4);
		test = pRepo.find(mapper.map(mgr.getCurrentPlaylistDTO(),
				Playlist.class));
		for (Map.Entry<Integer, Song> entry : test.getSongs().entrySet()) {
			if (entry.getKey().equals(0)) {
				assertEquals(s1.getArtist(), entry.getValue().getArtist());
			} else if (entry.getKey().equals(1)) {
				assertEquals(s2.getArtist(), entry.getValue().getArtist());
			} else if (entry.getKey().equals(2)) {
				assertEquals(s3.getArtist(), entry.getValue().getArtist());
			} else if (entry.getKey().equals(3)) {
				assertEquals(s4.getArtist(), entry.getValue().getArtist());
			}
		}
	}

	@Test
	public void testAddSongSourceTarget() {
		Song s1 = new Song("testArtist1", "testTitle1", "testPath1");
		Song s2 = new Song("testArtist2", "testTitle2", "testPath2");
		Song s3 = new Song("testArtist3", "testTitle3", "testPath3");
		Playlist pl = new Playlist("testAddSong");
		Repository<Song> sRepo = new SongRepository(mgr.getEmf());
		s1 = sRepo.save(s1);
		s2 = sRepo.save(s2);
		s3 = sRepo.save(s3);
		pl.addSong(s1);
		pl.addSong(s2);
		pl.addSong(s3);

		Repository<Playlist> pRepo = new PlaylistRepository(mgr.getEmf());
		pl = pRepo.save(pl);
		JukeboxModelMapper mapper = new JukeboxModelMapper();
		mgr.setCurrentPlaylist(mapper.map(pl, PlaylistDTO.class));

		SongDTO dto1 = mapper.map(s1, SongDTO.class);
		dto1.setPlayListOrder("0");
		SongDTO dto2 = mapper.map(s2, SongDTO.class);
		dto2.setPlayListOrder("1");
		SongDTO dto3 = mapper.map(s3, SongDTO.class);
		dto3.setPlayListOrder("2");
		Playlist test = pRepo.find(mapper.map(mgr.getCurrentPlaylistDTO(),
				Playlist.class));
		for (Map.Entry<Integer, Song> entry : test.getSongs().entrySet()) {
			if (entry.getKey().equals(0)) {
				assertEquals(s1.getArtist(), entry.getValue().getArtist());
			} else if (entry.getKey().equals(1)) {
				assertEquals(s2.getArtist(), entry.getValue().getArtist());
			} else if (entry.getKey().equals(2)) {
				assertEquals(s3.getArtist(), entry.getValue().getArtist());
			}
		}
		SongDTO dto4 = new SongDTO();
		dto4.setArtist("dto4Art");
		dto4.setPath("dto4Path");
		dto4.setTitle("dto4titl");

		Song s4 = mapper.map(dto4, Song.class);
		s4 = sRepo.save(s4);
		dto4 = mapper.map(s4, SongDTO.class);
		mgr.addSong(dto4, dto2);
		test = pRepo.find(mapper.map(mgr.getCurrentPlaylistDTO(),
				Playlist.class));
		for (Map.Entry<Integer, Song> entry : test.getSongs().entrySet()) {
			if (entry.getKey().equals(0)) {
				assertEquals(s1.getArtist(), entry.getValue().getArtist());
			} else if (entry.getKey().equals(1)) {
				assertEquals(s4.getArtist(), entry.getValue().getArtist());
			} else if (entry.getKey().equals(2)) {
				assertEquals(s2.getArtist(), entry.getValue().getArtist());
			} else if (entry.getKey().equals(3)) {
				assertEquals(s3.getArtist(), entry.getValue().getArtist());
			}
		}
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
		oDTO.setId("2");
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
		oDTO.setId("3");
		getO = mgr.getAccount(oDTO);

		assertEquals(oDTO.getId(), getO.getId());
		assertEquals(oDTO.getEmailAddress(), getO.getEmailAddress());
		assertEquals(oDTO.getFirstName(), getO.getFirstName());
		assertEquals(oDTO.getLastName(), getO.getLastName());
		assertEquals(oDTO.getServiceId(), getO.getServiceId());
		assertEquals(oDTO.getServiceName(), getO.getServiceName());
	}

	@Test
	public void testgetAllSongs() {
		assertNotNull("all songs", mgr.getAllSongs());
		assertTrue(mgr.getAllSongs().size() > 0);
		for (SongDTO songDTO : mgr.getAllSongs()) {
			assertNotNull(songDTO);
			assertNotNull(songDTO.getId());
		}
	}

	@Test
	public void testGetJukeboxes() {
		AccountDTO oDTO = new AccountDTO();
		oDTO.setEmailAddress("emailAddress");
		oDTO.setFirstName("firstName");
		oDTO.setLastName("lastName");
		oDTO.setServiceId("10153294269263586");
		oDTO.setServiceName("facebook");
		oDTO.setId("2");
		AccountDTO getO = mgr.getAccount(oDTO);
		List<JukeboxDTO> jbList = mgr.getJukeboxes(getO);
		assertNotNull("List not null", jbList);
		assertTrue("list not empty", jbList.size() > 0);
	}

	@Test
	public void testGetNextSong() {
		Account acc = new Account("testGetNextSonga", "testGetNextSongb",
				"testGetNextSongc", "testGetNextSongd", "testGetNextSonge");

		Repository<Account> aRepo = new AccountRepository(mgr.getEmf());
		acc = aRepo.save(acc);
		Jukebox o = new Jukebox("testGetNextSong", acc);

		String artist = "s1" + "artist";
		String title = "s1" + "title";
		String path = "s1" + "path";

		Song s1 = new Song(artist, title, path);

		artist = "s2" + "artist";
		title = "s2" + "title";
		path = "s2" + "path";

		Song s2 = new Song(artist, title, path);

		artist = "s3" + "artist";
		title = "s3" + "title";
		path = "s3" + "path";

		Song s3 = new Song(artist, title, path);

		Repository<Song> sRepo = new SongRepository(mgr.getEmf());
		s1 = sRepo.save(s1);
		s2 = sRepo.save(s2);
		s3 = sRepo.save(s3);

		o.getCurrentPlaylist().addSong(s1);
		o.getCurrentPlaylist().addSong(s2);
		o.getCurrentPlaylist().addSong(s3);

		artist = "s4" + "artist";
		title = "s4" + "title";
		path = "s4" + "path";

		Song s4 = new Song(artist, title, path);

		s4 = sRepo.save(s4);
		Repository<Jukebox> jRepo = new JukeboxRepository(mgr.getEmf());

		o = jRepo.save(o);
		JukeboxModelMapper mapper = new JukeboxModelMapper();
		mgr.setCurrentJukebox(mapper.map(o, JukeboxDTO.class));

		mgr.setCurrentSong(mapper.map(s1, SongDTO.class));

		SongDTO dto2 = mapper.map(s2, SongDTO.class);
		dto2.setPlayListOrder("0");

		SongDTO test =  mgr.getNextSong();
		assertEquals(dto2.getArtist(),test.getArtist());
		assertEquals(dto2.getTitle(),test.getTitle());

		o.getMandatoryPlaylist().addSong(s4);

		o = jRepo.save(o);
		mgr.setCurrentJukebox(mapper.map(o, JukeboxDTO.class));
		mgr.setCurrentSong(mapper.map(s1, SongDTO.class));

		SongDTO dto4 = mapper.map(s4, SongDTO.class);
		dto4.setPlayListOrder("0");

		assertEquals(dto4, mgr.getNextSong());
	}

	@Test
	public void testGetPreviousSong() {
		Account acc = new Account("testGetPreviousSonga", "testGetPreviousSongb",
				"testGetPreviousSongc", "testGetPreviousSongd", "testGetPreviousSonge");

		Repository<Account> aRepo = new AccountRepository(mgr.getEmf());
		acc = aRepo.save(acc);
		Jukebox o = new Jukebox("testGetPreviousSong", acc);

		String artist = "s1" + "artist";
		String title = "s1" + "title";
		String path = "s1" + "path";

		Song s1 = new Song(artist, title, path);

		artist = "s2" + "artist";
		title = "s2" + "title";
		path = "s2" + "path";

		Song s2 = new Song(artist, title, path);

		artist = "s3" + "artist";
		title = "s3" + "title";
		path = "s3" + "path";

		Song s3 = new Song(artist, title, path);

		Repository<Song> sRepo = new SongRepository(mgr.getEmf());
		s1 = sRepo.save(s1);
		s2 = sRepo.save(s2);
		s3 = sRepo.save(s3);

		o.getCurrentPlaylist().addSong(s1);
		o.getCurrentPlaylist().addSong(s2);
		o.getCurrentPlaylist().addSong(s3);

		artist = "s4" + "artist";
		title = "s4" + "title";
		path = "s4" + "path";

		Song s4 = new Song(artist, title, path);

		s4 = sRepo.save(s4);
		Repository<Jukebox> jRepo = new JukeboxRepository(mgr.getEmf());

		o = jRepo.save(o);
		JukeboxModelMapper mapper = new JukeboxModelMapper();
		mgr.setCurrentJukebox(mapper.map(o, JukeboxDTO.class));

		mgr.setCurrentSong(mapper.map(s2, SongDTO.class));

		SongDTO dto1 = mapper.map(s1, SongDTO.class);
		dto1.setPlayListOrder("0");

		assertNull(mgr.getPreviousSong());

		o.getMandatoryPlaylist().addSong(s4);

		o = jRepo.save(o);
		mgr.setCurrentJukebox(mapper.map(o, JukeboxDTO.class));
		mgr.setCurrentSong(mapper.map(s1, SongDTO.class));

		SongDTO dto4 = mapper.map(s4, SongDTO.class);
		dto4.setPlayListOrder("0");

		assertEquals(dto4, mgr.getPreviousSong());
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
	public void testLoop() {
		JukeboxModelMapper mapper = new JukeboxModelMapper();
		Account acc = new Account("testGetNextSonga", "testGetNextSongb",
				"testGetNextSongc", "testGetNextSongd", "testGetNextSonge");

		Repository<Account> aRepo = new AccountRepository(mgr.getEmf());
		acc = aRepo.save(acc);
		Jukebox o = new Jukebox("testGetNextSong", acc);

		Repository<Jukebox> jRepo = new JukeboxRepository(mgr.getEmf());
		o = jRepo.save(o);

		mgr.setCurrentJukebox(mapper.map(o, JukeboxDTO.class));

		assertFalse(mgr.isLooped());

		mgr.changeLoopState();

		assertTrue(mgr.isLooped());
	}

	@Test
	public void testRandom() {
		JukeboxModelMapper mapper = new JukeboxModelMapper();
		Account acc = new Account("testGetNextSonga", "testGetNextSongb",
				"testGetNextSongc", "testGetNextSongd", "testGetNextSonge");

		Repository<Account> aRepo = new AccountRepository(mgr.getEmf());
		acc = aRepo.save(acc);
		Jukebox o = new Jukebox("testGetNextSong", acc);

		Repository<Jukebox> jRepo = new JukeboxRepository(mgr.getEmf());
		o = jRepo.save(o);

		mgr.setCurrentJukebox(mapper.map(o, JukeboxDTO.class));

		assertFalse(mgr.isRandom());

		mgr.changeRandomState();

		assertTrue(mgr.isRandom());
	}

	@Test
	public void testReorderPlaylist() {
		Song s1 = new Song("testArtist1", "testTitle1", "testPath1");
		Song s2 = new Song("testArtist2", "testTitle2", "testPath2");
		Playlist pl = new Playlist("testReorderPlaylist");
		Repository<Song> sRepo = new SongRepository(mgr.getEmf());
		s1 = sRepo.save(s1);
		s2 = sRepo.save(s2);
		pl.addSong(s1);
		pl.addSong(s2);

		Repository<Playlist> pRepo = new PlaylistRepository(mgr.getEmf());
		pl = pRepo.save(pl);
		JukeboxModelMapper mapper = new JukeboxModelMapper();
		mgr.setCurrentPlaylist(mapper.map(pl, PlaylistDTO.class));

		SongDTO dto1 = mapper.map(s1, SongDTO.class);
		dto1.setPlayListOrder("0");
		SongDTO dto2 = mapper.map(s2, SongDTO.class);
		dto2.setPlayListOrder("1");
		Playlist test = pRepo.find(mapper.map(mgr.getCurrentPlaylistDTO(),
				Playlist.class));
		for (Map.Entry<Integer, Song> entry : test.getSongs().entrySet()) {
			if (entry.getKey().equals(0)) {
				assertEquals(s1.getArtist(), entry.getValue().getArtist());
			} else if (entry.getKey().equals(1)) {
				assertEquals(s2.getArtist(), entry.getValue().getArtist());
			}
		}
		mgr.reorderPlaylist(dto1, dto2);
		test = pRepo.find(mapper.map(mgr.getCurrentPlaylistDTO(),
				Playlist.class));
		for (Map.Entry<Integer, Song> entry : test.getSongs().entrySet()) {
			if (entry.getKey().equals(0)) {
				assertEquals(s2.getArtist(), entry.getValue().getArtist());
			} else if (entry.getKey().equals(1)) {
				assertEquals(s1.getArtist(), entry.getValue().getArtist());
			}
		}
	}

	@Test
	public void testSaveAccountDTO() {
		AccountDTO oDTO = new AccountDTO();
		oDTO.setEmailAddress("newaddress");
		oDTO.setFirstName("newFirstname");
		oDTO.setLastName("newLast");
		oDTO.setServiceId("10153294269263586");
		oDTO.setServiceName("facebook");
		oDTO.setId("3");
		AccountDTO saved = mgr.save(oDTO);

		assertEquals(oDTO.getId(), saved.getId());
		assertEquals(oDTO.getEmailAddress(), saved.getEmailAddress());
		assertEquals(oDTO.getFirstName(), saved.getFirstName());
		assertEquals(oDTO.getLastName(), saved.getLastName());
		assertEquals(oDTO.getServiceId(), saved.getServiceId());
		assertEquals(oDTO.getServiceName(), saved.getServiceName());
	}

	@Test
	public void testSaveJukeboxDTO() {
		JukeboxDTO oDTO = new JukeboxDTO();
		oDTO.setName("testSaveJukeboxDTO");
		JukeboxDTO saved = mgr.save(oDTO);

		assertEquals(oDTO.getName(), saved.getName());

		oDTO = new JukeboxDTO();
		oDTO.setName("testSaveJukeboxDTO2");
		oDTO.setId("6");
		saved = mgr.save(oDTO);

		assertEquals(oDTO.getName(), saved.getName());
		assertEquals(oDTO.getId(), saved.getId());
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
	public void testSetCurrentSong() {
		Repository<Song> sRepo = new SongRepository(mgr.getEmf());
		JukeboxModelMapper mapper = new JukeboxModelMapper();
		Song s = new Song();
		{
			for (Song songitem : sRepo.getList()) {
				s = songitem;
				SongDTO sDTO = mapper.map(s, SongDTO.class);
				sDTO.setPlayListOrder("0");
				mgr.setCurrentSong(sDTO);

				assertEquals(sDTO, mgr.getCurrentSongDTO());
			}
		}
	}
}

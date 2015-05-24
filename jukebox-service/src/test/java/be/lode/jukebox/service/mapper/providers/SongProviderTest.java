package be.lode.jukebox.service.mapper.providers;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import be.lode.jukebox.business.model.Song;
import be.lode.jukebox.service.dto.SongDTO;
import be.lode.jukebox.service.mapper.JukeboxModelMapper;
import be.lode.setup.ResetDBSetupLiveData;
import be.lode.setup.ResetDBSetupTestData;

public class SongProviderTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ResetDBSetupTestData.run();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ResetDBSetupLiveData.run();
	}

	private JukeboxModelMapper modelMapper;

	@Test
	public void mapSongToDTO() {
		Song o = new Song("artistmapSongToDTO", "titlemapSongToDTO",
				"pathmapSongToDTO");
		o.getMetadataProperties().put("creator", "testcre");
		o.setId(17);

		SongDTO oDTO = modelMapper.map(o, SongDTO.class);
		assertEquals(String.valueOf(o.getId()), oDTO.getId());
		assertEquals(o.getArtist(), oDTO.getArtist());
		assertEquals(o.getTitle(), oDTO.getTitle());
		assertEquals(o.getPath(), oDTO.getPath());
		assertEquals(o.getMetadataProperties().get("creator"),
				oDTO.getCreator());
	}

	@Before
	public void setUp() throws Exception {
		modelMapper = new JukeboxModelMapper();
	}

}

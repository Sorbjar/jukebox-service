package be.lode.jukebox.service.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import be.lode.jukebox.business.OAuthApiInfo;
import be.lode.jukebox.service.dto.OAuthApiInfoDTO;

public class JukeboxModelMapperTest {
	private JukeboxModelMapper modelMapper;

	@Before
	public void setUp() throws Exception {
		modelMapper = new JukeboxModelMapper();
	}

	@Test
	public void mapOAuthApiInfoValidate() {
		//modelMapper.createTypeMap(OAuthApiInfo.class, OAuthApiInfoDTO.class);
		modelMapper.validate();
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
	public void mapOAuthApiInfoToObj() {
		// no test, since no setters are defined for object
	}

	@Test
	public void testJukeboxModelMapper() {
		// no test, constructor adds maps, these are tested in actual mappings
	}

}

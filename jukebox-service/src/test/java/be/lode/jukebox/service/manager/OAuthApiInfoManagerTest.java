package be.lode.jukebox.service.manager;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import be.lode.jukebox.service.dto.OAuthApiInfoDTO;

public class OAuthApiInfoManagerTest {
	private OAuthApiInfoManager mgr;

	@Before
	public void setUp() throws Exception {
		mgr = new OAuthApiInfoManager();
	}

	@Test
	public void testOAuthApiInfoManager() {
		// no test
	}

	@Test
	public void testGetOAuthApiInfo() {
		OAuthApiInfoDTO o = mgr.getOAuthApiInfo("Facebook");
		assertEquals("getApiKey Expecting TestName", "837671609637096",
				o.getApiKey());
		assertEquals("getName Expecting Facebook", "Facebook", o.getName());
	}

}

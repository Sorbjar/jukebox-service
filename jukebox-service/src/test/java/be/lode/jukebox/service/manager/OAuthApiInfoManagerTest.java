package be.lode.jukebox.service.manager;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import be.lode.jukebox.service.dto.OAuthApiInfoDTO;
import be.lode.setup.ClearThenSetupDBData;

public class OAuthApiInfoManagerTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ClearThenSetupDBData.run();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ClearThenSetupDBData.run();
	}

	private OAuthApiInfoManager mgr;

	@Before
	public void setUp() throws Exception {
		mgr = new OAuthApiInfoManager();
	}

	@Test
	public void testGetOAuthApiInfo() {
		OAuthApiInfoDTO o = mgr.getOAuthApiInfo("Facebook");
		assertEquals("getApiKey Expecting TestName", "837671609637096",
				o.getApiKey());
		assertEquals("getName Expecting Facebook", "Facebook", o.getName());
	}

	@Test
	public void testOAuthApiInfoManager() {
		// no test
	}

}

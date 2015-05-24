package be.lode.jukebox.service.manager;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import be.lode.jukebox.service.dto.CurrencyDTO;
import be.lode.setup.ResetDBSetupLiveData;
import be.lode.setup.ResetDBSetupTestData;


public class CurrencyManagerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ResetDBSetupTestData.run();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ResetDBSetupLiveData.run();
	}
	private CurrencyManager mgr;

	@Before
	public void setUp() throws Exception {
		ResetDBSetupTestData.run();
		mgr = new CurrencyManager();
	}
	@Test
	public void testGetCurrency() {
		for (CurrencyDTO cur : mgr.getCurrencyList()) {
			CurrencyDTO sample = mgr.getCurrency(cur.getPayPalCode());
			sample.getName().equals(cur.getName());
		}
	}
	@Test
	public void testGetCurrencyList() {
		List<CurrencyDTO> curList = mgr.getCurrencyList();
		assertNotNull("list not null", curList);
		assertTrue("list - list not empty", curList.size() > 0);
	}

}

package be.lode.jukebox.service.manager;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import be.lode.jukebox.business.repo.CustomQueryRepository;
import be.lode.setup.ResetDBSetupTestData;

public class WebServiceManagerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ResetDBSetupTestData.run();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ResetDBSetupTestData.run();
	}

	private EntityManagerFactory emf;

	@Before
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("jukebox-business");
	}

	@Test
	public void testGetAllArtists() {
		CustomQueryRepository repo = new CustomQueryRepository(emf);
		List<String> list = repo.getAllArtists();
		assertNotNull(list);
		for (String string : list) {
			assertNotNull(string);
		}
	}
	
	@Test
	public void testGetAllTitles() {
		CustomQueryRepository repo = new CustomQueryRepository(emf);
		List<String> alist = repo.getAllArtists();
		assertNotNull(alist);
		assertTrue(alist.size() > 0);
		for (String artist : alist) {
			List<String> tlist = repo.getAllTitles(artist);
			assertNotNull(tlist);
			assertTrue(tlist.size() > 0);
			for (String title : tlist) {
				assertNotNull(title);
			}
		}
	}

}

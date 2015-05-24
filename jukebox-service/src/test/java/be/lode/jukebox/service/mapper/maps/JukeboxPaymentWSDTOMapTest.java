package be.lode.jukebox.service.mapper.maps;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import be.lode.jukebox.business.model.Account;
import be.lode.jukebox.business.model.Jukebox;
import be.lode.jukebox.service.dto.JukeboxPaymentWSDTO;
import be.lode.jukebox.service.mapper.JukeboxModelMapper;
import be.lode.setup.ResetDBSetupLiveData;
import be.lode.setup.ResetDBSetupTestData;

public class JukeboxPaymentWSDTOMapTest {

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
	public void mapJukeboxToJukeboxPaymentWSDTO() {

		Account acc = new Account("email", "firstname", "lastName",
				"serviceId", "serviceName");
		acc.setId(10);
		Jukebox o = new Jukebox("new name", acc);
		o.setId(20);

		JukeboxPaymentWSDTO oDTO = modelMapper
				.map(o, JukeboxPaymentWSDTO.class);

		assertEquals(String.valueOf(o.getId()), oDTO.getId());
		assertEquals(o.getName(), oDTO.getName());
	}

	@Before
	public void setUp() throws Exception {
		modelMapper = new JukeboxModelMapper();
	}
}

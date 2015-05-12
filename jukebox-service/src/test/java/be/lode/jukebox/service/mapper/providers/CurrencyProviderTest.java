package be.lode.jukebox.service.mapper.providers;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import be.lode.jukebox.business.model.Currency;
import be.lode.jukebox.service.dto.CurrencyDTO;
import be.lode.jukebox.service.mapper.JukeboxModelMapper;
import be.lode.setup.ResetDBSetupLiveData;
import be.lode.setup.ResetDBSetupTestData;

public class CurrencyProviderTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ResetDBSetupTestData.run();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ResetDBSetupLiveData.run();
	}

	private JukeboxModelMapper modelMapper;

	@Before
	public void setUp() throws Exception {
		modelMapper = new JukeboxModelMapper();
	}

	@Test
	public void mapPaypalSettingsToDTO() {

		Currency o = new Currency("PLN", "Polish Zloty");

		CurrencyDTO oDTO = modelMapper.map(o, CurrencyDTO.class);

		assertEquals(oDTO.getName(), o.getName());
		assertEquals(oDTO.getPayPalCode(), o.getPayPalCode());
	}

	@Test
	public void mapAccountDTOToObj() {
		CurrencyDTO oDTO = new CurrencyDTO();
		oDTO.setName("currencyName");
		oDTO.setPayPalCode("payPalCurrencyCode");

		Currency o = modelMapper.map(oDTO, Currency.class);

		assertEquals(oDTO.getName(), o.getName());
		assertEquals(oDTO.getPayPalCode(), o.getPayPalCode());
	}
}

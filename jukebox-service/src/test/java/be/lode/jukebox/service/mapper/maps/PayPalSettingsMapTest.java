package be.lode.jukebox.service.mapper.maps;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import be.lode.jukebox.business.model.Currency;
import be.lode.jukebox.business.model.PayPalSettings;
import be.lode.jukebox.service.dto.PayPalSettingsDTO;
import be.lode.jukebox.service.mapper.JukeboxModelMapper;
import be.lode.setup.ResetDBSetupLiveData;
import be.lode.setup.ResetDBSetupTestData;

public class PayPalSettingsMapTest {

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

		PayPalSettings o = new PayPalSettings();
		o.setCurrency(new Currency("PLN", "Polish Zloty"));
		o.setEmail("newEm@email.com");
		o.setPricePerSong(1.24);

		PayPalSettingsDTO oDTO = modelMapper.map(o, PayPalSettingsDTO.class);

		assertEquals(Long.valueOf(oDTO.getId()).longValue(), o.getId());
		assertEquals(oDTO.getCurrencyName(), o.getCurrency().getName());
		assertEquals(oDTO.getEmail(), o.getEmail());
		assertEquals(oDTO.getPayPalCurrencyCode(), o.getCurrency()
				.getPayPalCode());
		assertEquals(oDTO.getPricePerSong(),
				String.valueOf(o.getPricePerSong()));
	}

	@Test
	public void mapAccountDTOToObj() {
		PayPalSettingsDTO oDTO = new PayPalSettingsDTO();
		oDTO.setCurrencyName("currencyName");
		oDTO.setEmail("email");
		oDTO.setPayPalCurrencyCode("payPalCurrencyCode");
		oDTO.setPricePerSong("1.2");
		oDTO.setId("10");

		PayPalSettings o = modelMapper.map(oDTO, PayPalSettings.class);

		assertEquals(Long.valueOf(oDTO.getId()).longValue(), o.getId());
		assertEquals(oDTO.getCurrencyName(), o.getCurrency().getName());
		assertEquals(oDTO.getEmail(), o.getEmail());
		assertEquals(oDTO.getPayPalCurrencyCode(), o.getCurrency()
				.getPayPalCode());
		assertEquals(oDTO.getPricePerSong(),
				String.valueOf(o.getPricePerSong()));
	}

}

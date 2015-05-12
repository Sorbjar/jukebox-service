package be.lode.jukebox.service.mapper.maps;

import org.modelmapper.PropertyMap;

import be.lode.jukebox.business.model.PayPalSettings;
import be.lode.jukebox.service.dto.PayPalSettingsDTO;

public class PayPalSettingsMap extends
		PropertyMap<PayPalSettings, PayPalSettingsDTO> {

	@Override
	protected void configure() {
		map().setCurrencyName(String.valueOf(source.getCurrency().getName()));
		map().setPayPalCurrencyCode(
				String.valueOf(source.getCurrency().getPayPalCode()));
	}
}

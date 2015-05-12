package be.lode.jukebox.service.mapper.providers;

import org.modelmapper.Provider;

import be.lode.jukebox.business.model.Currency;
import be.lode.jukebox.business.model.PayPalSettings;
import be.lode.jukebox.service.dto.PayPalSettingsDTO;

public class PayPalSettingsProvider implements Provider<PayPalSettings> {

	@Override
	public PayPalSettings get(ProvisionRequest<PayPalSettings> request) {
		PayPalSettingsDTO dto = PayPalSettingsDTO.class.cast(request
				.getSource());
		PayPalSettings o = new PayPalSettings();
		o.setCurrency(new Currency(dto.getPayPalCurrencyCode(), dto
				.getCurrencyName()));
		o.setEmail(dto.getEmail());
		o.setPricePerSong(Double.parseDouble(dto.getPricePerSong()));
		o.setId(Long.parseLong(dto.getId()));
		return o;
	}
}

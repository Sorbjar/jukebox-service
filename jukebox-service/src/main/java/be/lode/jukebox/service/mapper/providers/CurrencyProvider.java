package be.lode.jukebox.service.mapper.providers;

import org.modelmapper.Provider;

import be.lode.jukebox.business.model.Currency;
import be.lode.jukebox.service.dto.CurrencyDTO;

public class CurrencyProvider implements Provider<Currency> {

	@Override
	public Currency get(ProvisionRequest<Currency> request) {
		CurrencyDTO dto = CurrencyDTO.class.cast(request.getSource());
		return new Currency(dto.getPayPalCode(), dto.getName());
	}
}

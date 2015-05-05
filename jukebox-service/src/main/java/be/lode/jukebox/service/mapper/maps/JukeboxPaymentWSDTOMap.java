package be.lode.jukebox.service.mapper.maps;

import org.modelmapper.PropertyMap;

import be.lode.jukebox.business.model.Jukebox;
import be.lode.jukebox.service.dto.JukeboxPaymentWSDTO;

public class JukeboxPaymentWSDTOMap extends
		PropertyMap<Jukebox, JukeboxPaymentWSDTO> {

	// TODO 010 testing
	@Override
	protected void configure() {
		map().setId(String.valueOf(source.getId()));
		map().setCurrencyCode(
				String.valueOf(source.getPayPalSettings().getCurrency()
						.getPayPalCode()));
		map().setEmail(String.valueOf(source.getPayPalSettings().getEmail()));
		map().setName(String.valueOf(source.getName()));
		map().setPricePerSong(
				String.valueOf(source.getPayPalSettings().getPricePerSong()));
	}
}

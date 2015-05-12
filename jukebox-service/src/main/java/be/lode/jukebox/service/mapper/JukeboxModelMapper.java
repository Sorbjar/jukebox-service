package be.lode.jukebox.service.mapper;

import org.modelmapper.ModelMapper;

import be.lode.jukebox.business.model.Currency;
import be.lode.jukebox.business.model.PayPalSettings;
import be.lode.jukebox.business.model.Song;
import be.lode.jukebox.service.dto.CurrencyDTO;
import be.lode.jukebox.service.dto.PayPalSettingsDTO;
import be.lode.jukebox.service.dto.SongDTO;
import be.lode.jukebox.service.mapper.maps.AccountMap;
import be.lode.jukebox.service.mapper.maps.JukeboxPaymentWSDTOMap;
import be.lode.jukebox.service.mapper.maps.OAuthApiInfoMap;
import be.lode.jukebox.service.mapper.maps.OAuthUserMap;
import be.lode.jukebox.service.mapper.maps.PayPalSettingsMap;
import be.lode.jukebox.service.mapper.providers.CurrencyProvider;
import be.lode.jukebox.service.mapper.providers.PayPalSettingsProvider;
import be.lode.jukebox.service.mapper.providers.SongProvider;

public class JukeboxModelMapper extends ModelMapper {

	public JukeboxModelMapper() {
		super();
		createTypeMaps();
		addMappings();
	}

	private void addMappings() {
		this.addMappings(new OAuthApiInfoMap());
		this.addMappings(new OAuthUserMap());
		this.addMappings(new AccountMap());
		this.addMappings(new JukeboxPaymentWSDTOMap());
		this.addMappings(new PayPalSettingsMap());
	}

	private void createTypeMaps() {
		this.createTypeMap(SongDTO.class, Song.class).setProvider(
				new SongProvider());
		this.createTypeMap(PayPalSettingsDTO.class, PayPalSettings.class)
				.setProvider(new PayPalSettingsProvider());
		this.createTypeMap(CurrencyDTO.class, Currency.class).setProvider(
				new CurrencyProvider());
	}

}
package be.lode.jukebox.service.mapper.maps;

import org.modelmapper.PropertyMap;

import be.lode.jukebox.business.model.Account;
import be.lode.jukebox.service.dto.AccountDTO;

public class AccountMap extends PropertyMap<Account, AccountDTO> {

	@Override
	protected void configure() {
		map().setId(String.valueOf(source.getId()));
	}

}

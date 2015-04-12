package be.lode.jukebox.service.mapper.maps;

import org.modelmapper.PropertyMap;

import be.lode.jukebox.service.dto.AccountDTO;
import be.lode.oauth.OAuthButton.IOAuthUser;

public class OAuthUserMap extends PropertyMap<IOAuthUser, AccountDTO> {

	@Override
	protected void configure() {
		map().setEmailAddress(source.getEmail());
		map().setFirstName(source.getName());
		map().setLastName(source.getName());
		map().setServiceId(source.getId());
		map().setServiceName(source.getService());
	}
}

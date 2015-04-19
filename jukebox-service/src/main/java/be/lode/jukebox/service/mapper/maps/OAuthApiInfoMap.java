package be.lode.jukebox.service.mapper.maps;

import org.modelmapper.PropertyMap;

import be.lode.jukebox.business.model.OAuthApiInfo;
import be.lode.jukebox.service.dto.OAuthApiInfoDTO;

public class OAuthApiInfoMap extends PropertyMap<OAuthApiInfo, OAuthApiInfoDTO> {

	@Override
	protected void configure() {
		map().setScribeApiName(source.getScribeApiName());

	}

}

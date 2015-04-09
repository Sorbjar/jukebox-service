package be.lode.jukebox.service.mapper;

import org.modelmapper.PropertyMap;

import be.lode.jukebox.business.OAuthApiInfo;
import be.lode.jukebox.service.dto.OAuthApiInfoDTO;

public class OAuthApiInfoMap extends PropertyMap<OAuthApiInfo, OAuthApiInfoDTO> {

	@Override
	protected void configure() {
		map().setScribeApiName(source.getScribeApiName());
		
	}

}

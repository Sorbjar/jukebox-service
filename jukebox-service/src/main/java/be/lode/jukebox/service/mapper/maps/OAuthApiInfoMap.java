package be.lode.jukebox.service.mapper.maps;

import org.modelmapper.PropertyMap;

import be.lode.jukebox.business.model.OAuthApiInfo;
import be.lode.jukebox.service.dto.OAuthApiInfoDTO;

/**
 * The Class OAuthApiInfoMap.
 */
public class OAuthApiInfoMap extends PropertyMap<OAuthApiInfo, OAuthApiInfoDTO> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.modelmapper.PropertyMap#configure()
	 */
	@Override
	protected void configure() {
		map().setScribeApiName(source.getScribeApiName());

	}

}

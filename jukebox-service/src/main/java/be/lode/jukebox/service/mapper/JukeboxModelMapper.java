package be.lode.jukebox.service.mapper;

import org.modelmapper.ModelMapper;

import be.lode.jukebox.service.mapper.maps.AccountMap;
import be.lode.jukebox.service.mapper.maps.OAuthApiInfoMap;
import be.lode.jukebox.service.mapper.maps.OAuthUserMap;

public class JukeboxModelMapper extends ModelMapper {

	public JukeboxModelMapper() {
		super();
		this.addMappings(new OAuthApiInfoMap());
		this.addMappings(new OAuthUserMap());
		this.addMappings(new AccountMap());
	}

}
package be.lode.jukebox.service.mapper;

import org.modelmapper.ModelMapper;

public class JukeboxModelMapper extends ModelMapper {

	public JukeboxModelMapper() {
		super();
		this.addMappings(new OAuthApiInfoMap());
	}

}

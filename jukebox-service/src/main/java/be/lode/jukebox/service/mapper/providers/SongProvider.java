package be.lode.jukebox.service.mapper.providers;

import org.modelmapper.Provider;

import be.lode.jukebox.business.model.Song;
import be.lode.jukebox.service.dto.SongDTO;

/**
 * The Class SongProvider.
 */
public class SongProvider implements Provider<Song> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.modelmapper.Provider#get(org.modelmapper.Provider.ProvisionRequest)
	 */
	@Override
	public Song get(ProvisionRequest<Song> request) {
		SongDTO s = SongDTO.class.cast(request.getSource());
		return new Song(s.getArtist(), s.getTitle(), s.getPath());
	}
}

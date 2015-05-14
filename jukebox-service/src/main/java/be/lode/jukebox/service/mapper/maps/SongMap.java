package be.lode.jukebox.service.mapper.maps;

import org.modelmapper.PropertyMap;

import be.lode.jukebox.business.model.Song;
import be.lode.jukebox.service.dto.SongDTO;

public class SongMap extends PropertyMap<Song, SongDTO> {

	@Override
	protected void configure() {
		// From song
		map().setArtist(source.getArtist());
		map().setId(String.valueOf(source.getId()));
		map().setPath(source.getPath());
		map().setTitle(source.getTitle());
		
		// From playlist
		map().setMandatory("");
		map().setPlayListOrder("");

		// From metadata
		map().setAlbum(getMetaData("xmpDM:album"));
		map().setAlbumArtist(getMetaData("xmpDM:albumArtist"));
		map().setAudioChannelType(getMetaData("xmpDM:audioChannelType"));
		map().setAudioCompressor(getMetaData("xmpDM:audioCompressor"));
		map().setAuthor(getMetaData("Author"));
		map().setChannels(getMetaData("channels"));
		map().setComposer(getMetaData("xmpDM:composer"));
		map().setContentType(getMetaData("Content-Type"));
		map().setCreator(getMetaData("creator"));
		map().setDiscNumber(getMetaData("xmpDM:discNumber"));
		map().setDuration(getMetaData("xmpDM:duration"));
		map().setGenre(getMetaData("xmpDM:genre"));
		map().setReleaseDate(getMetaData("xmpDM:releaseDate"));
		map().setSamplerate(getMetaData("samplerate"));
		map().setTrackNumber(getMetaData("xmpDM:trackNumber"));
		map().setVersion(getMetaData("version"));
	}

	private String getMetaData(String string) {
		if (source.getMetadataProperties() != null && source.getMetadataProperties().containsKey(string))
			return source.getMetadataProperties().get(string);
		return "";
	}
}

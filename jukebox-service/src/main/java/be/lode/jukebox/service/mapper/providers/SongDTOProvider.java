package be.lode.jukebox.service.mapper.providers;

import org.modelmapper.Provider;

import be.lode.jukebox.business.model.Song;
import be.lode.jukebox.service.dto.SongDTO;

public class SongDTOProvider implements Provider<SongDTO> {

	private Song source;

	@Override
	public SongDTO get(ProvisionRequest<SongDTO> request) {
		source = Song.class.cast(request.getSource());
		SongDTO ret = new SongDTO();

		// From song
		ret.setArtist(source.getArtist());
		ret.setId(String.valueOf(source.getId()));
		ret.setPath(source.getPath());
		ret.setTitle(source.getTitle());

		// From playlist
		ret.setMandatory("");
		ret.setPlayListOrder("");

		// From metadata
		ret.setAlbum(getMetaData("xmpDM:album"));
		ret.setAlbumArtist(getMetaData("xmpDM:albumArtist"));
		ret.setAudioChannelType(getMetaData("xmpDM:audioChannelType"));
		ret.setAudioCompressor(getMetaData("xmpDM:audioCompressor"));
		ret.setAuthor(getMetaData("Author"));
		ret.setChannels(getMetaData("channels"));
		ret.setComposer(getMetaData("xmpDM:composer"));
		ret.setContentType(getMetaData("Content-Type"));
		ret.setCreator(getMetaData("creator"));
		ret.setDiscNumber(getMetaData("xmpDM:discNumber"));
		//TODO 100 change format of duration
		ret.setDuration(getMetaData("xmpDM:duration"));
		ret.setGenre(getMetaData("xmpDM:genre"));
		ret.setReleaseDate(getMetaData("xmpDM:releaseDate"));
		ret.setSamplerate(getMetaData("samplerate"));
		ret.setTrackNumber(getMetaData("xmpDM:trackNumber"));
		ret.setVersion(getMetaData("version"));

		return ret;
	}

	private String getMetaData(String string) {
		try {
			return source.getMetadataProperties().get(string);
		} catch (NullPointerException ex) {
			return "";
		}
	}
}

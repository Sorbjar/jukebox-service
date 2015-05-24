package be.lode.jukebox.service.mapper.providers;

import org.modelmapper.Provider;

import be.lode.jukebox.business.model.Song;
import be.lode.jukebox.service.dto.SongDTO;

/**
 * The Class SongDTOProvider.
 */
public class SongDTOProvider implements Provider<SongDTO> {

	/** The source. */
	private Song source;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.modelmapper.Provider#get(org.modelmapper.Provider.ProvisionRequest)
	 */
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
		ret.setDuration(duration(getMetaData("xmpDM:duration")));
		ret.setGenre(getMetaData("xmpDM:genre"));
		ret.setReleaseDate(substring(getMetaData("xmpDM:releaseDate"), 0, 10));
		ret.setSamplerate(getMetaData("samplerate"));
		ret.setTrackNumber(getMetaData("xmpDM:trackNumber"));
		ret.setVersion(getMetaData("version"));

		return ret;
	}

	/**
	 * Duration.
	 *
	 * @param str
	 *            the str
	 * @return the string
	 */
	private String duration(String str) {
		try {
			String beforeFirstDot = str.split("\\.")[0];
			int dur = Integer.parseInt(beforeFirstDot);
			int secs = dur / 1000;
			int hr = secs / 3600;
			int rem = secs % 3600;
			int mn = rem / 60;
			int sec = rem % 60;
			String hrStr = (hr < 10 ? "0" : "") + hr;
			String mnStr = (mn < 10 ? "0" : "") + mn;
			String secStr = (sec < 10 ? "0" : "") + sec;
			if (hr > 0)
				return hrStr + ":" + mnStr + ":" + secStr;
			return mnStr + ":" + secStr;
		} catch (NullPointerException ex) {
			return "";
		}
	}

	/**
	 * Gets the meta data.
	 *
	 * @param string
	 *            the string
	 * @return the meta data
	 */
	private String getMetaData(String string) {
		try {
			return source.getMetadataProperties().get(string);
		} catch (NullPointerException ex) {
			return "";
		}
	}

	/**
	 * Substring.
	 *
	 * @param str
	 *            the str
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @return the string
	 */
	private String substring(String str, int start, int end) {
		try {
			if (str.length() > 10)
				return str.substring(start, end);
			else
				return str;
		} catch (NullPointerException ex) {
			return "";
		}
	}
}

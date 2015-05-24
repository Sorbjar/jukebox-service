package be.lode.jukebox.service.dto;

/**
 * The Class SongDTO.
 */
public class SongDTO {

	/** The album. */
	private String album;

	/** The album artist. */
	private String albumArtist;

	/** The artist. */
	private String artist;

	/** The audio channel type. */
	private String audioChannelType;

	/** The audio compressor. */
	private String audioCompressor;

	/** The author. */
	private String author;

	/** The channels. */
	private String channels;

	/** The composer. */
	private String composer;

	/** The content type. */
	private String contentType;

	/** The creator. */
	private String creator;

	/** The disc number. */
	private String discNumber;

	/** The duration. */
	private String duration;

	/** The genre. */
	private String genre;

	/** The id. */
	private String id;

	/** The mandatory. */
	private String mandatory;

	/** The path. */
	private String path;

	/** The play list order. */
	private String playListOrder;

	/** The release date. */
	private String releaseDate;

	/** The samplerate. */
	private String samplerate;

	/** The title. */
	private String title;

	/** The track number. */
	private String trackNumber;

	/** The version. */
	private String version;

	/**
	 * Instantiates a new song dto.
	 */
	public SongDTO() {
		super();
		this.album = "";
		this.albumArtist = "";
		this.artist = "";
		this.audioChannelType = "";
		this.audioCompressor = "";
		this.author = "";
		this.channels = "";
		this.composer = "";
		this.contentType = "";
		this.creator = "";
		this.discNumber = "";
		this.duration = "";
		this.genre = "";
		this.id = "";
		this.mandatory = "";
		this.path = "";
		this.playListOrder = "";
		this.releaseDate = "";
		this.samplerate = "";
		this.title = "";
		this.trackNumber = "";
		this.version = "";
		this.mandatory = String.valueOf(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SongDTO other = (SongDTO) obj;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (playListOrder == null) {
			if (other.playListOrder != null)
				return false;
		} else if (!playListOrder.equals(other.playListOrder))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Gets the album.
	 *
	 * @return the album
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * Gets the album artist.
	 *
	 * @return the album artist
	 */
	public String getAlbumArtist() {
		return albumArtist;
	}

	/**
	 * Gets the artist.
	 *
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * Gets the audio channel type.
	 *
	 * @return the audio channel type
	 */
	public String getAudioChannelType() {
		return audioChannelType;
	}

	/**
	 * Gets the audio compressor.
	 *
	 * @return the audio compressor
	 */
	public String getAudioCompressor() {
		return audioCompressor;
	}

	/**
	 * Gets the author.
	 *
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Gets the channels.
	 *
	 * @return the channels
	 */
	public String getChannels() {
		return channels;
	}

	/**
	 * Gets the composer.
	 *
	 * @return the composer
	 */
	public String getComposer() {
		return composer;
	}

	/**
	 * Gets the content type.
	 *
	 * @return the content type
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * Gets the creator.
	 *
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * Gets the disc number.
	 *
	 * @return the disc number
	 */
	public String getDiscNumber() {
		return discNumber;
	}

	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * Gets the genre.
	 *
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the mandatory.
	 *
	 * @return the mandatory
	 */
	public String getMandatory() {
		return mandatory;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Gets the play list order.
	 *
	 * @return the play list order
	 */
	public String getPlayListOrder() {
		return playListOrder;
	}

	/**
	 * Gets the release date.
	 *
	 * @return the release date
	 */
	public String getReleaseDate() {
		return releaseDate;
	}

	/**
	 * Gets the samplerate.
	 *
	 * @return the samplerate
	 */
	public String getSamplerate() {
		return samplerate;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the track number.
	 *
	 * @return the track number
	 */
	public String getTrackNumber() {
		return trackNumber;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result
				+ ((playListOrder == null) ? 0 : playListOrder.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Sets the album.
	 *
	 * @param album
	 *            the new album
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * Sets the album artist.
	 *
	 * @param albumArtist
	 *            the new album artist
	 */
	public void setAlbumArtist(String albumArtist) {
		this.albumArtist = albumArtist;
	}

	/**
	 * Sets the artist.
	 *
	 * @param artist
	 *            the new artist
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * Sets the audio channel type.
	 *
	 * @param audioChannelType
	 *            the new audio channel type
	 */
	public void setAudioChannelType(String audioChannelType) {
		this.audioChannelType = audioChannelType;
	}

	/**
	 * Sets the audio compressor.
	 *
	 * @param audioCompressor
	 *            the new audio compressor
	 */
	public void setAudioCompressor(String audioCompressor) {
		this.audioCompressor = audioCompressor;
	}

	/**
	 * Sets the author.
	 *
	 * @param author
	 *            the new author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Sets the channels.
	 *
	 * @param channels
	 *            the new channels
	 */
	public void setChannels(String channels) {
		this.channels = channels;
	}

	/**
	 * Sets the composer.
	 *
	 * @param composer
	 *            the new composer
	 */
	public void setComposer(String composer) {
		this.composer = composer;
	}

	/**
	 * Sets the content type.
	 *
	 * @param contentType
	 *            the new content type
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * Sets the creator.
	 *
	 * @param creator
	 *            the new creator
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * Sets the disc number.
	 *
	 * @param discNumber
	 *            the new disc number
	 */
	public void setDiscNumber(String discNumber) {
		this.discNumber = discNumber;
	}

	/**
	 * Sets the duration.
	 *
	 * @param duration
	 *            the new duration
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * Sets the genre.
	 *
	 * @param genre
	 *            the new genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Sets the mandatory.
	 *
	 * @param mandatory
	 *            the new mandatory
	 */
	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}

	/**
	 * Sets the path.
	 *
	 * @param path
	 *            the new path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Sets the play list order.
	 *
	 * @param playListOrder
	 *            the new play list order
	 */
	public void setPlayListOrder(String playListOrder) {
		this.playListOrder = playListOrder;
	}

	/**
	 * Sets the release date.
	 *
	 * @param releaseDate
	 *            the new release date
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * Sets the samplerate.
	 *
	 * @param samplerate
	 *            the new samplerate
	 */
	public void setSamplerate(String samplerate) {
		this.samplerate = samplerate;
	}

	/**
	 * Sets the title.
	 *
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Sets the track number.
	 *
	 * @param trackNumber
	 *            the new track number
	 */
	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}

	/**
	 * Sets the version.
	 *
	 * @param version
	 *            the new version
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return artist + " - " + title;
	}

}

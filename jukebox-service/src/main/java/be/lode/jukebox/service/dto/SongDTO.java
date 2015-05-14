package be.lode.jukebox.service.dto;

public class SongDTO {
	private String album;
	private String albumArtist;
	private String artist;
	private String audioChannelType;
	private String audioCompressor;
	private String author;
	private String channels;
	private String composer;
	private String contentType;
	private String creator;
	private String discNumber;
	private String duration;
	private String genre;
	private String id;
	private String mandatory;
	private String path;
	private String playListOrder;
	private String releaseDate;
	private String samplerate;
	private String title;
	private String trackNumber;
	private String version;

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

	public String getAlbum() {
		return album;
	}

	public String getAlbumArtist() {
		return albumArtist;
	}

	public String getArtist() {
		return artist;
	}

	public String getAudioChannelType() {
		return audioChannelType;
	}

	public String getAudioCompressor() {
		return audioCompressor;
	}

	public String getAuthor() {
		return author;
	}

	public String getChannels() {
		return channels;
	}

	public String getComposer() {
		return composer;
	}

	public String getContentType() {
		return contentType;
	}

	public String getCreator() {
		return creator;
	}

	public String getDiscNumber() {
		return discNumber;
	}

	public String getDuration() {
		return duration;
	}

	public String getGenre() {
		return genre;
	}

	public String getId() {
		return id;
	}

	public String getMandatory() {
		return mandatory;
	}

	public String getPath() {
		return path;
	}

	public String getPlayListOrder() {
		return playListOrder;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public String getSamplerate() {
		return samplerate;
	}

	public String getTitle() {
		return title;
	}

	public String getTrackNumber() {
		return trackNumber;
	}

	public String getVersion() {
		return version;
	}

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

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setAlbumArtist(String albumArtist) {
		this.albumArtist = albumArtist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setAudioChannelType(String audioChannelType) {
		this.audioChannelType = audioChannelType;
	}

	public void setAudioCompressor(String audioCompressor) {
		this.audioCompressor = audioCompressor;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setChannels(String channels) {
		this.channels = channels;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setDiscNumber(String discNumber) {
		this.discNumber = discNumber;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setPlayListOrder(String playListOrder) {
		this.playListOrder = playListOrder;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setSamplerate(String samplerate) {
		this.samplerate = samplerate;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return artist + " - " + title;
	}

}

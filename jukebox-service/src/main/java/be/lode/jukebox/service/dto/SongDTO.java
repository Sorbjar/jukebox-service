package be.lode.jukebox.service.dto;

public class SongDTO {
	// TODO 400 other fields
	private String artist;
	private String id;
	private String path;
	private String title;

	public SongDTO() {
		super();
		this.artist = "";
		this.title = "";
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
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public String getArtist() {
		return artist;
	}

	public String getId() {
		return id;
	}

	public String getPath() {
		return path;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return artist + " - " + title;
	}

}

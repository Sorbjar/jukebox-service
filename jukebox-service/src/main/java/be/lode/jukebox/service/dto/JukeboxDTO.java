package be.lode.jukebox.service.dto;

public class JukeboxDTO {
	private String id;
	private String looped;
	private String name;
	private String random;
	
	public JukeboxDTO() {
		super();
		this.id = "";
		this.name = "";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JukeboxDTO other = (JukeboxDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

	public String getLooped() {
		return looped;
	}

	public String getName() {
		return name;
	}

	public String getRandom() {
		return random;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLooped(String looped) {
		this.looped = looped;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	@Override
	public String toString() {
		return name;
	}
}

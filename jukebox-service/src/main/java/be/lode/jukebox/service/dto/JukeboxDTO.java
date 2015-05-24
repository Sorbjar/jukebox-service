package be.lode.jukebox.service.dto;

/**
 * The Class JukeboxDTO.
 */
public class JukeboxDTO {

	/** The id. */
	private String id;

	/** The looped. */
	private String looped;

	/** The name. */
	private String name;

	/** The random. */
	private String random;

	/**
	 * Instantiates a new jukebox dto.
	 */
	public JukeboxDTO() {
		super();
		this.id = "";
		this.name = "";
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
		JukeboxDTO other = (JukeboxDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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
	 * Gets the looped.
	 *
	 * @return the looped
	 */
	public String getLooped() {
		return looped;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the random.
	 *
	 * @return the random
	 */
	public String getRandom() {
		return random;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
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
	 * Sets the looped.
	 *
	 * @param looped
	 *            the new looped
	 */
	public void setLooped(String looped) {
		this.looped = looped;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the random.
	 *
	 * @param random
	 *            the new random
	 */
	public void setRandom(String random) {
		this.random = random;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}
}

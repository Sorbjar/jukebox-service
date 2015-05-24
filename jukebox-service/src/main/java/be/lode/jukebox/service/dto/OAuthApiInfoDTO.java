package be.lode.jukebox.service.dto;

/**
 * The Class OAuthApiInfoDTO.
 */
public class OAuthApiInfoDTO {

	/** The api key. */
	private String apiKey;

	/** The api secret. */
	private String apiSecret;

	/** The example get request. */
	private String exampleGetRequest;

	/** The name. */
	private String name;

	/** The scribe api name. */
	private String scribeApiName;

	/**
	 * Instantiates a new o auth api info dto.
	 */
	public OAuthApiInfoDTO() {
		super();
		this.apiKey = "";
		this.apiSecret = "";
		this.exampleGetRequest = "";
		this.name = "";
		this.scribeApiName = "";
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
		OAuthApiInfoDTO other = (OAuthApiInfoDTO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * Gets the api key.
	 *
	 * @return the api key
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * Gets the api secret.
	 *
	 * @return the api secret
	 */
	public String getApiSecret() {
		return apiSecret;
	}

	/**
	 * Gets the example get request.
	 *
	 * @return the example get request
	 */
	public String getExampleGetRequest() {
		return exampleGetRequest;
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
	 * Gets the scribe api name.
	 *
	 * @return the scribe api name
	 */
	public String getScribeApiName() {
		return scribeApiName;
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Sets the api key.
	 *
	 * @param apiKey
	 *            the new api key
	 */
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	/**
	 * Sets the api secret.
	 *
	 * @param apiSecret
	 *            the new api secret
	 */
	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	/**
	 * Sets the example get request.
	 *
	 * @param exampleGetRequest
	 *            the new example get request
	 */
	public void setExampleGetRequest(String exampleGetRequest) {
		this.exampleGetRequest = exampleGetRequest;
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
	 * Sets the scribe api name.
	 *
	 * @param scribeApiName
	 *            the new scribe api name
	 */
	public void setScribeApiName(String scribeApiName) {
		this.scribeApiName = scribeApiName;
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

package be.lode.jukebox.service.dto;

public class OAuthApiInfoDTO {
	private String apiKey;
	private String apiSecret;
	private String exampleGetRequest;
	private String name;
	private String scribeApiName;

	public OAuthApiInfoDTO() {
		super();
		this.apiKey = "";
		this.apiSecret = "";
		this.exampleGetRequest = "";
		this.name = "";
		this.scribeApiName = "";
	}

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

	public String getApiKey() {
		return apiKey;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public String getExampleGetRequest() {
		return exampleGetRequest;
	}

	public String getName() {
		return name;
	}

	public String getScribeApiName() {
		return scribeApiName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	public void setExampleGetRequest(String exampleGetRequest) {
		this.exampleGetRequest = exampleGetRequest;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScribeApiName(String scribeApiName) {
		this.scribeApiName = scribeApiName;
	}

	@Override
	public String toString() {
		return name;
	}
}

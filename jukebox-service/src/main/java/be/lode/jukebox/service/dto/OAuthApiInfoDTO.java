package be.lode.jukebox.service.dto;

public class OAuthApiInfoDTO {
	private String apiKey;
	private String apiSecret;
	private String exampleGetRequest;
	private String name;
	private String scribeApiName;

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
}

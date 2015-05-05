package be.lode.jukebox.service.dto;

public class JukeboxPaymentWSDTO {

	private String currencyCode;
	private String email;
	private String id;
	private String name;
	private String pricePerSong;

	public String getCurrencyCode() {
		return currencyCode;
	}

	public String getEmail() {
		return email;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPricePerSong() {
		return pricePerSong;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPricePerSong(String pricePerSong) {
		this.pricePerSong = pricePerSong;
	}

}

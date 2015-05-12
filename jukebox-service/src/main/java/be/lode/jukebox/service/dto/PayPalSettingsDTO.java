package be.lode.jukebox.service.dto;

public class PayPalSettingsDTO {
	private String currencyName;
	private String email;
	private String id;
	private String payPalCurrencyCode;
	private String pricePerSong;

	public PayPalSettingsDTO() {
		super();
		this.currencyName = "";
		this.payPalCurrencyCode = "";
		this.email = "";
		this.id = "";
		this.pricePerSong = "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PayPalSettingsDTO other = (PayPalSettingsDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public String getEmail() {
		return email;
	}

	public String getId() {
		return id;
	}

	public String getPayPalCurrencyCode() {
		return payPalCurrencyCode;
	}

	public String getPricePerSong() {
		return pricePerSong;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPayPalCurrencyCode(String payPalCurrencyCode) {
		this.payPalCurrencyCode = payPalCurrencyCode;
	}

	public void setPricePerSong(String pricePerSong) {
		this.pricePerSong = pricePerSong;
	}
}
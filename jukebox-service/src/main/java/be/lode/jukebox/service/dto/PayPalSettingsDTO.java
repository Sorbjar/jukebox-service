package be.lode.jukebox.service.dto;

/**
 * The Class PayPalSettingsDTO.
 */
public class PayPalSettingsDTO {

	/** The currency name. */
	private String currencyName;

	/** The email. */
	private String email;

	/** The id. */
	private String id;

	/** The PayPal currency code. */
	private String payPalCurrencyCode;

	/** The price per song. */
	private String pricePerSong;

	/**
	 * Instantiates a new PayPal settings dto.
	 */
	public PayPalSettingsDTO() {
		super();
		this.currencyName = "";
		this.payPalCurrencyCode = "";
		this.email = "";
		this.id = "";
		this.pricePerSong = "";
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
		PayPalSettingsDTO other = (PayPalSettingsDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * Gets the currency name.
	 *
	 * @return the currency name
	 */
	public String getCurrencyName() {
		return currencyName;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
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
	 * Gets the PayPal currency code.
	 *
	 * @return the PayPal currency code
	 */
	public String getPayPalCurrencyCode() {
		return payPalCurrencyCode;
	}

	/**
	 * Gets the price per song.
	 *
	 * @return the price per song
	 */
	public String getPricePerSong() {
		return pricePerSong;
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
	 * Sets the currency name.
	 *
	 * @param currencyName
	 *            the new currency name
	 */
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * Sets the PayPal currency code.
	 *
	 * @param payPalCurrencyCode
	 *            the new PayPal currency code
	 */
	public void setPayPalCurrencyCode(String payPalCurrencyCode) {
		this.payPalCurrencyCode = payPalCurrencyCode;
	}

	/**
	 * Sets the price per song.
	 *
	 * @param pricePerSong
	 *            the new price per song
	 */
	public void setPricePerSong(String pricePerSong) {
		this.pricePerSong = pricePerSong;
	}
}
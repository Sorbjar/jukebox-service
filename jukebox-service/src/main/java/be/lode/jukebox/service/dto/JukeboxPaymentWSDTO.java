package be.lode.jukebox.service.dto;

/**
 * The Class JukeboxPaymentWSDTO.
 */
public class JukeboxPaymentWSDTO {

	/** The currency code. */
	private String currencyCode;

	/** The email. */
	private String email;

	/** The id. */
	private String id;

	/** The name. */
	private String name;

	/** The price per song. */
	private String pricePerSong;

	/**
	 * Gets the currency code.
	 *
	 * @return the currency code
	 */
	public String getCurrencyCode() {
		return currencyCode;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the price per song.
	 *
	 * @return the price per song
	 */
	public String getPricePerSong() {
		return pricePerSong;
	}

	/**
	 * Sets the currency code.
	 *
	 * @param currencyCode
	 *            the new currency code
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
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
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
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

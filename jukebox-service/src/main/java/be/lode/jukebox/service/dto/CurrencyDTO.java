package be.lode.jukebox.service.dto;

/**
 * The Class CurrencyDTO.
 */
public class CurrencyDTO {

	/** The name. */
	private String name;

	/** The pay pal code. */
	private String payPalCode;

	/**
	 * Instantiates a new currency dto.
	 */
	public CurrencyDTO() {
		super();
		this.name = "";
		this.payPalCode = "";
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
		CurrencyDTO other = (CurrencyDTO) obj;
		if (payPalCode == null) {
			if (other.payPalCode != null)
				return false;
		} else if (!payPalCode.equals(other.payPalCode))
			return false;
		return true;
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
	 * Gets the pay pal code.
	 *
	 * @return the pay pal code
	 */
	public String getPayPalCode() {
		return payPalCode;
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
		result = prime * result
				+ ((payPalCode == null) ? 0 : payPalCode.hashCode());
		return result;
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
	 * Sets the pay pal code.
	 *
	 * @param payPalCode
	 *            the new pay pal code
	 */
	public void setPayPalCode(String payPalCode) {
		this.payPalCode = payPalCode;
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
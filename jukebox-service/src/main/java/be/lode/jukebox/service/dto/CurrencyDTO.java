package be.lode.jukebox.service.dto;

//TODO 010 testing
public class CurrencyDTO {
	private String name;
	private String payPalCode;

	public CurrencyDTO() {
		super();
		this.name = "";
		this.payPalCode = "";
	}

	@Override
	public String toString() {
		return name;
	}

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

	public String getName() {
		return name;
	}

	public String getPayPalCode() {
		return payPalCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((payPalCode == null) ? 0 : payPalCode.hashCode());
		return result;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPayPalCode(String payPalCode) {
		this.payPalCode = payPalCode;
	}

}
package DTO;
import java.math.BigDecimal;

public class BankAccountResponseDTO {

    private String email;
	private BigDecimal eur;
	private BigDecimal usd;
	private BigDecimal rsd;
	private BigDecimal gbp;
	private BigDecimal chf;
	private BigDecimal rub;
	private String message;

	public BankAccountResponseDTO() {
		
	}

	public BankAccountResponseDTO(String email, BigDecimal eur, BigDecimal usd, BigDecimal rsd, BigDecimal gbp,
			BigDecimal chf,	 BigDecimal rub, String message) {
		this.email = email;
		this.eur = eur;
		this.usd = usd;
		this.rsd = rsd;
		this.gbp = gbp;
		this.chf = chf;
		this.setRub(rub);
		this.message = message;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getEur() {
		return eur;
	}

	public void setEur(BigDecimal eur) {
		this.eur = eur;
	}

	public BigDecimal getUsd() {
		return usd;
	}

	public void setUsd(BigDecimal usd) {
		this.usd = usd;
	}

	public BigDecimal getRsd() {
		return rsd;
	}

	public void setRsd(BigDecimal rsd) {
		this.rsd = rsd;
	}

	public BigDecimal getGbp() {
		return gbp;
	}

	public void setGbp(BigDecimal gbp) {
		this.gbp = gbp;
	}

	public BigDecimal getChf() {
		return chf;
	}

	public void setChf(BigDecimal chf) {
		this.chf = chf;
	}

	public BigDecimal getRub() {
		return rub;
	}

	public void setRub(BigDecimal rub) {
		this.rub = rub;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
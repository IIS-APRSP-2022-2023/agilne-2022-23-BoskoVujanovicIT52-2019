package DTO;


import java.math.BigDecimal;

public class CryptoWalletResponseDto {

    private String email;
	private BigDecimal btc;
	private BigDecimal eth;
	private BigDecimal xrp;
	private String message;

	public CryptoWalletResponseDto() {
		
	}

	public CryptoWalletResponseDto(String email, BigDecimal btc, BigDecimal eth, BigDecimal xrp, 
			String message) {
		this.email = email;
		this.btc = btc;
		this.eth = eth;
		this.setXrp(xrp);
		this.message = message;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getBtc() {
		return btc;
	}

	public void setBtc(BigDecimal btc) {
		this.btc = btc;
	}

	public BigDecimal getEth() {
		return eth;
	}

	public void setEth(BigDecimal eth) {
		this.eth = eth;
	}

	public BigDecimal getXrp() {
		return xrp;
	}

	public void setXrp(BigDecimal xrp) {
		this.xrp = xrp;
	}

	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
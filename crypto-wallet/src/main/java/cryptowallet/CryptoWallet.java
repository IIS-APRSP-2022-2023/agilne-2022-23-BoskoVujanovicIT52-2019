package cryptowallet;


import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CryptoWallet {

    

	@Id
	private long id;
    @Column
	private BigDecimal xrp;
    @Column
	private BigDecimal eth;
    @Column
   	private BigDecimal btc;
    @Column
	private String email;

    public CryptoWallet() {
    }

    public CryptoWallet(long id, BigDecimal xrp, BigDecimal eth, BigDecimal btc, String email) {
        this.id = id;
        this.xrp= xrp;
        this.eth=eth;
        this.btc=btc;
        this.email = email;
    }
    
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getXrp() {
		return xrp;
	}

	public void setXrp(BigDecimal xrp) {
		this.xrp = xrp;
	}

	public BigDecimal getEth() {
		return eth;
	}

	public void setEth(BigDecimal eth) {
		this.eth = eth;
	}

	public BigDecimal getBtc() {
		return btc;
	}

	public void setBtc(BigDecimal btc) {
		this.btc = btc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
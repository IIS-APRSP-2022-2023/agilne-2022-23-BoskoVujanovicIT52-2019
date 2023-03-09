package currencyExchange;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeContoller {

	
	@Autowired	
	private Environment environment;//u pozadini se napravi klasa koja instancira interfejs, i ta klasa se instancira u objekat!
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange getExchange(@PathVariable String from, @PathVariable String to) {
		String port = environment.getProperty("local.server.port");
		
		return new CurrencyExchange(1000, from, to , BigDecimal.valueOf(118), port);
	
	
	}
}

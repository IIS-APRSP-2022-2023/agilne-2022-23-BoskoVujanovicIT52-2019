package cryptoconversion;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import DTO.CryptoWalletDto;
import DTO.CryptoWalletResponseDto;
@FeignClient(name = "crypto-wallet")
public interface CryptoWalletProxy {
    
    @PostMapping("/crypto-wallet/conversion")
    public ResponseEntity<CryptoWalletResponseDto> walletConversion(CryptoWalletDto walletDto);
}

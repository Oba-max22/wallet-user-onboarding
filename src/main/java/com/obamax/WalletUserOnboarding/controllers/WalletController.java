package com.obamax.WalletUserOnboarding.controllers;

import com.obamax.WalletUserOnboarding.payload.responses.WalletResponse;
import com.obamax.WalletUserOnboarding.services.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletResponse> fetchWalletBalance(@PathVariable Long id) {
        WalletResponse walletResponse = walletService.fetchWalletBalance(id);
        return new ResponseEntity<>(walletResponse, HttpStatus.OK);
    }

    @PostMapping("/fund/{id}")
    public ResponseEntity<WalletResponse> fundWallet(@PathVariable Long id, @RequestParam Double amount) {

        WalletResponse walletResponse = walletService.fundWalletByWalletId(id, amount);
        return new ResponseEntity<>(walletResponse, HttpStatus.OK);
    }
}

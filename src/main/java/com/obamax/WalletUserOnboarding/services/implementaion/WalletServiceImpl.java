package com.obamax.WalletUserOnboarding.services.implementaion;

import com.obamax.WalletUserOnboarding.exceptions.BadRequestException;
import com.obamax.WalletUserOnboarding.exceptions.ResourceNotFoundException;
import com.obamax.WalletUserOnboarding.models.Wallet;
import com.obamax.WalletUserOnboarding.payload.responses.WalletResponse;
import com.obamax.WalletUserOnboarding.repositories.WalletRepository;
import com.obamax.WalletUserOnboarding.services.WalletService;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public WalletResponse fetchWalletBalance(Long id) {
        Wallet wallet = getWalletById(id);
        return getWalletResponse(wallet);
    }

    @Override
    public WalletResponse fundWalletByWalletId(Long id, Double amount) {

        if (amount <= 0) throw new BadRequestException("Amount cannot be less than or equal to zero.");

        Wallet wallet = getWalletById(id);

        wallet.setBalance(wallet.getBalance() + amount);

        return getWalletResponse(walletRepository.save(wallet));
    }

    private Wallet getWalletById(Long id) {
        return walletRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("No such wallet.");
        });
    }

    private WalletResponse getWalletResponse(Wallet wallet) {
        return WalletResponse.builder()
                .walletId(wallet.getId())
                .accountId(wallet.getUser().getId())
                .accountName(wallet.getUser().getName())
                .balance(wallet.getBalance())
                .build();
    }
}

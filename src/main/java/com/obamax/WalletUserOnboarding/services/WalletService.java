package com.obamax.WalletUserOnboarding.services;

import com.obamax.WalletUserOnboarding.payload.responses.WalletResponse;

public interface WalletService {
    WalletResponse fetchWalletBalance(Long id);

    WalletResponse fundWalletByWalletId(Long id, Double amount);
}

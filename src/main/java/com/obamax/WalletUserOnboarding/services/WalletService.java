package com.obamax.WalletUserOnboarding.services;

import com.obamax.WalletUserOnboarding.payload.requests.WalletPurchaseRequest;
import com.obamax.WalletUserOnboarding.payload.requests.WalletWithdrawalRequest;
import com.obamax.WalletUserOnboarding.payload.responses.WalletPurchaseResponse;
import com.obamax.WalletUserOnboarding.payload.responses.WalletResponse;
import com.obamax.WalletUserOnboarding.payload.responses.WalletWithdrawalResponse;

public interface WalletService {
    WalletResponse fetchWalletBalance(Long id);
    WalletResponse fundWalletByWalletId(Long id, Double amount);
    WalletWithdrawalResponse walletWithdrawal(WalletWithdrawalRequest walletWithdrawalRequest);
    WalletPurchaseResponse makePurchase(WalletPurchaseRequest walletPurchaseRequest);
}

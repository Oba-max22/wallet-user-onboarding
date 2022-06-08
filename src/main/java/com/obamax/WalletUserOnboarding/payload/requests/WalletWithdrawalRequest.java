package com.obamax.WalletUserOnboarding.payload.requests;

import lombok.Data;

@Data
public class WalletWithdrawalRequest {
    private Long walletId;
    private Double amount;
    private String bankName;
    private String beneficiaryAccountNumber;
    private String beneficiaryAccountName;
}

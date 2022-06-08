package com.obamax.WalletUserOnboarding.payload.responses;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WalletWithdrawalResponse {
    private String message;
    private Long walletId;
    private Double amount;
    private String bankName;
    private String beneficiaryAccountNumber;
}

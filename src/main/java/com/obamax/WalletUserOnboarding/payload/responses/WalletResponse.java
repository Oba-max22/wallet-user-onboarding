package com.obamax.WalletUserOnboarding.payload.responses;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WalletResponse {

    private Long walletId;

    private Long accountId;

    private String accountName;

    private double balance;
}

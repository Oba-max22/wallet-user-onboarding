package com.obamax.WalletUserOnboarding.payload.requests;

import lombok.Data;

@Data
public class WalletPurchaseRequest {

    private Long walletId;
    private Long productId;
    private Long quantity;
}

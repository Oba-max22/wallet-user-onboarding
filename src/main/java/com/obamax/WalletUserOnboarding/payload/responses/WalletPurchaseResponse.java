package com.obamax.WalletUserOnboarding.payload.responses;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WalletPurchaseResponse {

    private Long message;
    private Long walletId;
    private Long productId;
    private Long quantity;
    private Double totalAmount;
    private LocalDateTime timeOfPurchase;
}

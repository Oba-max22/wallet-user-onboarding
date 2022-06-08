package com.obamax.WalletUserOnboarding.payload.requests;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

@Data
public class ProductRequest {

    @NotBlank
    private String name;

    @NotBlank
    @DecimalMin(value = "0.0", inclusive = false)
    private Double price;
}

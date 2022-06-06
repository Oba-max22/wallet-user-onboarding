package com.obamax.WalletUserOnboarding.payload.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupRequest {
    @NotBlank(message = "name can not be blank")
    private String name;

    @NotBlank(message = "email cannot be empty")
    @Email(message = "must be email")
    private String emailAddress;

    @NotBlank(message = "password cannot be empty")
    private String password;
}

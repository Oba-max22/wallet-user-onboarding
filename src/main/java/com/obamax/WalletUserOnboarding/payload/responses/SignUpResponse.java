package com.obamax.WalletUserOnboarding.payload.responses;

import com.obamax.WalletUserOnboarding.models.User;
import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SignUpResponse {

    private String message;

    private long Id;

    private String name;

    private  String emailAddress;

    private Long WalletId;

    public static SignUpResponse build(User user){
        return new SignUpResponse (
                "Signup Successful",
                user.getId(),
                user.getName(),
                user.getEmailAddress(),
                user.getWallet().getId()
        );
    }
}

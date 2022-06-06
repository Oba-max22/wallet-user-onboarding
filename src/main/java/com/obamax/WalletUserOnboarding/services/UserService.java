package com.obamax.WalletUserOnboarding.services;

import com.obamax.WalletUserOnboarding.models.User;
import com.obamax.WalletUserOnboarding.payload.requests.SignupRequest;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByEmail(String email);
    User signup(SignupRequest signupRequest);
}

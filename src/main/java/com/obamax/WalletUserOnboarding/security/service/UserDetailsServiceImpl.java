package com.obamax.WalletUserOnboarding.security.service;

import com.obamax.WalletUserOnboarding.models.User;
import com.obamax.WalletUserOnboarding.repositories.UserRepository;
import com.obamax.WalletUserOnboarding.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        Optional<User> user = userRepository.findByEmailAddress(email);
        return UserDetailsImpl.build(user.get());
    }
}
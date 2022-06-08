package com.obamax.WalletUserOnboarding.services.implementaion;

import com.obamax.WalletUserOnboarding.exceptions.BadRequestException;
import com.obamax.WalletUserOnboarding.models.Role;
import com.obamax.WalletUserOnboarding.models.User;
import com.obamax.WalletUserOnboarding.models.Wallet;
import com.obamax.WalletUserOnboarding.models.enums.RoleType;
import com.obamax.WalletUserOnboarding.payload.requests.SignupRequest;
import com.obamax.WalletUserOnboarding.repositories.RoleRepository;
import com.obamax.WalletUserOnboarding.repositories.UserRepository;
import com.obamax.WalletUserOnboarding.repositories.WalletRepository;
import com.obamax.WalletUserOnboarding.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final WalletRepository walletRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository,
                           RoleRepository roleRepository, WalletRepository walletRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmailAddress(email);
    }

    @Override
    public User signup(SignupRequest signupRequest) {
        if (userRepository.existsByEmailAddress(signupRequest.getEmailAddress())) {
            throw new BadRequestException("Error: Email is already taken!");
        }
        if (!isValidPassword(signupRequest.getPassword())) {
            throw new BadRequestException("Error: Password must be between 8 and 20, must be an Alphabet or Number");
        }

        // Create user
        User user = new User();
        user.setName(signupRequest.getName());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setEmailAddress(signupRequest.getEmailAddress());

        // Set role
        List<Role> roles = new ArrayList<>();
        Role userRole = new Role();
        userRole.setType(RoleType.USER);
        roles.add(userRole);
        user.setRoles(roles);

        // Create wallet
        Wallet wallet = new Wallet();
        wallet.setBalance(0.0);
        user.setWallet(walletRepository.save(wallet));

        return userRepository.save(user);
    }

    private boolean isValidPassword(String password) {
        String regex = "^(([0-9]|[a-z]|[A-Z]|[@])*){8,20}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
        if (password == null) {
            throw new BadRequestException("Error: Password cannot be null");
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }
}

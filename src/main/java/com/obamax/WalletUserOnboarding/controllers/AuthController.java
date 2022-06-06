package com.obamax.WalletUserOnboarding.controllers;

import com.obamax.WalletUserOnboarding.models.User;
import com.obamax.WalletUserOnboarding.payload.requests.LoginRequest;
import com.obamax.WalletUserOnboarding.payload.requests.SignupRequest;
import com.obamax.WalletUserOnboarding.payload.responses.LoginResponse;
import com.obamax.WalletUserOnboarding.payload.responses.SignUpResponse;
import com.obamax.WalletUserOnboarding.repositories.RoleRepository;
import com.obamax.WalletUserOnboarding.security.jwt.JwtUtils;
import com.obamax.WalletUserOnboarding.security.service.UserDetailsServiceImpl;
import com.obamax.WalletUserOnboarding.services.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailService;
    private final JwtUtils jwtUtils;

    public AuthController(UserService userService, RoleRepository roleRepository, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailService,
                          JwtUtils jwtUtils) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signup")
    @ApiOperation(value = "User Signup")
    public ResponseEntity<SignUpResponse> doSignup(@Valid @RequestBody SignupRequest request){
        User registeredUser = userService.signup(request);
        return new ResponseEntity<>(SignUpResponse.build(registeredUser), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @ApiOperation(value = "User Login")
    public ResponseEntity<?> doLogin(@Valid @RequestBody LoginRequest request){
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmailAddress(), request.getPassword()
                        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userDetailService.loadUserByUsername(request.getEmailAddress());

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        final String jwtToken = jwtUtils.generateToken(userDetails);

        LoginResponse response = new LoginResponse(jwtToken, roles);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

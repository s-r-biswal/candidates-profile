package com.profiles.candiadate.controller;

import com.profiles.candiadate.dto.LoginRequest;
import com.profiles.candiadate.dto.LoginResponse;
import com.profiles.candiadate.security.JwtUtil;
import com.profiles.candiadate.service.CustomUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.   RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService service;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          CustomUserDetailsService service) {
        this.authManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        UserDetails user = service.loadUserByUsername(loginRequest.username());
        String jwt = jwtUtil.generateToken(user);
        return ResponseEntity.ok(new LoginResponse(jwt));
    }
}

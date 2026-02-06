package org.example.springauthjwt.controller;

import lombok.RequiredArgsConstructor;
import org.example.springauthjwt.controller.dto.LoginRequest;
import org.example.springauthjwt.controller.dto.LoginResponse;
import org.example.springauthjwt.repository.UserRepository;
import org.example.springauthjwt.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
public class TokenController {
    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepo;
    private final UserRepository userRepository;
    private final UserServiceImpl userServiceImpl;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
        public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){

            var user = userRepo.findByEmail(loginRequest.email());
            if(user.isEmpty() || !userServiceImpl.isLoginCorrect(loginRequest, bCryptPasswordEncoder, )){
                throw new BadCredentialsException("Email or Password is Invalid");
            }

            var now = Instant.now();
            var expiresIn = 300L;

            var claims = JwtClaimsSet.builder()
                    .issuer("backend")
                    .subject(user.get().getId().toString())
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(expiresIn))
                    .build();

            var jwtValue = "";

            return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
    }
}

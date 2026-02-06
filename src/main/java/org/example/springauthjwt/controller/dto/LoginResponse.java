package org.example.springauthjwt.controller.dto;

public record LoginResponse(String accessToken, Long expiresIn) {
}

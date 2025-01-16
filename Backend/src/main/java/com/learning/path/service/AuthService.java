package com.learning.path.service;

import com.learning.path.dto.AuthRequest;
import com.learning.path.dto.AuthResponse;

public interface AuthService {
    AuthResponse authenticate(AuthRequest request);
}
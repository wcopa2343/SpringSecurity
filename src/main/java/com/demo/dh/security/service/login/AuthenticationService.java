package com.demo.dh.security.service.login;

import com.demo.dh.security.dto.AuthenticationRequest;
import com.demo.dh.security.dto.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse login(AuthenticationRequest request);
}

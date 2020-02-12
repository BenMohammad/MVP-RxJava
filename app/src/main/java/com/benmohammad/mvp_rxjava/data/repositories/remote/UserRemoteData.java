package com.benmohammad.mvp_rxjava.data.repositories.remote;

import com.benmohammad.mvp_rxjava.data.network.model.AuthResponse;
import com.benmohammad.mvp_rxjava.data.network.model.LoginRequest;
import com.benmohammad.mvp_rxjava.data.network.model.RegisterRequest;
import com.benmohammad.mvp_rxjava.data.network.services.UserService;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UserRemoteData {

    private UserService userService;

    @Inject
    UserRemoteData(UserService userService) {
        this.userService = userService;
    }

    public Observable<AuthResponse> authenticate() {
        return userService.authenticate();
    }

    public Observable<AuthResponse> login(LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    public Observable<AuthResponse> register(RegisterRequest registerRequest) {
        return userService.registerUser(registerRequest);
    }
}

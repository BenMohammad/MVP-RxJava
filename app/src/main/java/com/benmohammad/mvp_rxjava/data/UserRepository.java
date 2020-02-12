package com.benmohammad.mvp_rxjava.data;

import com.benmohammad.mvp_rxjava.data.network.model.AuthResponse;
import com.benmohammad.mvp_rxjava.data.network.model.LoginRequest;
import com.benmohammad.mvp_rxjava.data.network.model.RegisterRequest;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface UserRepository {

    Observable<AuthResponse> authenticate();
    Observable<AuthResponse> login(LoginRequest loginRequest);
    Observable<AuthResponse> registerUser(RegisterRequest registerRequest);
    Completable logout();
}

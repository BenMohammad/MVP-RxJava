package com.benmohammad.mvp_rxjava.data.repositories;

import com.benmohammad.mvp_rxjava.data.UserRepository;
import com.benmohammad.mvp_rxjava.data.network.model.AuthResponse;
import com.benmohammad.mvp_rxjava.data.network.model.LoginRequest;
import com.benmohammad.mvp_rxjava.data.network.model.RegisterRequest;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class UserDataRepository implements UserRepository {


    @Override
    public Observable<AuthResponse> authenticate() {
        return null;
    }

    @Override
    public Observable<AuthResponse> login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public Observable<AuthResponse> registerUser(RegisterRequest registerRequest) {
        return null;
    }

    @Override
    public Completable logout() {
        return null;
    }
}

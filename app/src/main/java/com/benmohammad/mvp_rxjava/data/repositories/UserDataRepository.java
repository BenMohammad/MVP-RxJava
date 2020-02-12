package com.benmohammad.mvp_rxjava.data.repositories;

import com.benmohammad.mvp_rxjava.data.UserRepository;
import com.benmohammad.mvp_rxjava.data.network.model.AuthResponse;
import com.benmohammad.mvp_rxjava.data.network.model.LoginRequest;
import com.benmohammad.mvp_rxjava.data.network.model.RegisterRequest;
import com.benmohammad.mvp_rxjava.data.repositories.local.UserLocalData;
import com.benmohammad.mvp_rxjava.data.repositories.remote.UserRemoteData;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class UserDataRepository implements UserRepository {


    private UserLocalData userLocalData;
    private UserRemoteData userRemoteData;

    @Inject
    UserDataRepository(UserRemoteData userRemoteData, UserLocalData userLocalData) {
        this.userLocalData = userLocalData;
        this.userRemoteData = userRemoteData;
    }


    @Override
    public Observable<AuthResponse> authenticate() {
        String token = userLocalData.getToken();
        if(token != null) {
            return userRemoteData.authenticate();
        } else {
            return Observable.error(new Throwable("No Token!!"));
        }
    }

    @Override
    public Observable<AuthResponse> login(LoginRequest loginRequest) {
        return userRemoteData.login(loginRequest)
                .doOnNext(authResponse -> {
                    userLocalData.setToken(authResponse.getToken());
                    userLocalData.setCurrentUserId(authResponse.getUserId());
                });
    }

    @Override
    public Observable<AuthResponse> registerUser(RegisterRequest registerRequest) {
        return userRemoteData.register(registerRequest)
                .doOnNext(authResponse -> {
                    userLocalData.setToken(authResponse.getToken());
                    userLocalData.setCurrentUserId(authResponse.getUserId());
                });
    }

    @Override
    public Completable logout() {
        return userLocalData.logout();
    }
}

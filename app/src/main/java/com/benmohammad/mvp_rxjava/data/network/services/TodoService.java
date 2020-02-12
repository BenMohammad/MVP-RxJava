package com.benmohammad.mvp_rxjava.data.network.services;

import com.benmohammad.mvp_rxjava.data.model.Todo;
import com.benmohammad.mvp_rxjava.data.network.model.TodoRequest;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TodoService {

    @GET("todos")
    Observable<List<Todo>> getTodos();

    @POST("todos")
    Completable saveTodo(@Body TodoRequest todoRequest);

    @PATCH("todos/{id}")
    Completable updateTodoById(@Path("id") String id);

    @DELETE("todos/{id}")
    Completable removeTodoByBody(@Path("id") String id);
}

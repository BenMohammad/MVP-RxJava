package com.benmohammad.mvp_rxjava.data.repositories;

import com.benmohammad.mvp_rxjava.data.TodoRepository;
import com.benmohammad.mvp_rxjava.data.model.Todo;
import com.benmohammad.mvp_rxjava.data.network.model.TodoRequest;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class TodoDataRepository implements TodoRepository {


    @Override
    public Completable removeTodoById(String id) {
        return null;
    }

    @Override
    public Completable updateTodoById(String id) {
        return null;
    }

    @Override
    public Observable<List<Todo>> getAllTodos() {
        return null;
    }

    @Override
    public Completable saveTodo(TodoRequest todoRequest) {
        return null;
    }
}

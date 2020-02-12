package com.benmohammad.mvp_rxjava.data.repositories;

import com.benmohammad.mvp_rxjava.data.TodoRepository;
import com.benmohammad.mvp_rxjava.data.model.Todo;
import com.benmohammad.mvp_rxjava.data.network.model.TodoRequest;
import com.benmohammad.mvp_rxjava.data.repositories.local.TodoLocalData;
import com.benmohammad.mvp_rxjava.data.repositories.remote.TodoRemoteData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class TodoDataRepository implements TodoRepository {

    private TodoLocalData todoLocalData;
    private TodoRemoteData todoRemoteData;

    @Inject
    public TodoDataRepository(TodoLocalData todoLocalData, TodoRemoteData todoRemoteData) {
        this.todoLocalData = todoLocalData;
        this.todoRemoteData = todoRemoteData;
    }

    @Override
    public Completable removeTodoById(String id) {
        return todoRemoteData.removeTodoById(id);
    }

    @Override
    public Completable updateTodoById(String id) {
        return todoRemoteData.updateTodoById(id);
    }

    @Override
    public Observable<List<Todo>> getAllTodos() {
        return todoRemoteData.getAllTodos();
    }

    @Override
    public Completable saveTodo(TodoRequest todoRequest) {
        return todoRemoteData.saveTodo(todoRequest);
    }
}

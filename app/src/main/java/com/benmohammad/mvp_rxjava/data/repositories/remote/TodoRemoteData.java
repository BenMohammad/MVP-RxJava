package com.benmohammad.mvp_rxjava.data.repositories.remote;

import com.benmohammad.mvp_rxjava.data.model.Todo;
import com.benmohammad.mvp_rxjava.data.network.model.TodoRequest;
import com.benmohammad.mvp_rxjava.data.network.services.TodoService;
import com.benmohammad.mvp_rxjava.data.repositories.TodoDataRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class TodoRemoteData {

    private TodoService todoService;

    @Inject
    TodoRemoteData (TodoService todoService) {
        this.todoService = todoService;
    }

    public Observable<List<Todo>> getAllTodos() {
        return todoService.getTodos();
    }

    public Completable saveTodo(TodoRequest todoRequest) {
        return todoService.saveTodo(todoRequest);
    }

    public Completable updateTodoById(String id) {
        return todoService.updateTodoById(id);
    }

    public Completable removeTodoById(String id) {
        return todoService.removeTodoByBody(id);
    }


}

package com.benmohammad.mvp_rxjava.data;

import com.benmohammad.mvp_rxjava.data.model.Todo;
import com.benmohammad.mvp_rxjava.data.network.model.TodoRequest;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface TodoRepository {

    Completable removeTodoById(String id);

    Completable updateTodoById(String id);

    Observable<List<Todo>> getAllTodos();

    Completable saveTodo(TodoRequest todoRequest);
}

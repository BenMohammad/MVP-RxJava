package com.benmohammad.mvp_rxjava.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.benmohammad.mvp_rxjava.R;
import com.benmohammad.mvp_rxjava.data.model.Todo;

import java.util.Collections;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoHolder> {

    private Context context;
    private List<Todo> mTodoList;

    public TodoAdapter(Context context) {
        this.context = context;
        mTodoList = Collections.emptyList();
    }

    public void setTodoList(List<Todo> todoList) {
        mTodoList = todoList;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        mTodoList.remove(position);
        notifyDataSetChanged();
    }

    public Todo get(int position) {
        return mTodoList.get(position);
    }

    public void setItemCompleted(int position) {
        mTodoList.get(position).setCompleted();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TodoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new TodoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoHolder holder, int position) {
        holder.bindTodo(mTodoList.get(position));
    }

    @Override
    public int getItemCount() {
        return mTodoList.isEmpty() ? 0 : mTodoList.size();
    }

    class TodoHolder extends RecyclerView.ViewHolder {
        TextView mStateText;
        TextView mTodoText;
        TextView mDateText;

        public TodoHolder(@NonNull View itemView) {
            super(itemView);
            mStateText = itemView.findViewById(R.id.state_text);
            mTodoText = itemView.findViewById(R.id.todo_text);
            mDateText = itemView.findViewById(R.id.date_text);
        }

        void bindTodo(Todo  todo){
            mTodoText.setText(todo.getText());
            mDateText.setText(todo.getCreateDate());
            if(todo.isCompleted()) {
                setCompleted();
            } else {
                setActive();
            }
        }

        private void setCompleted() {
            mStateText.setCompoundDrawablesWithIntrinsicBounds(
                    context.getDrawable(R.drawable.ic_done),null, null, null);
            mStateText.setText(R.string.todo_completed);
        }

        private void setActive() {
            mStateText.setCompoundDrawablesWithIntrinsicBounds(
                    context.getDrawable(R.drawable.ic_active), null, null, null);
            mStateText.setText(R.string.active);
        }
    }
}

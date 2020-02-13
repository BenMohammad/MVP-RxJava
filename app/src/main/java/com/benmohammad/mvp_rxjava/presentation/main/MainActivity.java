package com.benmohammad.mvp_rxjava.presentation.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.benmohammad.mvp_rxjava.R;
import com.benmohammad.mvp_rxjava.data.model.Todo;
import com.benmohammad.mvp_rxjava.presentation.adapters.SpacingItemDecoration;
import com.benmohammad.mvp_rxjava.presentation.adapters.TodoAdapter;
import com.benmohammad.mvp_rxjava.presentation.authentication.AuthenticationActivity;
import com.benmohammad.mvp_rxjava.presentation.base.BaseActivity;
import com.benmohammad.mvp_rxjava.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainContract.View {


    @BindView(R.id.empty_text)
    TextView mEmptyText;

    @BindView(R.id.progress_main)
    ProgressBar mProgress;

    @BindView(R.id.rv_todos)
    RecyclerView mRecyclerView;


    @Inject
    MainPresenter mainPresenter;

    @Inject
    TodoAdapter todoAdapter;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initUi() {
        setUpRecyclerView();
    }

    public void load() {
        mainPresenter.getTodos();

    }



    @OnClick(R.id.fab_add)
    public void onFabClicked() {
        DialogFragment dialogFragment = CreateTodoFragment.newInstance();
        dialogFragment.show(getSupportFragmentManager(), Constants.CREATE_TODO_FRAGMENT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.attachView(this);
        load();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }

    @Override
    public void showTodos(List<Todo> todoList) {
        mEmptyText.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        todoAdapter.setTodoList(todoList);
    }

    @Override
    public void showTodosFailure(String error) {
        showSnackBar(error);
    }

    @Override
    public void updateTodoSuccess() {

    }

    @Override
    public void updateTodoFailure(String message) {
        showSnackBar(message);
        load();
    }

    @Override
    public void removeTodoSuccess() {

    }

    @Override
    public void removeTodosFailure(String message) {
        showSnackBar(message);
        load();
    }

    @Override
    public void showEmpty() {
        mEmptyText.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void navigateToAuthenticationScreen() {
        start(AuthenticationActivity.class);
        finish();
    }

    private void setUpRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new SpacingItemDecoration(Constants.RECYCLER_SPACING));
        mRecyclerView.setAdapter(todoAdapter);

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT
        ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                if(direction == ItemTouchHelper.LEFT) {
                    mainPresenter.removeTodo(todoAdapter.get(position).getId());
                    todoAdapter.deleteItem(position);
                    if(todoAdapter.getItemCount() == 0) {
                        showEmpty();
                    }
                } else {
                    mainPresenter.updateTodo(todoAdapter.get(position).getId());
                    todoAdapter.setItemCompleted(position);
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    View itemView = viewHolder.itemView;
                    if(dX > 0) {
                        Paint paint = new Paint();
                        paint.setColor(getResources().getColor(R.color.colorPrimary));
                        c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX,
                                (float) itemView.getBottom(), paint);

                    } else {
                        Paint paint = new Paint();
                        paint.setColor(getResources().getColor(R.color.colorPrimaryDark));
                        c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(),
                                (float) itemView.getRight(), (float)itemView.getBottom(), paint);

                    }

                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }
}

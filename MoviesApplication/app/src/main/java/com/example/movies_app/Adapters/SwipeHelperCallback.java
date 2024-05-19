package com.example.movies_app.Adapters;

import android.app.AlertDialog;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SwipeHelperCallback extends ItemTouchHelper.Callback {
    ItemTouchHelperAdapter myadapter;

    public SwipeHelperCallback(ItemTouchHelperAdapter myadapter) {
        this.myadapter = myadapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int drag = ItemTouchHelper.DOWN | ItemTouchHelper.UP;
        int swipe = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(drag, swipe);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);

    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        final int fromPos = viewHolder.getAdapterPosition();
        final int toPos = target.getAdapterPosition();
        myadapter.onItemMove(fromPos, toPos);
        return true;// true if moved, false otherwise

    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            myadapter.onItemSwiped(viewHolder.getAdapterPosition(), direction);
    }

    @Override
    public boolean canDropOver(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder current, @NonNull RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }
}

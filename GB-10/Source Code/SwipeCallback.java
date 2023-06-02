package com.app.pepens.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.app.pepens.R;

/**
 * Created by Vikas Kumar Singh on 25/09/20.
 */
public class SwipeCallback extends ItemTouchHelper.Callback {

    Context mContext;
    private Paint mClearPaint;
    private ColorDrawable mBackground;
    private int backgroundColor;
    private Drawable drawable;
    private int intrinsicWidth;
    private int intrinsicHeight;

    public SwipeCallback(Context mContext) {
        this.mContext = mContext;
//        mBackground = new ColorDrawable();
//        backgroundColor = Color.parseColor("#FF0000");
//        mClearPaint = new Paint();
//        mClearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
//        deleteDrawable = ContextCompat.getDrawable(mContext, R.drawable.ic_delete);
//        intrinsicWidth = deleteDrawable.getIntrinsicWidth();
//        intrinsicHeight = deleteDrawable.getIntrinsicHeight();
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        View itemView = viewHolder.itemView;
        int itemHeight = itemView.getHeight();

        boolean isCancelled = dX == 0 && !isCurrentlyActive;

        mClearPaint = new Paint();
        mClearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        clearCanvas(c, itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
        mBackground = new ColorDrawable();
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(50);

        if (isCancelled) {
            clearCanvas(c, itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            return;
        } else if (dX < 0) {
            drawable = ContextCompat.getDrawable(mContext, R.drawable.ic_delete);
            intrinsicWidth = drawable.getIntrinsicWidth();
            intrinsicHeight = drawable.getIntrinsicHeight();
            backgroundColor = Color.parseColor("#FF0000");
            mBackground.setColor(backgroundColor);
            mBackground.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
            mBackground.draw(c);
            int iconMargin = (itemHeight - intrinsicHeight) / 2;
            drawable.setBounds((itemView.getRight() - iconMargin - intrinsicWidth) + 30, (itemView.getTop() + (itemHeight - intrinsicHeight) / 2), (itemView.getRight() - iconMargin) + 30, ((itemView.getTop() + (itemHeight - intrinsicHeight) / 2) + intrinsicHeight));
            drawable.draw(c);
            c.drawText("Delete", (itemView.getRight() - iconMargin - intrinsicWidth* 3), (itemView.getTop() + (itemHeight + 40) / 2), paint);
        } else if (dX > 0) {
            drawable = ContextCompat.getDrawable(mContext, R.drawable.ic_round_edit);
            intrinsicWidth = drawable.getIntrinsicWidth();
            intrinsicHeight = drawable.getIntrinsicHeight();
            backgroundColor = Color.parseColor("#2196F3");
            mBackground.setColor(backgroundColor);
            mBackground.setBounds(itemView.getLeft() + (int) dX, itemView.getTop(), itemView.getLeft(), itemView.getBottom());
            mBackground.draw(c);
            int iconMargin = (itemHeight - intrinsicHeight) / 2;
            drawable.setBounds((itemView.getLeft() + iconMargin) - 30, (itemView.getTop() + (itemHeight - intrinsicHeight) / 2), (itemView.getLeft() + iconMargin + intrinsicWidth) - 30, ((itemView.getTop() + (itemHeight - intrinsicHeight) / 2) + intrinsicHeight));
            drawable.draw(c);
            c.drawText("Edit", (itemView.getLeft() + iconMargin + intrinsicWidth), (itemView.getTop() + (itemHeight + 40) / 2), paint);
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    private void clearCanvas(Canvas c, Float left, Float top, Float right, Float bottom) {
        c.drawRect(left, top, right, bottom, mClearPaint);
    }

    @Override
    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        return 0.5f;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }

}
package com.example.simpleprojectroom;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_SWIPE;
import static androidx.recyclerview.widget.ItemTouchHelper.Callback;
import static androidx.recyclerview.widget.ItemTouchHelper.LEFT;
import static androidx.recyclerview.widget.ItemTouchHelper.RIGHT;



enum ButtonState {
    GONE,
    LEFT_VISIBLE,
    RIGHT_VISIBLE
}

public class SwipeController extends Callback {

    private RecyclerView.ViewHolder currentItemViewHolder = null;

    private boolean swipeBack = false;
    private ButtonState buttonShowedState = ButtonState.GONE;
    private static final float buttonWidth = 200;
    private SwipeControllerActions buttonsActions = null;
    private RectF buttonInstance = null;


//    public SwipeController(SwipeControllerActions buttonsActions) {
//        this.buttonsActions = buttonsActions;
//    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, LEFT | RIGHT);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
       if(actionState == ACTION_STATE_SWIPE){
           setTouchListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
       }

       if(buttonShowedState == ButtonState.GONE){
           super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
       }
        drawButtons(c, viewHolder);
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    private void setTouchListener(final Canvas c,
                                  final RecyclerView recyclerView,
                                  final RecyclerView.ViewHolder viewHolder,
                                  final float dX, final float dY, final int actionState,
                                  final boolean isCurrentlyActive) {
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                swipeBack = event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP;
                if(swipeBack){
                    if(dX < -buttonWidth) buttonShowedState = ButtonState.RIGHT_VISIBLE;
                    else if(dX > buttonWidth) buttonShowedState = ButtonState.LEFT_VISIBLE;
                    if(buttonShowedState != ButtonState.GONE){
                        setTouchDownListener(c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive);
                        setItemsClickable(recyclerView,false);
                    }
                }
                return false;
            }
        });
    }

    private void drawButtons(Canvas c, RecyclerView.ViewHolder viewHolder) {
        float buttonWidthWithoutPadding = buttonWidth - 20;
        float corners = 16;

        View itemView = viewHolder.itemView;
        Paint p = new Paint();

        RectF leftButton = new RectF(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + buttonWidthWithoutPadding, itemView.getBottom());
        p.setColor(Color.BLUE);
        c.drawRoundRect(leftButton, corners, corners, p);
        drawText("EDIT", c, leftButton, p);

        RectF rightButton = new RectF(itemView.getRight() - buttonWidthWithoutPadding, itemView.getTop(), itemView.getRight(), itemView.getBottom());
        p.setColor(Color.RED);
        c.drawRoundRect(rightButton, corners, corners, p);
        drawText("DELETE", c, rightButton, p);

        buttonInstance = null;
        if (buttonShowedState == ButtonState.LEFT_VISIBLE) {
            buttonInstance = leftButton;
        }
        else if (buttonShowedState == ButtonState.RIGHT_VISIBLE) {
            buttonInstance = rightButton;
        }
    }

    private void drawText(String text, Canvas c, RectF button, Paint p) {
        float textSize = 60;
        p.setColor(Color.WHITE);
        p.setAntiAlias(true);
        p.setTextSize(textSize);

        float textWidth = p.measureText(text);
        c.drawText(text, button.centerX()-(textWidth/2), button.centerY()+(textSize/2), p);
    }

    private void setTouchDownListener(final Canvas c,final RecyclerView recyclerView,
                                      final RecyclerView.ViewHolder viewHolder,
                                      final float dX, final float dY,
                                      final int actionState, final boolean isCurrentlyActive){
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    setTouchUpListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
                return false;
            }
        });
    }

    private void setTouchUpListener(final Canvas c,
                                    final RecyclerView recyclerView,
                                    final RecyclerView.ViewHolder viewHolder,
                                    final float dX, final float dY,
                                    final int actionState, final boolean isCurrentlyActive){
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    SwipeController.super.onChildDraw(c,recyclerView,viewHolder,0F,dY,actionState,isCurrentlyActive);
                    recyclerView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return false;
                        }
                    });
                    setItemsClickable(recyclerView,true);
                    swipeBack = false;
                    buttonShowedState = ButtonState.GONE;

                    if (buttonsActions != null && buttonInstance != null && buttonInstance.contains(event.getX(), event.getY())) {
                        if (buttonShowedState == ButtonState.LEFT_VISIBLE) {
                            buttonsActions.onLeftClicked(viewHolder.getAdapterPosition());
                        }
                        else if (buttonShowedState == ButtonState.RIGHT_VISIBLE) {
                            buttonsActions.onRightClicked(viewHolder.getAdapterPosition());
                        }
                    }
                }
                return false;
            }
        });
    }

    public void setItemsClickable(RecyclerView recyclerView,boolean isClickable){
        for (int i = 0; i < recyclerView.getChildCount(); ++i) {
            recyclerView.getChildAt(i).setClickable(isClickable);
        }
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        if (swipeBack) {
            swipeBack = false;
            return 0;
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    public void onDraw(Canvas c) {
        if (currentItemViewHolder != null) {
            drawButtons(c, currentItemViewHolder);
        }
    }
}

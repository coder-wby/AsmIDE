package com.word.asmide.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.word.asmide.Values;
import com.word.asmide.code.Token;

import java.util.ArrayList;

public class CodeEditor extends View {

    String[] text;
    //将不同token的不同高亮颜色存储起来
    int[] type;
    Paint paint;
    private OnMoveListener onMoveListener;

    public CodeEditor(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(text != null && type != null) {
            for (int i : type) {
                paint.setColor(i);
                canvas.drawText(text[0], 0, 0, paint);
            }
        }

    }

    public void setText(String text) {
        this.text = new String[]{text};
        this.type = new int[]{Values.TYPE_TEXT_COLOR};
        //重绘
        invalidate();
    }

    public void setText(ArrayList<Token> tokens) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //触摸点的起始坐标
        float startX = 0;
        float startY = 0;
        //滑动后触摸点的坐标
        float lastX;
        float lastY;
        //滑动的距离
        float offsetX;
        float offsetY;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                startX = event.getX();
                startY = event.getY();
                break;

            case MotionEvent.ACTION_MOVE :
                lastX = event.getX();
                lastY = event.getY();
                offsetX = startX-lastX;
                offsetY = startY-lastY;
                if(onMoveListener != null)
                    onMoveListener.OnMove(offsetX,offsetY);
                startX = event.getX();
                startY = event.getY();
                break;

        }
        return true;
    }

    public void setOnMoveListener(OnMoveListener onMoveListener) {
        this.onMoveListener = onMoveListener;
    }

    public interface OnMoveListener {
        /**
         *
         * @param offsetX X坐标的偏移量（startX-lastX）
         * @param offsetY Y坐标的偏移量（startY-lastY）
         */
        void OnMove(float offsetX,float offsetY);
    }
}

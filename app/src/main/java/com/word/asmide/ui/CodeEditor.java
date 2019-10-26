package com.word.asmide.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
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
//
    }
}

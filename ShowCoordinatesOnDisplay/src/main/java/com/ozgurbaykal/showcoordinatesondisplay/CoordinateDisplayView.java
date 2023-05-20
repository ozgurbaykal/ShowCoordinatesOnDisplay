package com.ozgurbaykal.showcoordinatesondisplay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class CoordinateDisplayView extends View {
    private Paint linePaint;
    private Paint textPaint;
    private int touchX = -1;
    private int touchY = -1;
    private int lineColor = 0xFFFF0000;
    private int textColor = 0xFFFFFFFF;
    private Handler handler = new Handler();
    boolean alwaysDisplayCoordinates = false;
    int closeCoordinatesWithDelayMillis = 1000;
    Context mContext;
    Toast toastMessage = null;

    public CoordinateDisplayView(Context context) {
        super(context);
        mContext = context;
    }

    public void init() {
        linePaint = new Paint();
        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(3);

        textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setTextSize(50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (touchX != -1 && touchY != -1) {
            // Dikey çizgiyi çiz
            canvas.drawLine(touchX, 0, touchX, getHeight(), linePaint);
            // Yatay çizgiyi çiz
            canvas.drawLine(0, touchY, getWidth(), touchY, linePaint);
            // Koordinatları yaz
            canvas.drawText("X: " + touchX + ", Y: " + touchY, touchX + 25, touchY - 25, textPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                touchX = (int) event.getX();
                touchY = (int) event.getY();
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:

                if (!alwaysDisplayCoordinates) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            touchX = -1;
                            touchY = -1;
                            invalidate();
                        }
                    }, closeCoordinatesWithDelayMillis);
                }

                if(mContext!=null){

                    if (toastMessage!= null)
                        toastMessage.cancel();

                    toastMessage= Toast.makeText(mContext, "X: " + touchX + ", Y: " + touchY, Toast.LENGTH_SHORT);
                    toastMessage.show();
                }

                return true;


        }

        return super.onTouchEvent(event);
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public boolean isAlwaysDisplayCoordinates() {
        return alwaysDisplayCoordinates;
    }

    public void setAlwaysDisplayCoordinates(boolean alwaysDisplayCoordinates) {
        this.alwaysDisplayCoordinates = alwaysDisplayCoordinates;
    }

    public int getCloseCoordinatesWithDelayMillis() {
        return closeCoordinatesWithDelayMillis;
    }

    public void setCloseCoordinatesWithDelayMillis(int closeCoordinatesWithDelayMillis) {
        this.closeCoordinatesWithDelayMillis = closeCoordinatesWithDelayMillis;
    }
}
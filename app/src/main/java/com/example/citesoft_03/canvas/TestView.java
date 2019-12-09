package com.example.citesoft_03.canvas;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class TestView extends View {
    MediaPlayer mp;
    private float mLastTouchX;
    private float mLastTouchY;
    private float mPosX;
    private float mPosY;
    private Bitmap bitmap;
    private Rect rect;
    private float cX, cY; // circle coords

    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 2.1f;
    private float scalePointX;
    private float scalePointY;

    public TestView (Context context) {
        super(context);

        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.RED);
        p.setAlpha(125);
        p.setTextSize(20);

        rect = canvas.getClipBounds();

        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor, scalePointX, scalePointY);
        canvas.translate(mPosX, mPosY);
        canvas.drawARGB(255, 125, 125, 125);
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.drawCircle(cX, cY, 10, p);
        canvas.restore();

    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Let the ScaleGestureDetector inspect all events.
        mScaleDetector.onTouchEvent(ev);

        final int action = ev.getAction();


        switch(action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {

                final float x = (ev.getX() - scalePointX)/mScaleFactor;
                final float y = (ev.getY() - scalePointY)/mScaleFactor;
                cX = x - mPosX + scalePointX; // canvas X
                cY = y - mPosY + scalePointY; // canvas Y
                mLastTouchX = x;
                mLastTouchY = y;
                break;
            }
            case MotionEvent.ACTION_MOVE: {

                final float x = (ev.getX() - scalePointX)/mScaleFactor;
                final float y = (ev.getY() - scalePointY)/mScaleFactor;
                cX = x - mPosX + scalePointX; // canvas X
                cY = y - mPosY + scalePointY; // canvas Y
                // Only move if the ScaleGestureDetector isn't processing a gesture.
                if (!mScaleDetector.isInProgress()) {
                    final float dx = x - mLastTouchX; // change in X
                    final float dy = y - mLastTouchY; // change in Y
                    mPosX += dx;
                    mPosY += dy;
                    invalidate();
                }

                mLastTouchX = x;
                mLastTouchY = y;
                break;

            }
            case MotionEvent.ACTION_UP: {
                final float x = (ev.getX() - scalePointX)/mScaleFactor;
                final float y = (ev.getY() - scalePointY)/mScaleFactor;
                cX = x - mPosX + scalePointX; // canvas X
                cY = y - mPosY + scalePointY; // canvas Y
                mLastTouchX = 0;
                mLastTouchY = 0;
                invalidate();
            }
        }
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();
            scalePointX =  detector.getFocusX();
            scalePointY = detector.getFocusY();

            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(2.1f, Math.min(mScaleFactor, 10.0f));

            invalidate();
            return true;
        }
    }


}
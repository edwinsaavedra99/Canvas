package com.example.citesoft_03.canvas;

import android.graphics.DashPathEffect;
import android.graphics.Paint;

public class Util {
    public static int color1[]={183,149,11};
    public static int color2[]={99,57,116};
    public static int color3[]={31,97,141};
    public static int color4[]={40,116,166};
    public static int color5[]={20,143,119};
    public static int color6[]={183,149,11};
    public static int color7[]={186,74,0};
    public static int color8[]={95,106,106};
    public static int color9[]={46,64,83};
    public static int color10[]={136,78,160};

    public static Paint Circle_Transparente(int color[]){
        float[] intervals = new float[]{5.0f, 5.0f};
        float phase = 0;
        DashPathEffect dashPathEffect = new DashPathEffect(intervals, phase);
        Paint paint;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setARGB(150, color[0], color[1], color[2]);
        paint.setStrokeWidth(44);
        paint.setStyle(Paint.Style.FILL);
        paint.setPathEffect(dashPathEffect);
        return paint;
    }
    public static Paint Circle_Small(int color[]){
        float[] intervals = new float[]{5.0f, 5.0f};
        float phase = 0;
        DashPathEffect dashPathEffect = new DashPathEffect(intervals, phase);
        Paint paint;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setARGB(255, color[0], color[1], color[2]);
        paint.setStrokeWidth(24);
        paint.setStyle(Paint.Style.FILL);
        paint.setPathEffect(dashPathEffect);
        return paint;
    }
}

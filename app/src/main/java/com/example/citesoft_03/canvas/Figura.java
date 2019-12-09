package com.example.citesoft_03.canvas;

import android.graphics.Paint;

public abstract class Figura {
    protected float x;
    protected  float y;
    protected Paint paint;
    protected int color[];
 //   protected ShapeDrawable drawable;

    public Figura(float _x , float _y, Paint _paint,int color[]){
        this.x = _x;
        this.y = _y;
        this.paint = _paint;
        this.color=color;
    }
    public void setX(float x ){
        this.x =x;
    }
    public float getX() {
        return x;
    }
    public void setY(float y) {
        this.y = y;
    }
    public float getY() {
        return y;
    }
    public void setPaint(Paint paint) {
        this.paint = paint;
    }
    public Paint getPaint() {
        return paint;
    }

    public int[] getColor() {
        return color;
    }

    public void setColor(int[] color) {
        this.color = color;
    }
}

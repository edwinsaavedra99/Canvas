package com.example.citesoft_03.canvas;

import android.graphics.Paint;

public abstract class Figura {
    protected float x;
    protected  float y;
    protected Paint paint;
    public Figura(float _x , float _y, Paint _paint){
        this.x = _x;
        this.y = _y;
        this.paint = _paint;
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
}

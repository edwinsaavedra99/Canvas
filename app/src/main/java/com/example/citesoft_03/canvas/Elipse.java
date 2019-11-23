package com.example.citesoft_03.canvas;

import android.graphics.Paint;

public class Elipse extends Figura{
    private float x1;
    private float y1;
    public Elipse(float _x, float _y, float _x1, float _y1 , Paint _paint){
        super(_x,_y,_paint);
        this.x1 = _x1;
        this.y1 = _y1;
    }
    public void setX1(float _x1) {
        this.x1 = _x1;
    }
    public float getX1() {
        return x1;
    }
    public void setY1(float _y1){
        this.y1 = _y1;
    }
    public float getY1(){
        return y1;
    }
}

package com.example.citesoft_03.canvas;

import android.graphics.Paint;

public class Rectangulo extends Figura {
    private float x1;
    private float y1;
    public Rectangulo(float _x, float _y, float _x1, float _y1 , Paint _paint,int color[]){
        super(_x,_y,_paint,color);
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

    public String toString(){
        return "{\"id\":4,\"x\"="+this.getX()+",\"y\"="+this.getY()+",\"x1\"="+this.getX1()+",\"y1\"="+this.getY1()+"}";
    }
}

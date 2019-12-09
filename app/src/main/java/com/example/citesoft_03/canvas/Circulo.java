package com.example.citesoft_03.canvas;

import android.graphics.Paint;

public class Circulo extends Figura {
    private float radio;
    public Circulo(float _x, float _y, float _radio , Paint _paint,int color[]){
        super(_x,_y,_paint,color);
        this.radio = _radio;

    }
    public void setRadio(float _radio){
        this.radio = _radio;
    }
    public float getRadio(){
        return this.radio;
    }


    public String toString(){
        return "{\"id\":1,\"x\"="+this.getX()+",\"y\"="+this.getY()+",\"r\"="+this.getRadio()+"}" ;
    }
}

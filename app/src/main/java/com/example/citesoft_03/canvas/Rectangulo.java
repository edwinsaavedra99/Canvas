package com.example.citesoft_03.canvas;

import android.graphics.Paint;

public class Rectangulo extends Figura{
    private float ancho;
    private float largo;
    public Rectangulo(float _x, float _y, float _ancho, float _largo , Paint _paint){
        super(_x,_y,_paint);
        this.ancho = _ancho;
        this.largo = _largo;
    }
    public void setAncho(float _ancho) {
        this.ancho = _ancho;
    }
    public float getAncho() {
        return ancho;
    }
    public void setLargo(float _largo) {
        this.largo = _largo;
    }
    public float getLargo() {
        return largo;
    }
}

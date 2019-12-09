package com.example.citesoft_03.canvas;

import android.graphics.Paint;

public class Linea extends Figura {
    private float finX;
    private float finY;

    public Linea(float _x , float _y, float finx,float finy,Paint _paint,int color[]){
        super(_x,_y,_paint,color);
        this.finX = finx;
        this.finY = finy;

    }
   /* protected void onDraw(Canvas canvas) {
        drawable.draw(canvas);
    }*/
    public void setFinX(float x ){
        this.finX =x;
    }
    public float getFinX() {
        return finX;
    }
    public void setFinY(float y) {
        this.finY = y;
    }
    public float getFinY() {
        return finY;
    }

    public float distancia(float getx, float gety){

        float m = (finY-y)/(finX-x);
        float A = m;
        float B= -1;
        float C = -m*x+y;
        double distancia = Math.abs(A*getx+B*gety+C)/Math.sqrt(A*A+B*B);
        return (float) distancia;
    }
    public String toString(){
        return "{\"id\":3,\"x\"="+this.getX()+",\"y\"="+this.getY()+",\"finX\"="+this.finX+",\"finY\"="+this.getFinY()+"}";
    }

}

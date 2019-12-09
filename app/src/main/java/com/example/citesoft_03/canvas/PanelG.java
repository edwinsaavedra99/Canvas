package com.example.citesoft_03.canvas;

public class PanelG {
    private static int hight;
    private static int width;
    private PanelG(){

    }
    public static int hight(){
        return hight;
    }
    public static int width(){
        return width;
    }
    public static void setHight(int value){
        hight=value;
    }
    public static void setWidth(int value){
        width=value;
    }
}

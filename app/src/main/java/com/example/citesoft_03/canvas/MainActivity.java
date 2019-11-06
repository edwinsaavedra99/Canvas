package com.example.citesoft_03.canvas;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private float xBegin;
    private float yBeging;
    //private float radio;
//  private float corx, cory;
    private  TextView value_y;
    private Button creator_circles;
    private Button delete_circles;
    private Button creator_line;
    private Button creator_rectangle;
    private LinearLayout imagen;
    private  list_Circle lista_circulo;
    private list_Rectangle lista_rectangulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lista_circulo = new list_Circle(this);
    //    lista_rectangulo = new list_Rectangle(this);

        creator_circles = (Button) findViewById(R.id.button_circle);
        creator_line = (Button) findViewById(R.id.button_line);
        creator_rectangle = (Button) findViewById(R.id.button_rectangle);
        creator_circles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista_circulo.addCircle(50,50,30);
                lista_circulo.actualizar();
            }
        });
        creator_rectangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista_rectangulo.addRectangle(0,0,300, 500);
                lista_rectangulo.actualizar();
            }
        });

        delete_circles = (Button) findViewById(R.id.button_delete);
        delete_circles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista_circulo.deleteCicle();
                lista_circulo.actualizar();
            }
        });

        imagen = (LinearLayout) findViewById(R.id.img);
       imagen.addView(lista_circulo);
      //  imagen.addView(lista_rectangulo);



    }
    class Circle{
        private float x;
        private float y;
        private float radio;
        private Paint paint;

        public Circle(float x,float y,float radio ,Paint paint){
            this.x =x;
            this.y =y;
            this.radio =radio;
            this.paint = paint;
        }

        public void setX(float x ){ this.x =x; }
        public float getX() { return x;  }
        public void setY(float y) {  this.y = y; }
        public float getY() {   return y;   }
        public void setRadio(float radio) { this.radio = radio;  }
        public float getRadio() {   return radio;  }
        public void setPaint(Paint paint) { this.paint = paint;  }
        public Paint getPaint() {  return paint; }

    }


    class Rectangle{
        private float x;
        private float y;
        private float lado1;
        private float lado2;
        private Paint paint;

        public Rectangle(float x,float y,float lado1,float lado2 ,Paint paint){
            this.x =x;
            this.y =y;
            this.lado1 =lado1;
            this.lado2 = lado2;
            this.paint = paint;
        }

        public void setX(float x ){ this.x =x; }
        public float getX() { return x;  }
        public void setY(float y) {  this.y = y; }
        public float getY() {   return y;   }

        public void setLado1(float lado1) { this.lado1 = lado1;  }
        public float getLado1() {   return lado1;  }
        public void setLado2(float lado2) { this.lado2 = lado2;  }
        public float getLado2() {   return lado2;  }

        public void setPaint(Paint paint) { this.paint = paint;  }
        public Paint getPaint() {  return paint; }

    }


    class list_Rectangle extends View {
        int selected;
        private  ArrayList<Rectangle> mis_rectangles;
        int rectangle =  -1;
        public list_Rectangle(Context context) {
            super(context);
            mis_rectangles = new ArrayList<Rectangle>();
            this.addRectangle(0,0,300,400);
        }
        public void addRectangle(float x,float y,float lado1, float lado2){
            System.out.println(":c");
            float[] intervals = new float[]{5.0f, 5.0f};
            float phase = 0;
            DashPathEffect dashPathEffect = new DashPathEffect(intervals, phase);

            Paint pincela;

            pincela= new Paint();
            pincela.setAntiAlias(true   );
            pincela.setARGB(250, 255, 0, 0);
            pincela.setStrokeWidth(4);
            pincela.setStyle(Paint.Style.STROKE);

            pincela.setPathEffect(dashPathEffect);

            Rectangle aux = new Rectangle(x,y,lado1,lado2,pincela);

            this.mis_rectangles.add(aux);

        }
        public void deleteRectangle(int _selected){

            if(mis_rectangles.size()!=0){
                mis_rectangles.remove(_selected);
                rectangle= -1;
            }

        }

        protected void onDraw(Canvas canvas){
            for(int i =0; i< mis_rectangles.size();i++ ){
                canvas.drawRect(mis_rectangles.get(i).getX(),mis_rectangles.get(i).getY(),mis_rectangles.get(i).getLado1(),mis_rectangles.get(i).getLado2(),mis_rectangles.get(i).getPaint());
            }
        }
        public void actualizar(){
            invalidate();
        }

        public boolean onTouchEvent(MotionEvent event){
            float getx =event.getX();
            float gety= event.getY();
            int acci = event.getAction();

            if(acci == MotionEvent.ACTION_DOWN){
                for(int i =0; i<mis_rectangles.size();i++){
                    double cenx = getx-mis_rectangles.get(i).getX();
                    double ceny =  gety-mis_rectangles.get(i).getY();

                    float distancia =   (float) Math.sqrt(cenx*cenx + ceny*ceny);
                    mis_rectangles.get(i).getPaint().setARGB(250, 255, 0, 0);
                    if(distancia<= mis_rectangles.get(i).getLado1() || distancia<= mis_rectangles.get(i).getLado2()){
                        rectangle =i;
                        this.selected=i;
                        invalidate();
                    }


                }

                if(rectangle >-1){
                    this.selected = rectangle;
                    mis_rectangles.get(rectangle).getPaint().setARGB(255, 0, 255, 0);
                    mis_rectangles.get(rectangle).setX(getx);
                    mis_rectangles.get(rectangle).setY(gety);
                    invalidate();
                }
            }

            if (acci == MotionEvent.ACTION_MOVE){
                if(rectangle >-1) {
                    double cenx = getx-mis_rectangles.get(rectangle).getX();
                    double ceny =  gety-mis_rectangles.get(rectangle).getY();
                    float distancia = (float) Math.sqrt(cenx * cenx + ceny * ceny);
                    mis_rectangles.get(rectangle).setLado1(distancia);
                    mis_rectangles.get(rectangle).setLado2(distancia);
                    invalidate();
                }
            }

            return true;

        }
    }




    class list_Circle extends View {

        int selected;
        private  ArrayList<Circle> mis_circulos;

        /*
               float[]x = {50,130};
               float[]y = {50,100};
               float[] rad = {20,30};


               Paint painting [] = new Paint[2];
       */
        int circulo =  -1;

        //      String flag ="";
        //       Paint pincel1;
        public list_Circle(Context context) {
            super(context);

            mis_circulos = new ArrayList<Circle>();

            this.addCircle(50,50,20);
/*
            painting [0] = new Paint();
            painting [0].setAntiAlias(true   );
            painting [0].setARGB(150, 255, 0, 0);
            painting [0].setStrokeWidth(4);
            painting [0].setStyle(Paint.Style.STROKE);

            float[] intervals = new float[]{5.0f, 5.0f};
            float phase = 0;
            DashPathEffect dashPathEffect = new DashPathEffect(intervals, phase);

            painting [0].setPathEffect(dashPathEffect);

            painting [1] = new Paint();
            painting [1].setAntiAlias(true   );
            painting [1].setARGB(250, 255, 0, 0);
            painting [1].setStrokeWidth(4);
            painting [1].setStyle(Paint.Style.STROKE);

            painting [1].setPathEffect(dashPathEffect);
*/

        }
        public void addCircle(float x,float y,float radio){
            float[] intervals = new float[]{5.0f, 5.0f};
            float phase = 0;
            DashPathEffect dashPathEffect = new DashPathEffect(intervals, phase);

            Paint pincela;

            pincela= new Paint();
            pincela.setAntiAlias(true   );
            pincela.setARGB(250, 255, 0, 0);
            pincela.setStrokeWidth(4);
            pincela.setStyle(Paint.Style.STROKE);

            pincela.setPathEffect(dashPathEffect);

            Circle aux = new Circle(x,y,radio,pincela);

            this.mis_circulos.add(aux);

        }
        public void deleteCicle(){
            if (mis_circulos.size()!=0){
                mis_circulos.remove(this.selected);
                circulo= -1;
            }
        }

        protected void onDraw(Canvas canvas){


            for(int i =0; i< mis_circulos.size();i++ ){
                canvas.drawCircle(mis_circulos.get(i).getX(),mis_circulos.get(i).getY(),mis_circulos.get(i).getRadio(),mis_circulos.get(i).getPaint());
            }
            //canvas.drawCircle(x[0], y[0], rad[0], painting [0]);
            //canvas.drawCircle(x[1], y[1], rad[1], painting [1]);




        }
        public void actualizar(){
            invalidate();
        }

        public boolean onTouchEvent(MotionEvent event){
            float getx =event.getX();
            float gety= event.getY();
            int acci = event.getAction();

            if(acci == MotionEvent.ACTION_DOWN){
                for(int i =0; i<mis_circulos.size();i++){
                    double cenx = getx-mis_circulos.get(i).getX();
                    double ceny =  gety-mis_circulos.get(i).getY();

                    float distancia =   (float) Math.sqrt(cenx*cenx + ceny*ceny);
                    mis_circulos.get(i).getPaint().setARGB(250, 255, 0, 0);
                    if(distancia<= mis_circulos.get(i).getRadio()){
                        circulo =i;
                        this.selected=i;
                        invalidate();
                    }


                }

                if(circulo >-1){
                    this.selected = circulo;
                    mis_circulos.get(circulo).getPaint().setARGB(255, 0, 255, 0);
                    mis_circulos.get(circulo).setX(getx);
                    mis_circulos.get(circulo).setY(gety);
                    //x[circulo] = getx;
                    //y[circulo] = gety;

                    invalidate();
                }
            }

            if (acci == MotionEvent.ACTION_MOVE){
                if(circulo >-1) {
                    double cenx = getx-mis_circulos.get(circulo).getX();
                    double ceny =  gety-mis_circulos.get(circulo).getY();
                    // double cenx = getx - x[circulo];
                    //double ceny = gety - y[circulo];

                    float distancia = (float) Math.sqrt(cenx * cenx + ceny * ceny);
                    if(distancia>20)
                        mis_circulos.get(circulo).setRadio(distancia);
                    //rad[circulo] = distancia;
                    invalidate();
                }
            }

            return true;

        }
    }

}
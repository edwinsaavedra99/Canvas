package com.example.citesoft_03.canvas;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
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
    private TextView value_y;
    private Button creator_circles;
    private Button delete_circles;
    private Button creator_line;
    private Button creator_rectangle;
    private LinearLayout imagen;
    private list_Figura lista_figura;
    private float introx;
    private float introy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista_figura = new list_Figura(this);
        creator_circles = (Button) findViewById(R.id.button_circle);
        creator_line = (Button) findViewById(R.id.button_line);
        creator_rectangle = (Button) findViewById(R.id.button_rectangle);
        delete_circles = (Button) findViewById(R.id.button_delete);
        creator_circles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // lista_figura.addCirculo(50, 60, 60);
                lista_figura.addElipse(0,100,400,300);
                lista_figura.actualizar();
            }
        });
        creator_rectangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista_figura.addRectangulo(0, 100, 400, 300);
                lista_figura.actualizar();
            }
        });
        creator_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista_figura.addLinea(50, 50, 150, 400);
                lista_figura.actualizar();
            }
        });

        delete_circles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista_figura.deleteFigure();
                lista_figura.actualizar();
            }
        });
        imagen = (LinearLayout) findViewById(R.id.img);
        imagen.addView(lista_figura);
    }

    class list_Figura extends View {
        int selected;
        private ArrayList<Figura> mis_figuras;
        int figura = -1;

        public list_Figura(Context context) {
            super(context);
            mis_figuras = new ArrayList<Figura>();
        }

        public void addCirculo(float x, float y, float radio) {
            float[] intervals = new float[]{5.0f, 5.0f};
            float phase = 0;
            DashPathEffect dashPathEffect = new DashPathEffect(intervals, phase);
            Paint pincela;
            pincela = new Paint();
            pincela.setAntiAlias(true);
            pincela.setARGB(250, 255, 0, 0);
            pincela.setStrokeWidth(4);
            pincela.setStyle(Paint.Style.STROKE);
            pincela.setPathEffect(dashPathEffect);
            Circulo aux = new Circulo(x, y, radio, pincela);
            this.mis_figuras.add(aux);
        }

        public void addRectangulo(float x, float y, float lado1, float lado2) {
            float[] intervals = new float[]{5.0f, 5.0f};
            float phase = 0;
            DashPathEffect dashPathEffect = new DashPathEffect(intervals, phase);
            Paint pincela;
            pincela = new Paint();
            pincela.setAntiAlias(true);
            pincela.setARGB(250, 255, 0, 0);
            pincela.setStrokeWidth(4);
            pincela.setStyle(Paint.Style.STROKE);
            pincela.setPathEffect(dashPathEffect);
            Rectangulo aux = new Rectangulo(x, y, lado1, lado2, pincela);
            this.mis_figuras.add(aux);

        }

        public void addElipse(float x, float y, float x1, float y1) {
            float[] intervals = new float[]{5.0f, 5.0f};
            float phase = 0;
            DashPathEffect dashPathEffect = new DashPathEffect(intervals, phase);
            Paint pincela;
            pincela = new Paint();
            pincela.setAntiAlias(true);
            pincela.setARGB(250, 255, 0, 0);
            pincela.setStrokeWidth(4);
            pincela.setStyle(Paint.Style.STROKE);
            pincela.setPathEffect(dashPathEffect);
            Elipse aux = new Elipse(x, y, x1, y1, pincela);
            this.mis_figuras.add(aux);
        }

        public void addLinea(float x, float y, float x1, float y1) {
            float[] intervals = new float[]{5.0f, 5.0f};
            float phase = 0;
            DashPathEffect dashPathEffect = new DashPathEffect(intervals, phase);
            Paint pincela;
            pincela = new Paint();
            pincela.setAntiAlias(true);
            pincela.setARGB(250, 255, 0, 0);
            pincela.setStrokeWidth(4);
            pincela.setStyle(Paint.Style.STROKE);
            pincela.setPathEffect(dashPathEffect);
            Linea aux = new Linea(x, y, x1, y1, pincela);
            this.mis_figuras.add(aux);
        }

        public void deleteFigure() {
            if (mis_figuras.size() != 0 && this.selected != -1) {
                mis_figuras.remove(this.selected);
                figura = -1;
            }
        }

        protected void onDraw(Canvas canvas) {
            for (int i = 0; i < mis_figuras.size(); i++) {

                if (mis_figuras.get(i) instanceof Circulo) {
                    Circulo temp = (Circulo) mis_figuras.get(i);
                    canvas.drawCircle(temp.getX(), temp.getY(), temp.getRadio(), temp.getPaint());
                } else if (mis_figuras.get(i) instanceof Rectangulo) {
                    Rectangulo temp = (Rectangulo) mis_figuras.get(i);
                    canvas.drawRect(temp.getX(), temp.getY(), temp.getX1(), temp.getY1(), temp.getPaint());
                } else if (mis_figuras.get(i) instanceof Linea) {
                    Linea temp = (Linea) mis_figuras.get(i);
                    canvas.drawLine(temp.getX(), temp.getY(), temp.getFinX(), temp.getFinY(), temp.getPaint());
                } else if (mis_figuras.get(i) instanceof Elipse) {
                    Elipse temp = (Elipse) mis_figuras.get(i);
                    RectF rectangulo1 = new RectF(temp.getX(), temp.getY(), temp.getX1(), temp.getY1());
                    canvas.drawOval(rectangulo1, temp.getPaint());
                } else {
                    System.out.println("Esperando Tipos");
                }


            }
        }

        public void actualizar() {
            invalidate();
        }

        public boolean onTouchEvent(MotionEvent event) {
            float getx = event.getX();
            float gety = event.getY();
            int acci = event.getAction();
            if (acci == MotionEvent.ACTION_DOWN) {
                for (int i = 0; i < mis_figuras.size(); i++) {
                    mis_figuras.get(i).getPaint().setARGB(250, 255, 0, 0);
                    if (mis_figuras.get(i) instanceof Circulo) {
                        Circulo temp = (Circulo) mis_figuras.get(i);
                        double cenx = getx - mis_figuras.get(i).getX();
                        double ceny = gety - mis_figuras.get(i).getY();
                        float distancia = (float) Math.sqrt(cenx * cenx + ceny * ceny);
                        if (distancia <= temp.getRadio()) {
                            System.out.println("entro en condicional de circulo");
                            figura = i;
                            this.selected = i;
                            invalidate();
                        }
                    } else if (mis_figuras.get(i) instanceof Rectangulo) {
                        Rectangulo temp = (Rectangulo) mis_figuras.get(i);
                        if (getx <= temp.getX1() && getx >= temp.getX() && gety >= temp.getY() && gety <= temp.getY1()) {
                            System.out.println("entro en condicional de rectangulo");
                            figura = i;
                            this.selected = i;
                            invalidate();
                        }
                    } else if (mis_figuras.get(i) instanceof Linea) {
                        //Canvas sd = new Canvas();
                        Linea temp = (Linea) mis_figuras.get(i);
                        //sd.drawCircle(50,50,40,temp.getPaint());
                        // mis_figuras.add(new Circulo(temp.getX(),temp.getY(),40,temp.getPaint()));
                        double cenx = getx - temp.getX();
                        double ceny = gety - temp.getY();
                        float mx = (temp.getX() + temp.getFinX()) / 2;
                        float my = (temp.getY() + temp.getFinY()) / 2;
                        double cx = getx - mx;
                        double cy = gety - my;
                        float distancia2 = (float) Math.sqrt(cx * cx + cy * cy);
                        float distancia = (float) Math.sqrt(cenx * cenx + ceny * ceny);
                        double cenx1 = getx - temp.getFinX();
                        double ceny1 = gety - temp.getFinY();
                        float distancia1 = (float) Math.sqrt(cenx1 * cenx1 + ceny1 * ceny1);
                        if (temp.distancia(getx, gety) <= 20) {
                            //if(distancia2<=30){
                            //dibujar punto de accion
                            figura = i;
                            this.selected = i;
                            invalidate();
                        }
                        //add canvas
                    } else if (mis_figuras.get(i) instanceof Elipse) {
                        Elipse temp = (Elipse) mis_figuras.get(i);
                        if (getx <= temp.getX1() && getx >= temp.getX() && gety >= temp.getY() && gety <= temp.getY1()) {
                            figura = i;
                            this.selected = i;
                            invalidate();
                        }
                    }
                    if (figura > -1) {
                        this.selected = figura;
                        mis_figuras.get(figura).getPaint().setARGB(255, 0, 255, 0);
                        invalidate();
                    } else {
                        for (int t = 0; t < mis_figuras.size(); t++) {
                            mis_figuras.get(t).getPaint().setARGB(250, 255, 0, 0);
                        }
                    }
                }
            }
                if (acci == MotionEvent.ACTION_MOVE) {
                    if (figura > -1) {
                        if (mis_figuras.get(figura) instanceof Circulo) {
                            double cenx = getx - mis_figuras.get(figura).getX();
                            double ceny = gety - mis_figuras.get(figura).getY();
                            float distancia = (float) Math.sqrt(cenx * cenx + ceny * ceny);
                            Circulo aux = (Circulo) mis_figuras.get(figura);
                            mis_figuras.get(figura).setX(getx);
                            mis_figuras.get(figura).setY(gety);


                        } else if (mis_figuras.get(figura) instanceof Rectangulo) {
                            Rectangulo aux = (Rectangulo) mis_figuras.get(figura);
                            //implementado SECCION DE REDIX
                            double cenx = getx - aux.getX();
                            double ceny = gety - aux.getY();
                            float distancia = (float) Math.sqrt(cenx * cenx + ceny * ceny);
                            double cenx1 = getx - aux.getX1();
                            double ceny1 = gety - aux.getY1();
                            float mx = (aux.getX() + aux.getX1()) / 2;
                            float my = (aux.getY() + aux.getY1()) / 2;
                            double cx = getx - mx;
                            double cy = gety - my;
                            float distancia2 = (float) Math.sqrt(cx * cx + cy * cy);
                            float largo = (aux.getX1() - aux.getX()) / 2;
                            float ancho = (aux.getY() - aux.getY1()) / 2;
                            float minimo;
                            if (largo <= ancho)
                                minimo = largo;
                            else
                                minimo = ancho;
                            float distancia1 = (float) Math.sqrt(cenx1 * cenx1 + ceny1 * ceny1);
                            if (distancia <= 60) {
                                aux.setX(getx);
                                aux.setY(gety);
                            } else if (distancia1 <= 60) {
                                aux.setX1(getx);
                                aux.setY1(gety);
                            //} else if (distancia2 <= minimo * 2 / 3) {
                            } else if (distancia2 <= largo*2/3 || distancia2<= ancho*2/3) {
                                aux.setX1(getx + largo);
                                aux.setY1(gety - ancho);
                                aux.setX(getx - largo);
                                aux.setY(gety + ancho);
                            }
                        } else if (mis_figuras.get(figura) instanceof Linea) {
                            Linea temp = (Linea) mis_figuras.get(figura);
                            double cenx = getx - temp.getX();
                            double ceny = gety - temp.getY();
                            float distancia = (float) Math.sqrt(cenx * cenx + ceny * ceny);
                            double cenx1 = getx - temp.getFinX();
                            double ceny1 = gety - temp.getFinY();

                            float mx = (temp.getX() + temp.getFinX()) / 2;
                            float my = (temp.getY() + temp.getFinY()) / 2;
                            double cx = getx - mx;
                            double cy = gety - my;
                            float distancia2 = (float) Math.sqrt(cx * cx + cy * cy);
                            float distancia1 = (float) Math.sqrt(cenx1 * cenx1 + ceny1 * ceny1);
                            if (distancia <= 40) {
                                temp.setX(getx);
                                temp.setY(gety);
                            } else if (distancia1 <= 40) {
                                temp.setFinX(getx);
                                temp.setFinY(gety);
                            } else if (distancia2 <= 30) {
                                float largo = (temp.getFinX() - temp.getX()) / 2;
                                float ancho = (temp.getY() - temp.getFinY()) / 2;
                                temp.setFinX(getx + largo);
                                temp.setFinY(gety - ancho);
                                temp.setX(getx - largo);
                                temp.setY(gety + ancho);
                            }
                            //add canvas
                        } else if (mis_figuras.get(figura) instanceof Elipse) {
                            Elipse aux = (Elipse) mis_figuras.get(figura);
                            //implementado SECCION DE REDIX
                            double cenx = getx - aux.getX();
                            double ceny = gety - aux.getY();
                            float distancia = (float) Math.sqrt(cenx * cenx + ceny * ceny);
                            double cenx1 = getx - aux.getX1();
                            double ceny1 = gety - aux.getY1();
                            float mx = (aux.getX() + aux.getX1()) / 2;
                            float my = (aux.getY() + aux.getY1()) / 2;
                            double cx = getx - mx;
                            double cy = gety - my;
                            float distancia2 = (float) Math.sqrt(cx * cx + cy * cy);
                            float largo = (aux.getX1() - aux.getX()) / 2;
                            float ancho = (aux.getY() - aux.getY1()) / 2;
                            float minimo;
                            if (largo <= ancho)
                                minimo = largo;
                            else
                                minimo = ancho;
                            float distancia1 = (float) Math.sqrt(cenx1 * cenx1 + ceny1 * ceny1);
                            if (distancia <= 60) {
                                aux.setX(getx);
                                aux.setY(gety);
                            } else if (distancia1 <= 60) {
                                aux.setX1(getx);
                                aux.setY1(gety);
                                //} else if (distancia2 <= minimo * 2 / 3) {
                            } else if (distancia2 <= largo*2/3 || distancia2<= ancho*2/3) {
                                aux.setX1(getx + largo);
                                aux.setY1(gety - ancho);
                                aux.setX(getx - largo);
                                aux.setY(gety + ancho);
                            }
                            //add canvas
                        } else {
                            System.out.println("TIPO NO RECONOCIDO");
                        }


                    }
                    invalidate();

                }
            return true;
            }

        }
}
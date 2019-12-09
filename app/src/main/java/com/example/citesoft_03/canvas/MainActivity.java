package com.example.citesoft_03.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
//LIMITES
//ARREGLAR CAMBIO DE SELECCION
//PUNTEROS
//

public class MainActivity extends AppCompatActivity {

    private float xBegin;
    private float yBeging;
    String archivo;
    String carpeta;
    File file;
    private TextView value_y;
    private Button creator_circles;
    private Button delete_circles;
    private Button creator_line;
    private Button creator_rectangle;
    private Button paleta_blue;
    private Button paleta_green;
    private Button paleta_red;
    private Button paleta_celeste;
    private Button save_1;
    private Button load_1;

    private LinearLayout imagen;
    private list_Figura lista_figura;
    private int color[]={183,149,11};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista_figura = new list_Figura(this);
        creator_circles = (Button) findViewById(R.id.button_circle);
        creator_line = (Button) findViewById(R.id.button_line);
        creator_rectangle = (Button) findViewById(R.id.button_rectangle);
        delete_circles = (Button) findViewById(R.id.button_delete);
        paleta_blue = (Button) findViewById(R.id.paleta_blue);
        paleta_green = (Button) findViewById(R.id.paleta_green);
        paleta_red = (Button) findViewById(R.id.paleta_red);
        paleta_celeste = (Button) findViewById(R.id.paleta_celeste);
        save_1 = (Button) findViewById(R.id.button_save);
        load_1 =(Button) findViewById(R.id.button_load);
        archivo = "miarchivo";
        carpeta = "/carpeta/";

        crear_archivo_json();



        creator_circles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista_figura.addCirculo(100,100,100);
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

//botones de carga para todo :) puede haber error
        save_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //codigo de color azul
                escribir_archivo_json();

            }
        });
        load_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //codigo de color azul

            }
        });




        paleta_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //codigo de color azul
                color=Util.color2;
                lista_figura.cambiarColor();
                lista_figura.actualizar();
            }
        });

        paleta_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //codigo de color verde
                color=Util.color3;
                lista_figura.cambiarColor();
                lista_figura.actualizar();
            }
        });

        paleta_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //codigo de color rojo
                color=Util.color4;
                lista_figura.cambiarColor();
                lista_figura.actualizar();
            }
        });

        paleta_celeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //codigo de color celeste
                color=Util.color7;
                lista_figura.cambiarColor();
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
            float[] intervals = new float[]{0.0f, 0.0f};
            float phase = 0;
            DashPathEffect dashPathEffect = new DashPathEffect(intervals, phase);
            Paint pincela;
            pincela = new Paint();
            pincela.setAntiAlias(true);
            pincela.setARGB(250, color[0],color[1],color[2]);
            pincela.setStrokeWidth(4);
            pincela.setStyle(Paint.Style.STROKE);
            pincela.setPathEffect(dashPathEffect);
            Circulo aux = new Circulo(x, y, radio, pincela,color);
            this.mis_figuras.add(aux);
        }

        public void addRectangulo(float x, float y, float lado1, float lado2) {
            float[] intervals = new float[]{0.0f, 0.0f};
            float phase = 0;
            DashPathEffect dashPathEffect = new DashPathEffect(intervals, phase);
            Paint pincela;
            pincela = new Paint();
            pincela.setAntiAlias(true);
            pincela.setARGB(250, color[0],color[1],color[2]);
            pincela.setStrokeWidth(4);
            pincela.setStyle(Paint.Style.STROKE);
            pincela.setPathEffect(dashPathEffect);
            Rectangulo aux = new Rectangulo(x, y, lado1, lado2, pincela,color);
            this.mis_figuras.add(aux);

        }

        public void addElipse(float x, float y, float x1, float y1) {
            float[] intervals = new float[]{0.0f, 0.0f};
            float phase = 0;
            DashPathEffect dashPathEffect = new DashPathEffect(intervals, phase);
            Paint pincela;
            pincela = new Paint();
            pincela.setAntiAlias(true);
            pincela.setARGB(250, color[0],color[1],color[2]);
            pincela.setStrokeWidth(4);
            pincela.setStyle(Paint.Style.STROKE);
            pincela.setPathEffect(dashPathEffect);
            Elipse aux = new Elipse(x, y, x1, y1, pincela,color);
            this.mis_figuras.add(aux);
        }

        public void addLinea(float x, float y, float x1, float y1) {
            float[] intervals = new float[]{0.0f, 0.0f};
            float phase = 0;
            DashPathEffect dashPathEffect = new DashPathEffect(intervals, phase);
            Paint pincela;
            pincela = new Paint();
            pincela.setAntiAlias(true);
            pincela.setARGB(250, color[0],color[1],color[2]);
            pincela.setStrokeWidth(4);
            pincela.setStyle(Paint.Style.STROKE);
            pincela.setPathEffect(dashPathEffect);
            Linea aux = new Linea(x, y, x1, y1, pincela,color);
            this.mis_figuras.add(aux);
        }

        public void deleteFigure() {
            if (mis_figuras.size() != 0 && this.selected != -1 ) {
                mis_figuras.remove(this.selected);
                figura = -1;
            }
        }
        public void cambiarColor() {
            if (mis_figuras.size() != 0 && this.selected != -1) {
                mis_figuras.get(this.selected).setColor(color);
                mis_figuras.get(this.selected).getPaint().setARGB(255,color[0],color[1],color[2]);
                invalidate();
            }
        }
        public String toString(){
            String list_json = "[\n";
            for(int i=0;i<this.mis_figuras.size();i++){
                list_json = list_json+mis_figuras.get(i).toString()+"\n";
                if(i<this.mis_figuras.size()-1){
                    list_json = list_json+",";
                }
            }

            return list_json+"]";
        }
        public void clear_list(){
            this.mis_figuras.clear();
        }

        protected void onDraw(Canvas canvas) {
            for (int i = 0; i < mis_figuras.size(); i++) {
                if (mis_figuras.get(i) instanceof Circulo) {
                    Circulo temp = (Circulo) mis_figuras.get(i);
                    canvas.drawCircle(temp.getX(), temp.getY(), temp.getRadio(), temp.getPaint());
                    canvas.drawCircle(temp.getX()+temp.getRadio(), temp.getY(), 10, Util.Circle_Small(temp.getColor()));
                    if(i==figura){
                        canvas.drawCircle(temp.getX()+temp.getRadio(), temp.getY(), 30, Util.Circle_Transparente(temp.getColor()));
                    }

                } else if (mis_figuras.get(i) instanceof Rectangulo) {
                    Rectangulo temp = (Rectangulo) mis_figuras.get(i);
                    canvas.drawRect(temp.getX(), temp.getY(), temp.getX1(), temp.getY1(), temp.getPaint());

                    canvas.drawCircle(temp.getX(), temp.getY(), 10,  Util.Circle_Small(temp.getColor()));
                    canvas.drawCircle(temp.getX1(), temp.getY1(), 10,  Util.Circle_Small(temp.getColor()));


                    if(i==figura){
                        canvas.drawCircle(temp.getX(), temp.getY(), 30, Util.Circle_Transparente(temp.getColor()));
                        canvas.drawCircle(temp.getX1(), temp.getY1(), 30, Util.Circle_Transparente(temp.getColor()));
                    }


                } else if (mis_figuras.get(i) instanceof Linea) {
                    Linea temp = (Linea) mis_figuras.get(i);
                    canvas.drawLine(temp.getX(), temp.getY(), temp.getFinX(), temp.getFinY(), temp.getPaint());

                    canvas.drawCircle(temp.getX(), temp.getY(), 10,  Util.Circle_Small(temp.getColor()));
                    canvas.drawCircle(temp.getFinX(), temp.getFinY(), 10,  Util.Circle_Small(temp.getColor()));


                    if(i==figura){
                        canvas.drawCircle(temp.getX(), temp.getY(), 30, Util.Circle_Transparente(temp.getColor()));
                        canvas.drawCircle(temp.getFinX(), temp.getFinY(), 30, Util.Circle_Transparente(temp.getColor()));
                    }


                } else if (mis_figuras.get(i) instanceof Elipse) {
                    Elipse temp = (Elipse) mis_figuras.get(i);
                    RectF rectangulo1 = new RectF(temp.getX(), temp.getY(), temp.getX1(), temp.getY1());
                    canvas.drawOval (rectangulo1, temp.getPaint());

                    canvas.drawCircle(temp.getX(), temp.getY(), 10, Util.Circle_Small(temp.getColor()));
                    canvas.drawCircle(temp.getX1(), temp.getY1(), 10, Util.Circle_Small(temp.getColor()));


                    if(i==figura){
                        canvas.drawCircle(temp.getX(), temp.getY(), 30, Util.Circle_Transparente(temp.getColor()));
                        canvas.drawCircle(temp.getX1(), temp.getY1(), 30, Util.Circle_Transparente(temp.getColor()));
                    }
                } else {
                    System.out.println("Esperando Tipos");
                }
            }
        }

        public void actualizar() {
            invalidate();
        }

        float getx_pasado = 0;
        float gety_pasado = 0;
        public boolean onTouchEvent(MotionEvent event) {
            float getx = event.getX();
            float gety = event.getY();

            int acci = event.getAction();
            if (acci == MotionEvent.ACTION_DOWN) {
                getx_pasado=getx;
                gety_pasado=gety;
                for (int i = 0; i < mis_figuras.size(); i++) {
                    //mis_figuras.get(i).getPaint().setStrokeWidth(4);
                    if (mis_figuras.get(i) instanceof Circulo) {
                        Circulo temp = (Circulo) mis_figuras.get(i);
                        double cenx = getx - mis_figuras.get(i).getX();
                        double ceny = gety - mis_figuras.get(i).getY();
                        float distancia = (float) Math.sqrt(cenx * cenx + ceny * ceny);
                        if (distancia <= temp.getRadio()) {
                            figura = i;
                            this.selected = i;
                            invalidate();
                        }
                    } else if (mis_figuras.get(i) instanceof Rectangulo) {
                        Rectangulo temp = (Rectangulo) mis_figuras.get(i);
                        if (getx <= temp.getX1() && getx >= temp.getX() && gety >= temp.getY() && gety <= temp.getY1()) {
                            figura = i;
                            this.selected = i;
                            invalidate();
                        }
                    } else if (mis_figuras.get(i) instanceof Linea) {
                        Linea temp = (Linea) mis_figuras.get(i);
                        if (temp.distancia(getx, gety) <= 20) {
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
                        //mis_figuras.get(figura).getPaint().setStrokeWidth(9);
                    }
                }
            }
                if (acci == MotionEvent.ACTION_MOVE) {
                    if (figura > -1) {
                        if (mis_figuras.get(figura) instanceof Circulo) {
                            Circulo temp=(Circulo)mis_figuras.get(figura);
                            float cenx = getx - (temp.getX()+temp.getRadio());
                            float ceny = gety - temp.getY();
                            float distancia = (float) Math.sqrt(cenx * cenx + ceny * ceny);
                            float cenx1 = getx - temp.getX();
                            float ceny1 = gety - temp.getY();
                            float distancia2 = (float) Math.sqrt(cenx1 * cenx1 + ceny1 * ceny1);
                            if (distancia <= 40) {
                                temp.setRadio(temp.getRadio()-(getx_pasado-getx));
                            } else if (distancia2 <= temp.getRadio()-40) {
                                temp.setX(temp.getX()-(getx_pasado-getx));
                                temp.setY(temp.getY()-(gety_pasado-gety));
                            }
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
                        } else {File file;
                            System.out.println("TIPO NO RECONOCIDO");
                        }


                    }

                    getx_pasado=getx;
                    gety_pasado=gety;
                    invalidate();
                }
            return true;
            }

        }
        public void crear_archivo_json(){
            String file_path= (Environment.getExternalStorageDirectory() +  carpeta );
            File localFile = new File(file_path);

            if(!localFile.exists()){
                localFile.mkdir();
                System.out.println("hollaaaa");
            }
            this.file = new File(localFile,archivo);
            try{
                file.createNewFile();

            }catch (Exception e){
                e.printStackTrace();
            }


        }
        public void escribir_archivo_json(){
            FileWriter fileWriter=null;
            PrintWriter printWriter = null;
            try{
                fileWriter = new FileWriter(file);
                printWriter = new PrintWriter(fileWriter);
                printWriter.print(lista_figura.toString());
                printWriter.flush();
                printWriter.close();
            }catch (Exception e){

            }
        }
/*
    public void readJson() {
        String jsonString = IOHelper.stringFromAsset(this, "circles.json");
        try {
            //JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray circulos_saved = new JSONArray(jsonString);

            String result = "";
            for (int i = 0; i < circulos_saved.length(); i++) {
                JSONObject circ = circulos_saved.getJSONObject(i);
                //new Gson().fromJson(city.toString(), City.class);

                this.lista_figura.addCircle(circ.getLong("x"),circ.getLong("y"),circ.getLong("radio"));
            }
            this.lista_figura.actualizar();
        } catch (Exception e) {
            System.out.print("dooooo");
        }
    }
*/
    public void writeJson() {
        IOHelper.writeToFile(this, "circleJsonObj.txt", this.lista_figura.toString());
    }
}
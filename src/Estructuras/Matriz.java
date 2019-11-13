/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import static Estructuras.nodoM.ax;
import static Estructuras.nodoM.ay;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import proyecto.pkg2.edd.Carpeta;

/**
 *
 * @author Luis
 */
class nodoM{
    public nodoM ant,sig,arriba,abajo;
    public static int ax=-1,ay=-1;
    public int x,y;
    public Carpeta carpeta;
    public nodoM padre;
    public ArbolAVL arbol;
    public nodoM(Carpeta carpeta,nodoM padre){
        ant = null;
        sig = null;
        arriba = null;
        abajo = null;
        x=ax;
        y=ay;
        this.carpeta= carpeta;
        this.padre= padre;
        arbol=new ArbolAVL();
    }
    
    public nodoM(Carpeta carpeta,nodoM padre,int x,int y){
        ant = null;
        sig = null;
        arriba = null;
        abajo = null;
        this.x=x;
        this.y=y;
        this.carpeta= carpeta;
        this.padre= padre;
        arbol=new ArbolAVL();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
public class Matriz {
    public nodoM raiz;

    public Matriz() {
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        String time = "Hora y fecha: " + hourdateFormat.format(date);
        raiz = new nodoM(new Carpeta("raiz",time),null);
        raiz.carpeta.ruta="/";
        this.crear_columna(0,"/");
        this.crear_fila(0,"/");
    }
    
    public nodoM buscar_fila(int y){
        nodoM tem = raiz;
        while(tem!= null){
            if(tem.y==y){
                return tem;
            }
            tem=tem.abajo;
        }
        return null;
    }
    
    public int buscar_fila(String y){
        nodoM tem = raiz;
        while(tem!= null){
            if(tem.carpeta.nombre.equals(y)){
                System.out.println("regresa esto");
                return tem.y;
            }
            tem=tem.abajo;
        }
        return 0;
    }
    
    public nodoM buscar_columna(int x){
        nodoM tem = raiz;
        while(tem!= null){
            if(tem.x==x){
                return tem;
            }
            tem=tem.sig;
        }
        return null;
    }
    public nodoM insertar_ordenado_columna(nodoM nuevo,nodoM cabeza_c){
        nodoM tem = cabeza_c;
        boolean bandera= false;
        while(true){
            if(tem.x == nuevo.x){
                tem.y = nuevo.y;
                tem.carpeta.nombre = nuevo.carpeta.nombre;
                return tem;
            }else if(tem.x>nuevo.x){
                bandera = true;
                break;
            }
            if(tem.sig!=null){
                tem=tem.sig;
            }else{
                break;
            }
        }
        if(bandera){
            nuevo.sig = tem;
            tem.ant.sig = nuevo;
            nuevo.ant= tem.ant;
            tem.ant= nuevo; 
        }else{
            tem.sig = nuevo;
            nuevo.ant = tem;
        }
        return nuevo;
    }
    
    public nodoM insertar_ordenado_fila(nodoM nuevo,nodoM cabeza_f){
        nodoM tem = cabeza_f;
        boolean bandera= false;
        while(true){
            if(tem.y == nuevo.y){
                tem.x = nuevo.x;
                tem.carpeta.nombre = nuevo.carpeta.nombre;
                return tem;
            }else if(tem.y>nuevo.y){
                bandera = true;
                break;
            }
            if(tem.abajo!=null){
                tem=tem.abajo;
            }else{
                break;
            }
        }
        if(bandera){
            nuevo.abajo = tem;
            tem.arriba.abajo = nuevo;
            nuevo.arriba= tem.arriba;
            tem.arriba= nuevo; 
        }else{
            tem.abajo = nuevo;
            nuevo.arriba = tem;
        }
        return nuevo;
    }
    
    public nodoM crear_columna(int x,String nombre){
        nodoM cabeza_c = raiz;
        ax+=1;
        nodoM columna = this.insertar_ordenado_columna(new nodoM(new Carpeta(nombre,"time"),null,ax,-1), cabeza_c);
        return columna;
    }
    
    public nodoM crear_fila(int y,String nombre){
        nodoM cabeza_f = raiz;
        ay+=1;
        nodoM fila = this.insertar_ordenado_fila(new nodoM(new Carpeta(nombre,"time"),null,-1,ay), cabeza_f);
        return fila;
    }
    
    public void insertar(Carpeta carpeta,int p){
        nodoM nodoF = this.buscar_fila(p);
        nodoM nuevo = new nodoM(carpeta,nodoF);
        //nodoM nodoC = this.buscar_columna(nuevo.x);
        
        /*if(nodoC==null && nodoF==null){
            nodoC = this.crear_columna(nuevo.x);
            nodoF = this.crear_fila(nuevo.x);
            nuevo = this.insertar_ordenado_columna(nuevo, nodoF);
            nuevo = this.insertar_ordenado_fila(nuevo, nodoC);
            return;
        }else 
        if(nodoC==null && nodoF!= null){
            //Creando cabeceras
            nodoC = this.crear_columna(nuevo.x,carpeta.nombre);
            this.crear_fila(nuevo.y,carpeta.nombre);
            nuevo = this.insertar_ordenado_columna(nuevo, nodoF);
            nuevo = this.insertar_ordenado_fila(nuevo, nodoC);
            return;
        }else if(nodoC!= null && nodoF==null){
            nodoF = this.crear_fila(y);
            nuevo = this.insertar_ordenado_columna(nuevo, nodoF);
            nuevo = this.insertar_ordenado_fila(nuevo, nodoC);
            return;
        }else if(nodoC!=null && nodoF!=null){
            nuevo = this.insertar_ordenado_columna(nuevo, nodoF);
            nuevo = this.insertar_ordenado_fila(nuevo, nodoC);
        }*/
        nodoM nodoC = this.crear_columna(nuevo.x,carpeta.nombre);
        this.crear_fila(nuevo.y,carpeta.nombre);
        System.out.println(nuevo.x);
        System.out.println(nodoF.x);
        nuevo = this.insertar_ordenado_columna(nuevo, nodoF);
        nuevo = this.insertar_ordenado_fila(nuevo, nodoC);
        if (nodoF.carpeta.nombre.equals("/")) {
            nuevo.carpeta.ruta=nodoF.carpeta.nombre+nuevo.carpeta.nombre;
        }else{
            nuevo.carpeta.ruta=nodoF.carpeta.nombre+"/"+nuevo.carpeta.nombre;
        }
        
    }
    
    public void mostrar(){
        nodoM tem = raiz;
        nodoM tem2 = raiz;
        while(tem2!=null){
            while(tem!=null){
                System.out.print("->"+tem.carpeta.nombre);
                tem=tem.sig;
            }
            tem2=tem2.abajo;
            tem= tem2;
            System.out.println(" ");
        }
    }
    
    public String nombresC(String padre){
        nodoM tem = raiz;
        nodoM tem2 = raiz;
        String supre="";
        while(tem2!=null){
            while(tem!=null){
                if (tem2.carpeta.nombre.equals(padre)&&tem.x!=-1) {
                    supre=supre+tem.carpeta.nombre+";";
                }
                tem=tem.sig;
            }
            tem2=tem2.abajo;
            tem= tem2;
        }
        //System.out.println("esto");
        //System.out.println(supre);
        return supre;
    }
    
    public void borrar(String padre){
        int f=buscar_fila(padre);
        nodoM cabeza=buscar_fila(f);
        nodoM tem= cabeza.sig;
        while(tem!=null){
            if (tem.arriba.sig!=null) {
                tem.arriba.ant.sig=tem.arriba.sig;
                tem.arriba.sig.ant=tem.arriba.ant;
                nodoM auxF=buscar_fila(tem.arriba.x);
                if (auxF.abajo!=null) {
                    auxF.arriba.abajo=auxF.abajo;
                    auxF.abajo.arriba=auxF.arriba;
                }else{
                    auxF.arriba.abajo=null;
                }
            }else{
                tem.arriba.ant.sig=null;
                nodoM auxF=buscar_fila(tem.arriba.x);
                if (auxF.abajo!=null) {
                    auxF.arriba.abajo=auxF.abajo;
                    auxF.abajo.arriba=auxF.arriba;
                }else{
                    auxF.arriba.abajo=null;
                }
            }
            tem=tem.sig;
        }
        if (cabeza.abajo!=null) {
            cabeza.arriba.abajo=cabeza.abajo;
            cabeza.abajo.arriba=cabeza.arriba;
        }else{
            cabeza.arriba.abajo=null;
        }
        nodoM cabezaC=buscar_columna(f);
        if (cabezaC.abajo.sig!=null) {
            cabezaC.abajo.ant.sig=cabezaC.abajo.sig;
            cabezaC.abajo.sig.ant=cabezaC.abajo.ant;
        }else{
            cabezaC.abajo.ant.sig=null;
        }
        if (cabezaC.sig!=null) {
            cabezaC.ant.sig=cabezaC.sig;
            cabezaC.sig.ant=cabezaC.ant;
        }else{
            cabezaC.ant.sig=null;
        }
        mostrar();
    }
    public  void modificar(String padre,String nuevo){
        int f=buscar_fila(padre);
        nodoM cabeza=buscar_fila(f);
        nodoM cabezaC=buscar_columna(f);
        cabeza.carpeta.nombre=nuevo;
        cabezaC.carpeta.nombre=nuevo;
        cabezaC.abajo.carpeta.nombre=nuevo;
        mostrar();
    }
    public void agregarAVL(String nombre,String contenido, String extension, String time,nodoM padre){
        padre.arbol.insertar(nombre, contenido, extension, time);
    }
    
    public String nombresA(nodoM padre){
        return padre.arbol.nombresA(padre.arbol.raiz);
    }
    public void borrar(nodoM padre,String nombre){
        System.out.println(padre.arbol.eliminar(nombre));
    }
    
    public String contenidoA(nodoM padre,String nombre){
        return padre.arbol.contenido(padre.arbol.raiz, nombre);
    }
}

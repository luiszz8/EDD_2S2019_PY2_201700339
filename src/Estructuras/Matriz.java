/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import proyecto.pkg2.edd.Carpeta;

/**
 *
 * @author Luis
 */
class nodoM{
    nodoM ant,sig,arriba,abajo;
    int x,y;
    Carpeta carpeta;
    public nodoM(int x, int y,Carpeta carpeta){
        ant = null;
        sig = null;
        arriba = null;
        abajo = null;
        this.x=x;
        this.y=y;
        this.carpeta= carpeta;
    }
}
public class Matriz {
    nodoM raiz;

    public Matriz() {
        raiz = new nodoM(-1,-1,new Carpeta("raiz","time"));
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
    
    public nodoM crear_columna(int x){
        nodoM cabeza_c = raiz;
        nodoM columna = this.insertar_ordenado_columna(new nodoM(x,-1,new Carpeta("Col","time")), cabeza_c);
        return columna;
    }
    
    public nodoM crear_fila(int y){
        nodoM cabeza_f = raiz;
        nodoM fila = this.insertar_ordenado_fila(new nodoM(-1,y,new Carpeta("Fil","time")), cabeza_f);
        return fila;
    }
    
    public void insertar(int x,int y,Carpeta carpeta){
        nodoM nuevo = new nodoM(x,y,carpeta);
        nodoM nodoC = this.buscar_columna(x);
        nodoM nodoF = this.buscar_fila(y);
        if(nodoC==null && nodoF==null){
            nodoC = this.crear_columna(x);
            nodoF = this.crear_fila(y);
            nuevo = this.insertar_ordenado_columna(nuevo, nodoF);
            nuevo = this.insertar_ordenado_fila(nuevo, nodoC);
            return;
        }else if(nodoC==null && nodoF!= null){
            nodoC = this.buscar_columna(x);
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
        }
    }
}

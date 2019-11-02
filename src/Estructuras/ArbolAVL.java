/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import proyecto.pkg2.edd.Archivo;

/**
 *
 * @author Luis
 */
class nodoA{
    public Archivo contenido;
    public nodoA izq, der;
    public int fe;
    public nodoA(Archivo contenido){
        this.contenido = contenido;
        this.izq = null;
        this.der = null;
        this.fe = 0;
    }
}
public class ArbolAVL {
    public nodoA raiz;
    public int tamayo;
    public ArbolAVL(){
        this.raiz = null;
        this.tamayo = 0;
    }
    
    public int obtenerAltura(nodoA nodo){
        if(nodo == null){
            return -1;
        }else{
            return nodo.fe;
        }
    }
    
    public nodoA rotar_izq(nodoA nodo){
        nodoA aux = nodo.izq;
        nodo.izq = aux.der;
        aux.der = nodo;
        if(obtenerAltura(nodo.izq) >= obtenerAltura(nodo.der)){
            nodo.fe = obtenerAltura(nodo.izq) + 1;
        }else{
            nodo.fe = obtenerAltura(nodo.der) + 1;
        }
        if(obtenerAltura(aux.izq) >= obtenerAltura(aux.der)){
            nodo.fe = obtenerAltura(aux.izq) + 1;
        }else{
            nodo.fe = obtenerAltura(aux.der) + 1;
        }
        return aux;
    }
    
    public nodoA rotar_der(nodoA nodo){
        nodoA aux = nodo.der;
        nodo.der = aux.izq;
        aux.izq = nodo;
        if(obtenerAltura(nodo.izq) >= obtenerAltura(nodo.der)){
            nodo.fe = obtenerAltura(nodo.izq) + 1;
        }else{
            nodo.fe = obtenerAltura(nodo.der) + 1;
        }
        if(obtenerAltura(aux.izq) >= obtenerAltura(aux.der)){
            nodo.fe = obtenerAltura(aux.izq) + 1;
        }else{
            nodo.fe = obtenerAltura(aux.der) + 1;
        }
        return aux;
    }
    
    public nodoA rotar_doble_izq(nodoA nodo){
        nodo.izq = rotar_der(nodo.izq);
        nodoA tem = rotar_izq(nodo);
        return tem;
    }
    
    public nodoA rotar_doble_der(nodoA nodo){
        nodo.der = rotar_izq(nodo.der);
        nodoA tem = rotar_der(nodo);
        return tem;
    }
    
    public boolean empty(){
        if (raiz==null){
            return true;
        }else{
            return false;
        }
    }
    
    public void insertar(String nombre,String contenido, String extension, String time){
        nodoA nuevo = new nodoA(new Archivo(nombre,contenido,extension,time));
        if(raiz == null){
            raiz = nuevo;
        }else{
            raiz = insertarAVL(nuevo,raiz);
        }
    }
    
    public nodoA insertarAVL(nodoA nuevo, nodoA sub){
        nodoA padre = sub;
        if(nuevo.contenido.nombre.compareTo(sub.contenido.nombre)<0){
            if(sub.izq == null){
                sub.izq = nuevo;
            }else{
                sub.izq = insertarAVL(nuevo, sub.izq);
                if(obtenerAltura(sub.izq)-obtenerAltura(sub.der)==2){
                    if(nuevo.contenido.nombre.compareTo(sub.izq.contenido.nombre)<0){
                        padre = rotar_izq(sub);
                    }else{
                        padre = rotar_doble_izq(sub);
                    }
                }
            }
        }else if(nuevo.contenido.nombre.compareTo(sub.contenido.nombre)>0){
            if(sub.der == null){
                sub.der = nuevo;
            }else{
                sub.der = insertarAVL(nuevo, sub.der);
                if(obtenerAltura(sub.der)-obtenerAltura(sub.izq)==2){
                    if(nuevo.contenido.nombre.compareTo(sub.der.contenido.nombre)>0){
                        padre = rotar_der(sub);
                    }else{
                        padre = rotar_doble_der(sub);
                    }
                }
            }
        }
        if(sub.izq == null && sub.der != null){
            sub.fe = sub.der.fe + 1;
        }else if(sub.izq!=null && sub.der == null){
            sub.fe = sub.izq.fe + 1;
        }else{
            if(obtenerAltura(sub.izq)>obtenerAltura(sub.der)){
                sub.fe = obtenerAltura(sub.izq)+1;
            }else{
                sub.fe = obtenerAltura(sub.der)+1;
            }
        }
        return padre;
    }
    
    public void mos(nodoA tem){
        if(tem!=null){
            System.out.println(tem.contenido.nombre);
            mos(tem.izq);
            mos(tem.der);
        }
    }
}

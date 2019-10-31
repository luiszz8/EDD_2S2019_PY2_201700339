/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author Luis
 */
class nodo{
    public String nombre;
    public String contrasena;
    public int clave;
    public nodo sig;
    public nodo(){
        this.nombre="-1";
        this.contrasena="-1";
        this.sig = null; 
    }
}
public class Tabla {
    public nodo inicio;
    public int tam;
    public Tabla(){
        this.inicio = null;
        this.tam = 0;
        crecer(7);
    }
    public boolean esVacia(){
        return inicio == null;
    }
    public void agregarAlFinal(){
        nodo nuevo = new nodo();
        //nuevo.nombre = nombre;
        //nuevo.contrasena = contra;
        nuevo.clave = this.tam;
        if (esVacia()) {
            inicio = nuevo;
        } else{
            nodo aux = inicio;
            while(aux.sig != null){
                aux = aux.sig;
            }
            aux.sig = nuevo;
        }
        tam++;
    }
    public void crecer(int cant){
        int aux=0;
        while(aux<cant){
            agregarAlFinal();
            aux += 1;
        }
    }
}

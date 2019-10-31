/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Luis
 */
class nodoP{
    public String usuario, tiempo ,descripcion;
    public nodoP sig;
    public nodoP(String usuario, String descripcion){
        this.usuario = usuario;
        this.descripcion = descripcion;
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        this.tiempo = "Hora y fecha: " + hourdateFormat.format(date);
        this.sig = null;
    }
}
public class Pila {
    public nodoP cima;
    public int tam;
    public Pila(){
        this.cima = null;
        this.tam = 0;
    }
    public void agregar(String usuario,String descripcion){
        nodoP nuevo = new nodoP(usuario, descripcion);
        if(this.cima == null){
            this.cima = nuevo;
            this.tam += 1;
        }else{
            nuevo.sig = this.cima;
            this.cima = nuevo;
            this.tam += 1;
        }
    }
}

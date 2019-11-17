/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import proyecto.pkg2.edd.GraphvizJava;

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
    static int veces=0;
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
    public void grafo() throws IOException{
        nodoP tem=cima;
        String ruta="pila.dot";
        String contenido = "digraph G { \n";
        contenido= contenido+" node [shape=record]; \n";
        contenido= contenido+" node1[label = \"{ Bitacora";
        while(tem!=null){
            contenido=contenido+"|"+"Usuario: "+tem.usuario +" Time: "+tem.tiempo+" Descripcion: "+tem.descripcion;
            tem=tem.sig;
        }
        contenido = contenido + "}\"] \n}";
        File file = new File(ruta);
            // Si el archivo no existe es creado
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(contenido);
        bw.close();
        GraphvizJava grafo=new GraphvizJava("pila.dot","src/Imagenes/pila.png");
    }
}

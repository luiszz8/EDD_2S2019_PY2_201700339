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
import javax.swing.JOptionPane;
import proyecto.pkg2.edd.Archivo;
import proyecto.pkg2.edd.GraphvizJava;

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
    
    public void insertar(String nombre,String contenido, String extension, String time,String prop){
        nodoA nuevo = new nodoA(new Archivo(nombre,contenido,extension,time,prop));
        int resp=0;
        if (raiz!=null) {
            if (existe(raiz,nombre+"."+extension).equals("")) {
            
            }else{
                resp = JOptionPane.showConfirmDialog(null, "¿Desea reescribir el archivo?");
                if (resp==0) {
                    System.out.println(eliminar(nombre+"."+extension));
                }
            }
        }
        if (resp==0) {
            if(raiz == null){
                raiz = nuevo;
            }else{
                raiz = insertarAVL(nuevo,raiz);
            }
        }
    }
    
    public nodoA insertarAVL(nodoA nuevo, nodoA sub){
        nodoA padre = sub;
        if((nuevo.contenido.nombre+"."+nuevo.contenido.extension).compareTo(sub.contenido.nombre+"."+sub.contenido.extension)<0){
            if(sub.izq == null){
                sub.izq = nuevo;
            }else{
                sub.izq = insertarAVL(nuevo, sub.izq);
                if(obtenerAltura(sub.izq)-obtenerAltura(sub.der)==2){
                    if((nuevo.contenido.nombre+"."+nuevo.contenido.extension).compareTo(sub.izq.contenido.nombre+"."+sub.izq.contenido.extension)<0){
                        padre = rotar_izq(sub);
                    }else{
                        padre = rotar_doble_izq(sub);
                    }
                }
            }
        }else if((nuevo.contenido.nombre+"."+nuevo.contenido.extension).compareTo(sub.contenido.nombre+"."+sub.contenido.extension)>0){
            if(sub.der == null){
                sub.der = nuevo;
            }else{
                sub.der = insertarAVL(nuevo, sub.der);
                if(obtenerAltura(sub.der)-obtenerAltura(sub.izq)==2){
                    if((nuevo.contenido.nombre+"."+nuevo.contenido.extension).compareTo(sub.der.contenido.nombre+"."+sub.der.contenido.extension)>0){
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
    public String nombresA(nodoA tem){
        String datos="";
        if(tem!=null){
            if (tem.izq!=null) {
                datos=datos+nombresA(tem.izq);
            }
            datos=datos+tem.contenido.nombre+"."+tem.contenido.extension+";";
            if (tem.der!=null) {
                datos=datos+nombresA(tem.der);
            }
        }
        return datos;
    }
    public boolean eliminar(String nombre){
        nodoA auxiliar = raiz;
        nodoA padre = raiz;
        boolean esHizq = true;
        while(!(auxiliar.contenido.nombre+"."+auxiliar.contenido.extension).equals(nombre)){
            padre=auxiliar;
            if ((auxiliar.contenido.nombre+"."+auxiliar.contenido.extension).compareTo(nombre)>0) {
                esHizq = true;
                auxiliar=auxiliar.izq;
            }else{
                esHizq = false;
                auxiliar=auxiliar.der;
            }
            if (auxiliar==null) {
                return false;
            }
        }
        if (auxiliar.izq==null && auxiliar.der==null) {
            if (auxiliar==raiz) {
                raiz=null;
            }else if (esHizq) {
                padre.izq=null;
            }else{
                padre.der=null;
            }
        }else if (auxiliar.der==null) {
            if (auxiliar==raiz) {
                raiz=auxiliar.izq;
            }else if (esHizq) {
                padre.izq=auxiliar.izq;
            }else{
                padre.der=auxiliar.izq;
            }
        }else if (auxiliar.izq==null) {
            if (auxiliar==raiz) {
                raiz=auxiliar.der;
            }else if (esHizq) {
                padre.izq=auxiliar.der;
            }else{
                padre.der=auxiliar.der;
            }
        }else{
            nodoA reemplazo=obtenerReemplazo(auxiliar);
            if (auxiliar==raiz) {
                raiz= reemplazo;
            }else if (esHizq) {
                padre.izq=reemplazo;
            }else{
                padre.der=reemplazo;
            }
            reemplazo.izq=auxiliar.izq;
        }
        mos(raiz);
        return true;
    }
    
    public nodoA obtenerReemplazo(nodoA nodo){
        nodoA reemplazoPadre=nodo;
        nodoA remplazo = nodo;
        nodoA aux = nodo.der;
        while(aux!=null){
            reemplazoPadre=remplazo;
            remplazo=aux;
            aux=aux.izq;
        }
        if (remplazo!=nodo.der) {
            reemplazoPadre.izq=remplazo.der;
            remplazo.der=nodo.der;
        }
        return remplazo;
    }
    
    public String contenido(nodoA tem,String nombre){
        String datos="";
        if(tem!=null){
            datos= datos+contenido(tem.izq,nombre);
            if ((tem.contenido.nombre+"."+tem.contenido.extension).equals(nombre)) {
                return tem.contenido.contenido;
            }
            datos= datos+contenido(tem.der,nombre);
        }
        return datos;
    }
    public String existe(nodoA tem,String nombre){
        String datos="";
        if(tem!=null){
            datos= datos+contenido(tem.izq,nombre);
            if ((tem.contenido.nombre+"."+tem.contenido.extension).equals(nombre)) {
                return tem.contenido.nombre;
            }
            datos= datos+contenido(tem.der,nombre);
        }
        return datos;
    }
    public String todo(nodoA tem,String nombre){
        String datos="";
        if(tem!=null){
            datos= datos+contenido(tem.izq,nombre);
            if ((tem.contenido.nombre+"."+tem.contenido.extension).equals(nombre)) {
                return tem.contenido.nombre+";"+tem.contenido.contenido+";"+tem.contenido.extension+";"+tem.contenido.time+";"+tem.contenido.prop;
                
            }
            datos= datos+contenido(tem.der,nombre);
        }
        return datos;
    }
    public void grafo() throws IOException{
        String ruta = "Arbol.dot";
        String datos = "digraph BST {";
        datos=datos+grafo(raiz);
        datos= datos +"}";
        File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(datos);
            bw.close();
            GraphvizJava grafo=new GraphvizJava("Arbol.dot","src/Imagenes/arbol.png");
    }
    public String grafo(nodoA nodo){
        String datos ="";
        if (nodo!=null) {
            if (nodo.izq!=null){ 
                datos=datos+ "\"Nombre: " +nodo.contenido.nombre +"."+nodo.contenido.extension +"\\n Contenido: "+nodo.contenido.contenido+"\\n FE: "+(this.obtenerAltura(nodo.der)-this.obtenerAltura(nodo.izq))+"\\n Altura: "+nodo.fe+"\\n Time: "+nodo.contenido.time+"\\n Propiedad: "+nodo.contenido.prop+"\"";
                datos = datos + "->" + "\"Nombre: " +nodo.izq.contenido.nombre +"."+nodo.izq.contenido.extension +"\\n Contenido: "+nodo.izq.contenido.contenido+"\\n FE: "+(this.obtenerAltura(nodo.izq.der)-this.obtenerAltura(nodo.izq.izq))+"\\n Altura: "+nodo.izq.fe+"\\n Time: "+nodo.izq.contenido.time+"\\n Propiedad: "+nodo.izq.contenido.prop+"\""+ ";";
            }
            if (nodo.der!=null){ 
                datos=datos+ "\"Nombre: " +nodo.contenido.nombre +"."+nodo.contenido.extension +"\\n Contenido: "+nodo.contenido.contenido+"\\n FE: "+(this.obtenerAltura(nodo.der)-this.obtenerAltura(nodo.izq))+"\\n Altura: "+nodo.fe+"\\n Time: "+nodo.contenido.time+"\\n Propiedad: "+nodo.contenido.prop+"\"";
                datos = datos + "->" + "\"Nombre: " +nodo.der.contenido.nombre +"."+nodo.der.contenido.extension +"\\n Contenido: "+nodo.der.contenido.contenido+"\\n FE: "+(this.obtenerAltura(nodo.der.der)-this.obtenerAltura(nodo.der.izq))+"\\n Altura: "+nodo.der.fe+"\\n Time: "+nodo.der.contenido.time+"\\n Propiedad: "+nodo.der.contenido.prop+"\""+ ";";
            }
            datos=datos+grafo(nodo.izq);
            datos=datos+grafo(nodo.der);
        }
        return datos;
    }
}

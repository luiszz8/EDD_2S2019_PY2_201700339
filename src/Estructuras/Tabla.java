/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import javax.swing.JOptionPane;

/**
 *
 * @author Luis
 */
class nodoH{
    public String nombre;
    public String contrasena;
    public Matriz propia;
    
    public nodoH(){
        this.nombre="";
        this.contrasena="";
        this.propia = new Matriz();
    }
}
public class Tabla{
    public nodoH[] tablahash;
    public Tabla(){
        this.tablahash = new nodoH[7];
    }
    public int tam(){
        return tablahash.length;
    } 
    
    int funcion(String nombre){
        char[] abc ={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z','1','2','3','4','5','6','7','8','9','0'};
        int valor=0;
        for (int i = 0; i < nombre.length(); i++) {
            for (int j = 0; j < abc.length; j++) {
                if (nombre.charAt(i)==abc[j]) {
                   valor= valor + j; 
                }
            }
        }
        return valor;
    }
    int lugar(int id, int tope){
        int posicion = id % (tope);        
        return posicion;
    }
    
    int porcentaje(int tope, nodoH[] tabla){
        int hayestudiante=0;
            for (int i = 0; i < tabla.length; i++) {
                if(tabla[i]!=null){
                    hayestudiante++;
                }
            }            
            double t=tope*0.75;
            System.out.println("Tope..."+t+"============================porcentaje: "+hayestudiante);
            if(hayestudiante<t){
                return 1;
            }else{
               return 0;
            }        
    }
    
    int Esprimo(int numero){
       numero++;
       while(true){
           int cont=0;
           for (int i = 1; i <= numero; i++) {
               if(numero%i==0){
                   cont++;
               }
           }
           if(cont<=2){
               //System.out.println("Primo  " +numero);
               return numero;
           }else{
               numero++;
           }
       }
    }
    
    int colision(int id, int colision){
        int col=((id % 7)+1)*colision;
        //System.out.println("COLISION...."+col);
        return col;
    }
    
    
            

    public void Insertar(String nombre,String contra){
        nodoH estudiante=new nodoH();
        estudiante.nombre=nombre;
        estudiante.contrasena=contra;
        int tope= tablahash.length;
        if(porcentaje(tope, tablahash)==1){
            //System.out.println("No ha llegado al porcentaje");
            int lugar=lugar(funcion(estudiante.nombre), tope);
            if(tablahash[lugar]==null){
                tablahash[lugar]=estudiante;
                System.out.println("ID: "+estudiante.nombre +"  Posicion : "+lugar);
            }else{
                if (tablahash[lugar].nombre==nombre) {
                    JOptionPane.showMessageDialog(null, "Usuario ya existe");
                    return;
                }
                double colision=1;
                System.out.println("Hubo colision en "+lugar);
                
                while(true){
                    int numero=lugar+(int)Math.pow(colision, 2);
                    while(numero>tablahash.length-1){
                        numero = numero - tope;
                    }
                    if(tablahash[numero]==null){                        
                        tablahash[numero]=estudiante;
                        System.out.println(estudiante.nombre+" Se inserto en "+numero);                        
                        break;
                    }else{
                        colision++;
                    }
                }
            }            
        }else{
            System.out.println("************************************************Llego al 75%*******************************************************");
            Cambio(tope,nombre,contra,tablahash);
        }
        JOptionPane.showMessageDialog(null, "Usuario ingresado");
    }
    
    void Cambio(int tope,String nombre,String contra, nodoH[] tabla){
        //System.out.println(Esprimo(tope));
        nodoH[] nuevatabla=new nodoH[Esprimo(tope)];
        for (int i = 0; i <tabla.length; i++) {
            if(tabla[i]==null){
                //System.out.println("No ingresa nada");
            }else{
                nuevatabla[i]=tabla[i];
            }
        }
        this.tablahash = nuevatabla;
        tope=tablahash.length;
        Insertar(nombre,contra);
        /*for (int i = 0; i < tablahash.length; i++) {
            if (tablahash[i]!=null) {
                System.out.println(tablahash[i].nombre);
            }else{
                System.out.println("null");
            }
        }*/
    }
    
    public Matriz raiz(int lugar,String nombre,String contra){
        if (tablahash[lugar]!=null) {
            if (this.tablahash[lugar].nombre.equals(nombre) && this.tablahash[lugar].contrasena.equals(contra)) {
                return tablahash[lugar].propia;
            }else{
                for (int i = 0; i < tablahash.length; i++) {
                    if (tablahash[i]!=null) {
                        if (this.tablahash[i].nombre.equals(nombre) && this.tablahash[i].contrasena.equals(contra)) {
                            return tablahash[i].propia;
                        }
                    }
                }
                return null;
            }
        }else{
            return null;
        }
    }
    public boolean existe(String nombre){
        for (int i = 0; i < tablahash.length; i++) {
            if (tablahash[i]!=null) {
                if (tablahash[i].nombre.equals(nombre)) {
                    return false;
                }
            }
        }
        return true;
    }
    public Matriz recibir(String nombre){
        for (int i = 0; i < tablahash.length; i++) {
            if (tablahash[i]!=null) {
                if (tablahash[i].nombre.equals(nombre)) {
                    return tablahash[i].propia;
                }
            }
        }
        return null;
    }
}
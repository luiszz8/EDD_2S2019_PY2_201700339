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
class nodoH{
    public String nombre;
    public String contrasena;
    
    public nodoH(){
        this.nombre="";
        this.contrasena=""; 
    }
}
public class Tabla{
    nodoH[] tablahash;
    public Tabla(){
        this.tablahash = new nodoH[7];
    }
    int funcion(String nombre){
        char[] abc ={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z'};
        int valor=1;
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
    
    
            

    void Insertar(int tope, nodoH estudiante, nodoH[] tabla){
  
        if(porcentaje(tope, tabla)==1){
            //System.out.println("No ha llegado al porcentaje");
            int lugar=lugar(funcion(estudiante.nombre), tope);
            if(tabla[lugar]==null){
                tabla[lugar]=estudiante;
                System.out.println("ID: "+estudiante.nombre +"  Posicion : "+lugar);
            }else{       
                double colision=1;
                System.out.println("Hubo colision en "+lugar);
                
                while(true){
                    int numero=lugar+(int)Math.pow(colision, 2);
                    while(numero>tabla.length-1){
                        numero = numero - tope;
                    }
                    if(tabla[numero]==null){                        
                        tabla[numero]=estudiante;
                        System.out.println(estudiante.nombre+" Se inserto en "+numero);                        
                        break;
                    }else{
                        colision++;
                    }
                }
            }            
        }else{
            System.out.println("************************************************Llego al 75%*******************************************************");
            Cambio(tope,estudiante, tabla);
        }
        
    }
    
    void Cambio(int tope, nodoH estudiante, nodoH[] tabla){
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
        Insertar(tope, estudiante, tablahash);
    }
}
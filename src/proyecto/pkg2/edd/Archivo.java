/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.pkg2.edd;

/**
 *
 * @author Luis
 */
public class Archivo {
    public String nombre, extension, contenido, time,prop;
    public Archivo(String nombre,String contenido, String extension, String time,String prop){
        this.contenido = contenido;
        this.extension = extension;
        this.nombre = nombre;
        this.time = time;
        this.prop=prop;
    }
}

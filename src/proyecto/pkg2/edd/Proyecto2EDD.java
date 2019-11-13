/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.pkg2.edd;

import Estructuras.Tabla;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



/**
 *
 * @author Luis
 */
public class Proyecto2EDD {
    public static Tabla tablaha=new Tabla();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update("admin".getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest){
            sb.append(String.format("%02x", b & 0xff));
        }
        tablaha.Insertar("admin", sb.toString());
        InicioSesion sesion=new InicioSesion();
        sesion.setVisible(true);
        sesion.setLocationRelativeTo(null);
        /*Tabla ha=new Tabla();
        ha.Insertar("zxy","fernando");
        ha.Insertar("luis","fernando");
        ha.Insertar("abc","fernando");
        ha.Insertar("jorge","fernando");
        ha.Insertar("eduardo","fernando");
        ha.Insertar("ab","fernando");
        ha.Insertar("jkl","fernando");*/
    } 
}

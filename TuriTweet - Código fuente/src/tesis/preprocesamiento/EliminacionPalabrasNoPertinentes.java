/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.preprocesamiento;


import java.util.ArrayList;
import java.util.List;

import tesis.util.DBConexion;
import tesis.util.Tweet;

/**
 *
 * @author unicef
 */
public class EliminacionPalabrasNoPertinentes {

    private DBConexion conexion;

    public EliminacionPalabrasNoPertinentes(DBConexion conexion) {
        this.conexion = conexion;
    }
  
    public void eliminarPalabrasIrrelevantes(ArrayList<Tweet> tweets) {
        System.out.println("ELIMINACION DE PALABRAS IRRELEVANTES");
        System.out.println("......");
        List<String> palabrasPertinentes = conexion.leerPalabrasClaves();
        for (Tweet tweet : tweets) {
            String tweetLimpio = "";
            //String[] cadena = tweet.getStatus().split(" ");
            String[] cadena = tweet.getStatus().split("\\s+");
            Boolean bandera;
            for (int i = 0; i < cadena.length; i++) {
                bandera = false;
                for (String palabra : palabrasPertinentes) {
                    if (cadena[i].equals(palabra.toLowerCase())) {
                        bandera = true;
                    }
                }
                if (bandera) {
                    tweetLimpio = tweetLimpio + " " + cadena[i];
                }
            }
            tweet.setStatus(tweetLimpio);
        }
    }

    public Integer eliminarPalabrasRaras(List<Tweet> tweets) {
        System.out.println("......");
        System.out.println("PALABRAS POCO FRECUENTES");
        System.out.println("......");
        int contador = 0;
        List<String> palabrasRaras = conexion.leerPalabrasRaras();
        Boolean bandera;
        for (Tweet tweet : tweets) {
            String tweetLimpio = "";
            String[] statusSplit = tweet.getStatus().split("\\s+");
            for (int i = 0; i < statusSplit.length; i++) {
                bandera = false;
                if (palabrasRaras.contains(statusSplit[i].toLowerCase())) {
                    bandera = true;
                    contador++;
                }
                if (!bandera) {
                    tweetLimpio = tweetLimpio + " " + statusSplit[i];
                }
            }
            tweet.setStatus(tweetLimpio);
        }
        System.out.println("Palabras raras encontradas: " + contador);
        return contador;
    }
}

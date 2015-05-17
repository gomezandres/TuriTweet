/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.preprocesamiento;


import java.util.ArrayList;
import java.util.List;
import tesis.util.Tweet;


/**
 *
 * @author tebs
 */
public class EliminacionURLs {

    

    /**
     * @param args the command line arguments
     */
    public Integer eliminarURLs(List<Tweet> tweets) {
//        System.out.println("......");
//        System.out.println("URL");
//        System.out.println("......");
        String aux = "";
        String palabra[];
        int contador = 0;
        for (Tweet tweet : tweets) {
            if (tweet.getStatus().contains("http://") || tweet.getStatus().contains("https://")) {
//                palabra = tweet.getStatus().split(" ");
                palabra = tweet.getStatus().split("\\s+");
                for (int i = 0; i < palabra.length; i++) {
                    if (palabra[i].contains("http://") || tweet.getStatus().contains("https://")) {
                        palabra[i] = " <link> ";
                        contador++;
                    }
                    if (i == 0) {
                        aux = palabra[i];
                    } else {
                        aux = aux + " " + palabra[i];
                    }

                }
                tweet.setStatus(aux);
                aux = "";
            }
        }
        System.out.println("Links encontrados: "+contador);
      return contador;  
    }
}

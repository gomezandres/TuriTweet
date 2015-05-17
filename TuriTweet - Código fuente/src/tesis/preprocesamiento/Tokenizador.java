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
public class Tokenizador {

    public List<Tweet> tokenizar(List<Tweet> tweets) {
        System.out.println("......");  
        System.out.println("TOKENIZACION - STEMMER");
            System.out.println("......");
        for (Tweet tweet : tweets) {
//            String array[] = tweet.getStatus().split(" ");
            String array[] = tweet.getStatus().split("\\s+");
            int c = 0;
            String tweetLimpio = "";
            Stemmer stemm = new Stemmer();
            while (c < array.length) {
                String palabra = array[c];
                String lema = stemm.stemm(palabra);
                //System.out.println(palabra);
                //con.cargarLemas(palabra, lema);
                tweetLimpio = tweetLimpio + lema + " ";
                c++;       
            }
            tweet.setStatus(tweetLimpio);
        }
        return tweets;
    }
}

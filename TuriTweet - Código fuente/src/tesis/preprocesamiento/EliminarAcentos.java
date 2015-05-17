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
public class EliminarAcentos {

    public void eliminarAcentos(List<Tweet> tweets) {
        System.out.println("......");
        System.out.println("ACENTOS");
        System.out.println("......");
        for (Tweet tweet : tweets) {
            tweet.setStatus(removeAccent(tweet.getStatus()));
        }
    }

    private String removeAccent(String word) {
        word = word.replace('á', 'a');
        word = word.replace('é', 'e');
        word = word.replace('í', 'i');
        word = word.replace('ó', 'o');
        word = word.replace('ú', 'u');
        return word;
    }
}

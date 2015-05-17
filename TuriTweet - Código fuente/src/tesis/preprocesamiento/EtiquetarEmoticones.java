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
public class EtiquetarEmoticones {

    /**
     * @param args the command line arguments
     */
    public Integer etiquetarEmoticones(List<Tweet> tweets) {
        System.out.println("......");
        System.out.println("EMOTICONES");
        System.out.println("......");
        this.etiquetarEmoticonesTristes(this.etiquetarEmoticonesFeliz(tweets));
        return this.contarFelices(tweets) + this.contarTristes(tweets);
    }

    public List<Tweet> etiquetarEmoticonesFeliz(List<Tweet> tweets) {
        String status;
        for (Tweet tweet : tweets) {
            status = tweet.getStatus().replaceAll(":\\)", " <feliz> ");
            status = status.replaceAll(":d", " <feliz> ");
            status = status.replaceAll("\\♥", " <feliz> ");
            status = status.replaceAll("\\❤", " <feliz> ");
            status = status.replaceAll(":\\)", " <feliz> ");
            status = status.replaceAll("\\(:", " <feliz> ");
            status = status.replaceAll(":p", " <feliz> ");
            status = status.replaceAll(":3", " <feliz> ");
            status = status.replaceAll("<3", " <feliz> ");
            status = status.replaceAll("\\❤", " <feliz> ");
            status = status.replaceAll(":-\\)", " <feliz> ");
            status = status.replaceAll(":'-\\)", " <feliz> ");
            status = status.replaceAll(":-d", " <feliz> ");
            status = status.replaceAll("xd", " <feliz> ");
            status = status.replaceAll("x-d", " <feliz> ");
            status = status.replaceAll(";\\)", " <feliz> ");
            status = status.replaceAll(";-\\)", " <feliz> ");
            status = status.replaceAll(":-*", " <feliz> ");
            status = status.replaceAll("\\=\\)", " <feliz> ");
            status = status.replaceAll("\\(\\=", " <feliz> ");
            status = status.replaceAll("^^", " <feliz> ");
            status = status.replaceAll("^.^", " <feliz> ");
            tweet.setStatus(status);

        }
        return tweets;
    }

    public List<Tweet> etiquetarEmoticonesTristes(List<Tweet> tweets) {
        String status;
        for (Tweet tweet : tweets) {
            status = tweet.getStatus().replaceAll(":\\(", " <triste> ");
            status = status.replaceAll(":'\\(", " <triste> ");
            status = status.replaceAll(":'c", " <triste> ");
            status = status.replaceAll(":c", " <triste> ");
            status = status.replaceAll(":\\(", " <triste> ");
            status = status.replaceAll("\\):", " <triste> ");
            status = status.replaceAll(":-\\(", " <triste> ");
            status = status.replaceAll(":'-\\(", " <triste> ");
            status = status.replaceAll(":@", " <triste> ");
            status = status.replaceAll("-.-", " <triste> ");
            status = status.replaceAll("-_-", " <triste> ");
            status = status.replaceAll(":/", " <triste> ");
            status = status.replaceAll(":-/", " <triste> ");
            status = status.replaceAll("\\=\\(", " <feliz> ");
            status = status.replaceAll("\\)\\=", " <feliz> ");
            status = status.replaceAll(":\\|", " <feliz> ");
            tweet.setStatus(status);
            //System.out.println(tweet.getStatus());
        }
        return tweets;
    }

    private Integer contarFelices(List<Tweet> tweets) {
        int contador = 0;
        for (Tweet tweet : tweets) {
            String[] stringSplit = tweet.getStatus().split("\\s+");
            for (int i = 0; i < stringSplit.length; i++) {
                String palabra = stringSplit[i];
                if (palabra.equals("<feliz>")) {
                    contador++;
                }
            }
        }
        System.out.println("Emoticones positivos encontrados: " + contador);
        return contador;
    }

    private Integer contarTristes(List<Tweet> tweets) {
        int contador = 0;
        for (Tweet tweet : tweets) {
            String[] stringSplit = tweet.getStatus().split("\\s+");
            for (int i = 0; i < stringSplit.length; i++) {
                String palabra = stringSplit[i];
                if (palabra.equals("<triste>")) {
                    contador++;
                }
            }
        }
        System.out.println("Emoticones negativos encontrados: " + contador);
        return contador;
    }
}

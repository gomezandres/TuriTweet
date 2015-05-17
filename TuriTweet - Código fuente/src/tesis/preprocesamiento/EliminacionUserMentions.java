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
public class EliminacionUserMentions {

    public Integer EliminarMenciones(List<Tweet> tweets) {
        System.out.println("......");
        System.out.println("MENCIONES");
        System.out.println("......");
        String user_mention = null;
        int contador = 0;
        for (Tweet tweet : tweets) {
            if (!tweet.getUser_mentions().equals("")) {
                user_mention = tweet.getUser_mentions().replaceAll(" ", " @");
                tweet = this.limpiarUserMentions(tweet, user_mention);
                contador++;
            }
        }
        System.out.println("Mensiones encontradas: "+contador);
        return contador;
    }

    public Tweet limpiarUserMentions(Tweet tweet, String menciones) {
//        String[] usuarioMencionados = menciones.split(" ");
        String[] usuarioMencionados = menciones.split("\\s+");
        for (int i = 0; i < usuarioMencionados.length; i++) {
            tweet.setStatus(tweet.getStatus().toLowerCase().replace(usuarioMencionados[i].toLowerCase(), ""));
        }
        //  System.out.println(tweet.getStatus() + " || " + tweet.getUser_mentions());
        return tweet;
    }
}

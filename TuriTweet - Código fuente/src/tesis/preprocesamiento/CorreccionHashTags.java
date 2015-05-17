/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.preprocesamiento;

import java.util.ArrayList;
import tesis.util.Tweet;


/**
 *
 * @author Tebs
 */
public class CorreccionHashTags {

    public void corregirHastags(ArrayList<Tweet> tweets) {
        System.out.println("HASHTAGS");
        System.out.println("......");
        for (Tweet tweet : tweets) {
            this.separarHastag(tweet);
        }
    }

    public void separarHastag(Tweet tw) {
//        System.out.println(tw.getStatus());
        boolean hayHashTag = false;
        String laCadena = tw.getStatus();
        StringBuilder nuevaCadena = new StringBuilder();
        for (int i = 0; i < laCadena.length(); i++) {
            if (laCadena.charAt(i) == '#') {
                hayHashTag = true;
            }
            if (i >= 0 && i + 1 < laCadena.length()) {
                if (Character.isUpperCase(laCadena.charAt(i)) && hayHashTag && Character.isLowerCase(laCadena.charAt(i - 1)) && Character.isLowerCase(laCadena.charAt(i + 1))) {
                    nuevaCadena.append(" ");
                    nuevaCadena.append(Character.toLowerCase(laCadena.charAt(i)));
                    tw.setStatus(nuevaCadena.toString());
                } else {
                    nuevaCadena.append(laCadena.charAt(i));
                    tw.setStatus(nuevaCadena.toString());
                }
            }

        }
//        System.out.println(nuevaCadena);
    }
}

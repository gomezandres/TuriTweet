/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.clasificacion.nb;

import java.util.List;
import tesis.util.Tweet;



/**
 *
 * @author asistencia
 */
public class Heuristicas {

    private List<String>[] palabrasDominio;//Contiene las palabras del dominio, con sus respectivos pesos definidos
    private String[] palabrasNegativas = {"no", "nunca", "ninguno", "ni"};
    //wt>0 ^ wt<1 ==> ++P ; w>1 ==> --P
    private Double wU = 1.0, wM = 1.0, wE = 1.0, wT = 1.0, wS = 1.0, wI = 1.0, wBsas = 0.0, wCat = 0.0, wCal = 0.0, wSalt = 0.0, wTdf = 0.0;

    /**
     * Si el tweet posee mas de una url, aumentar P para ng
     */
    private void ponderarURL(Tweet tweet) {
        if (tweet.isUrl()) {
            wU = wU - 0.5;
        }
    }

    /**
     * Si el tweet posee multimedia, aumentar P para clases destinos
     */
    private void ponderarMultimedia(Tweet tweet) {
        if (tweet.isMedia()) {
            wM = wM - 0.3;
        }
    }

    private void ponderarEmoticones(Tweet tweet) {
        if ((tweet.getStatus().contains("<feliz>") || tweet.getStatus().contains("<triste>"))) {
            wE = wE - 0.9;
        }
    }

    private void ponderarSentimientos(Tweet tweet) {
        for (int i = 0; i < palabrasNegativas.length; i++) {
            //Si el tweet tiene emoticon triste y NO tiene una palabra negativa castigarlo
            if (tweet.getStatus().contains("<triste>") && !tweet.getStatus().contains(palabrasNegativas[i])) {
                wS = wS + 0.1;
            }
        }
    }
    //Para ello se deberia etiquetar los signos ? y ! como <?> y <!>

    private void ponderarInterrogacionExclamacion(Tweet tweet) {
        //Si el tweet contiene interrogacion, castigarlo
        if (tweet.getStatus().contains("<?>")) {
            wI = wI + 0.1;
        }
        //Si el tweet contiene exclamacion, premiarlo
        if (tweet.getStatus().contains("<!>")) {
            wI = wI - 0.1;
        }
    }

    private void ponderarTamano(Tweet tweet) {
        String statusSplit[] = tweet.getStatus().split("\\s+");
        if (statusSplit.length < 20) {
            wT = wT - 0.2;
        }
    }

    private void ponderarEtiquetas(Tweet tweet, String categoria) {
        switch (categoria) {
            case "<bsas>":
                if(tweet.getStatus().contains("<bsas>"))
                wBsas = -0.2;
                break;
            case "<cal>":
                if(tweet.getStatus().contains("<cal>"))
                wCal = -0.2;
                break;
            case "<cat>":
                if(tweet.getStatus().contains("<cat>"))
                wCat = -0.2;
                break;
            case "<salt>":
                if(tweet.getStatus().contains("<salt>"))
                wSalt = -0.2;
                break;
            case "<tdf>":
                if(tweet.getStatus().contains("<tdf>"))
                wTdf = -0.2;
                break;
            default:
                break;
        }
    }

    public Double obtenerHeuristica(Tweet tweet, String categoria) {
        switch (categoria) {
            case "<ng>":
                //ponderarURL(tweet);
                return wU;
            default:
                ponderarMultimedia(tweet);
//                ponderarEmoticones(tweet);
//                ponderarTamano(tweet);
                System.out.println("..................................");
                System.out.println("categoria:"+categoria);
                System.out.println("tweet:"+tweet.getStatus());
                System.out.println("wM:"+wM);
                System.out.println("wE:"+wE);
                System.out.println("wT:"+wT);
                System.out.println("..................................");
                Double min = Math.min(Math.min(wM, wE), wT);
                return min;
        }
    }
}

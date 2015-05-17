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
 * @author gomez
 */
public class EliminacionStopWords {

    /**
     * @param args the command line arguments
     */
    public void eliminarStopWords(List<Tweet> tweets, List<String> stopWords) {
        System.out.println("......");
        System.out.println("STOPWORDS");
        System.out.println("......");
        for (Tweet tweet : tweets) {
            //  System.out.println(tweet.getStatus());
            List<String> removeStopWordsFrom = Utlities.removeStopWordsFrom(tweet.getStatus(), stopWords);
            //   System.out.println("TWEET LIMPIO");
            String tweetLimpio = "";
            for (String string1 : removeStopWordsFrom) {
                tweetLimpio = tweetLimpio + " " + string1;
            }
            tweetLimpio = tweetLimpio.replaceAll("\\'", "");
            tweet.setStatus(tweetLimpio);
        }
    }
}

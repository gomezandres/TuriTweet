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
public class LoweerCase {

    public void lowerCasing(List<Tweet> tweets) {
        for (Tweet tweet : tweets) {
            tweet.setStatus(tweet.getStatus().toLowerCase());
        }
    }
}

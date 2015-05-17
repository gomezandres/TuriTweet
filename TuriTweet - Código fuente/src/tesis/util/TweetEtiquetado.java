/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.util;


/**
 *
 * @author Tebs
 */
public class TweetEtiquetado {

    String clase;
    String polaridad;
    boolean claseAcierto;
    boolean claseIndefinido;
    boolean polaridadAcierto;
    boolean polaridadIndefinido;
    Tweet tweet;

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }
    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public boolean isClaseAcierto() {
        return claseAcierto;
    }

    public void setClaseAcierto(boolean claseAcierto) {
        this.claseAcierto = claseAcierto;
    }

    public boolean isClaseIndefinido() {
        return claseIndefinido;
    }

    public void setClaseIndefinido(boolean claseIndefinido) {
        this.claseIndefinido = claseIndefinido;
    }

    public String getPolaridad() {
        return polaridad;
    }

    public void setPolaridad(String polaridad) {
        this.polaridad = polaridad;
    }

    public boolean isPolaridadAcierto() {
        return polaridadAcierto;
    }

    public void setPolaridadAcierto(boolean polaridadAcierto) {
        this.polaridadAcierto = polaridadAcierto;
    }

    public boolean isPolaridadIndefinido() {
        return polaridadIndefinido;
    }

    public void setPolaridadIndefinido(boolean polaridadIndefinido) {
        this.polaridadIndefinido = polaridadIndefinido;
    }
}

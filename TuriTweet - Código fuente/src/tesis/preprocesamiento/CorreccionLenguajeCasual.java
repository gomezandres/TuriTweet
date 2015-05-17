/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.preprocesamiento;


import java.util.List;
import tesis.util.DBConexion;
import tesis.util.PalabraSMS;
import tesis.util.Tweet;


/**
 *
 * @author Tebs
 */
public class CorreccionLenguajeCasual {

    private DBConexion conexion;

    public CorreccionLenguajeCasual(DBConexion conexion) {
        this.conexion = conexion;
    }

    public Integer corregirPalabrasCasuales(List<Tweet> tweets) {
        System.out.println("......");
        System.out.println("LENGUAJES CASUALES");
        System.out.println("......");
        int x = 0;
        List<PalabraSMS> listaPalabras = conexion.leerDiccionarioSms();
        for (Tweet tweet : tweets) {
            String array[] = tweet.getStatus().split("\\s+");
            String tweetLimpio = "";
            int c = 0;
            while (c < array.length) {
                String palabra = array[c];
                for (PalabraSMS palabraSMS : listaPalabras) {
                    if (palabra.equals(palabraSMS.getPalabraMala())) {
                        palabra = palabraSMS.getPalabraBuena();
                        x++;
                        break;
                    }
                }
                tweetLimpio = tweetLimpio + palabra + " ";
                c++;
            }
            tweet.setStatus(tweetLimpio);
        }
        System.out.println("Palabras corregidas:" + x);
        return x;
    }
}

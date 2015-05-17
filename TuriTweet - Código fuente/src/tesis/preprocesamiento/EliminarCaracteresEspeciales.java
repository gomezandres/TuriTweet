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
public class EliminarCaracteresEspeciales {

    /**
     * @param args the command line arguments
     */
    public Integer EliminarCarateresEspeciales(List<Tweet> tweets) {
        System.out.println("......");
        System.out.println("CARACTERES ESPECIALES");
        System.out.println("......");
        int contador = 0;
        String cadena;
        for (Tweet tweet : tweets) {
            cadena = this.removeCaracter(tweet.getStatus());
            contador = contador + tweet.getStatus().length() - cadena.length();
            tweet.setStatus(cadena);
        }
//        this.contarCaracteres(tweets);
//        this.quitarEtiquetaCE(tweets);
        System.out.println("Caracteres especiales encontrados: " + contador);
        return contador;
    }

    public String removeCaracter(String status) {
        String cadena;
//        status = "PaseoMerecido";
        String caracteresEspeciales = "(ji+)*(ij+)*(je+)*(ej+)*(ja+)*(aj+)*(:)*(')*(-)*(\\()*(#)*(@)*(;)*(!)*(3)*(,)*[0-9]*(\\|)*(\\.)*(\\?)*(\\))*(\")*(_)*(/)*(¿)*(¡)*(—)*(\\+)*(”)*(“)*(\\$)*(\\*)*(])*(\\[)*(\\%)*(\\¨)*(°c)*(\\❌)*(\\★)*(\\¬¬)*(\\►)*(\\●)*(\\◑▂◐)*(\\↑tucum)*(\\➨)*(\\↓)*(\\↕)*(\\®)*(\\··&gt)*(\\☂☃☺)*(\\☑☺☀)*(\\☹☹☹)*(\\☺)*(\\☺☺☺)*(\\☻)*(\\♡)*(\\♣)*(\\♪)*(\\♪♫♪♫)*(\\♪nosseguimosentremill)*(\\♪tu)*(\\♫)*(\\♫♫)*(\\♫å♫♫♫♫)*(\\✈)*(\\✌)*(\\✌️️✌️️)*(\\✌️⛄️✌️)*(\\✿✿✿)*(\\❄️⛄️✈️)*(å)*(◡‿◡✿)*(~)*(&)*(【】)*(€)*(<)*(>)*(`)*(´)*(amp&)*(&lt)*(》)*(°)*(·)*(…)*(☹)*(•)*(☑)*(\\{)*(\\})*(＼^^／)*";
        cadena = status.replaceAll("([aeiouAEIOUáéíóú])\\1{1,}", "$1"); //Elimina vocales seguidas repetidas
        cadena = cadena.replaceAll("([^aeiouAEIOUáéíóú])\\1{1,}", "$1$1"); //Elimina cononantes seguidas repetidas, dejando hasta 2 repetidas
        cadena = cadena.replaceAll(caracteresEspeciales, ""); //Elimina caracteres especiales
        cadena = this.limpiarCaracteresSueltos(cadena);
//        System.out.println("*- " + cadena);
        return cadena;
    }

    public String limpiarCaracteresSueltos(String cadena) {
        //Limpieza de letras sueltas
        cadena = cadena.replaceAll(" ([a-z]) ", " ");
        cadena = cadena.replaceAll(" ([a-z]) ", " ");
        cadena = cadena.replaceAll(" ([a-z]) ", " ");
        cadena = cadena.replaceAll(" ([a-z]) ", " ");
        cadena = cadena.replaceAll(" ([a-z]) ", " ");
        cadena = cadena.replaceAll(" ([a-z]) ", " ");
        cadena = cadena.replaceAll(" ([a-z]) ", " ");
        cadena = cadena.replaceAll(" ([a-z]) ", " ");
        cadena = cadena.replaceAll(" ([a-z]) ", " ");
        cadena = cadena.replaceAll(" ([a-z]) ", " ");
        cadena = cadena.replaceAll(" ([a-z]) ", " ");
        cadena = cadena.replace("\\", ""); //Limpieza para la insercion en BD
        return cadena;
    }

    private void contarCaracteres(ArrayList<Tweet> tweets) {
        int contador = 0;
        for (Tweet tweet : tweets) {
            String[] stringSplit = tweet.getStatus().split("\\s+");
            for (int i = 0; i < stringSplit.length; i++) {
                String palabra = stringSplit[i];
                if (palabra.equals("<ce>")) {
                    contador++;
                }
            }
        }
        System.out.println("Caracteres especiales encontrados: " + contador);
    }

    private void quitarEtiquetaCE(ArrayList<Tweet> tweets) {
        for (Tweet tweet : tweets) {
            tweet.getStatus().replaceAll("<ce>", " ");
        }
    }
}

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
public class EtiquetadoPalabrasClaves {

    private ArrayList<String> claveBuenosAires = new ArrayList<>();
    private ArrayList<String> claveCalafate = new ArrayList<>();
    private ArrayList<String> claveCataratas = new ArrayList<>();
    private ArrayList<String> claveSalta = new ArrayList<>();
    private ArrayList<String> claveTDF = new ArrayList<>();

    public EtiquetadoPalabrasClaves() {
        this.definirEtiquetas();
    }

    /**
     * @param args the command line arguments
     */
    public void definirEtiquetas() {
        this.claveBuenosAires.add("buenos aires tecnopolis");
        this.claveBuenosAires.add("buenos aires");
        this.claveBuenosAires.add("bs as");
        this.claveBuenosAires.add("bsas");
        this.claveBuenosAires.add("tecnopolis argentina");
        this.claveBuenosAires.add("tecnopolis");
        this.claveBuenosAires.add("parque de la costa");
        this.claveBuenosAires.add("parque costa");
        this.claveBuenosAires.add("cost park");
        this.claveBuenosAires.add("puerto madero");
        this.claveCalafate.add("glaciar perito moreno");
        this.claveCalafate.add("glacier perito moreno");
        this.claveCalafate.add("perito moreno glacier");
        this.claveCalafate.add("argentina calafate");
        this.claveCalafate.add("calafate argentina");
        this.claveCalafate.add("perito moreno");
        this.claveCalafate.add("calafate");
        this.claveCataratas.add("cataratas del iguazu");
        this.claveCataratas.add("cataratas iguazu");
        this.claveCataratas.add("cataratas misiones");
        this.claveCataratas.add("cataratas argentina");
        this.claveCataratas.add("cataratas devil throat");
        this.claveCataratas.add("garganta del diablo");
        this.claveCataratas.add("garganta diablo");
        this.claveCataratas.add("iguazu falls");
        this.claveCataratas.add("cataratas");
        this.claveCataratas.add("iguazu");
        this.claveSalta.add("provincia de salta");
        this.claveSalta.add("provincia salta");
        this.claveSalta.add("salta la linda");
        this.claveSalta.add("salta linda");
        this.claveSalta.add("salta argentina");
        this.claveSalta.add("salta city");
        this.claveSalta.add("salta province");
        this.claveSalta.add("salta tren de las nubes");
        this.claveSalta.add("salta tren nubes");
        this.claveSalta.add("salta tren de las nubes");
        this.claveSalta.add("salta beautiful");
        this.claveSalta.add("tren de las nubes");
        this.claveSalta.add("tren nubes");
        this.claveSalta.add("salta");
        this.claveTDF.add("ushuaia beautiful");
        this.claveTDF.add("tierra del fuego");
        this.claveTDF.add("tierra fuego");
        this.claveTDF.add("beagle channel");
        this.claveTDF.add("tolhuin");
        this.claveTDF.add("canal beagle");
        this.claveTDF.add("ushuaia");
    }

    public Integer etiquetar(List<Tweet> tweets) {
        System.out.println("......");
        System.out.println("PALABRAS CLAVES");
        System.out.println("......");
        String cadena = null;
        for (Tweet tweet : tweets) {
            cadena = this.reemplazarPalabraClave(tweet.getStatus());
            tweet.setStatus(cadena);
        }
        return this.contarClaves(tweets);
    }

    public String reemplazarPalabraClave(String status) {
        String cadenaReemplazada = status;
        for (String clave : claveBuenosAires) {
            if (status.contains(clave)) {
                cadenaReemplazada = cadenaReemplazada.replace(clave, " <bsas> ");
            }
        }
        for (String clave : claveCalafate) {
            if (status.contains(clave)) {
                cadenaReemplazada = cadenaReemplazada.replace(clave, " <cal> ");
            }
        }
        for (String clave : claveCataratas) {
            if (status.contains(clave)) {
                cadenaReemplazada = cadenaReemplazada.replace(clave, " <cat> ");
            }
        }
        for (String clave : claveSalta) {
            if (status.contains(clave)) {
                cadenaReemplazada = cadenaReemplazada.replace(clave, " <salt> ");
            }
        }
        for (String clave : claveTDF) {
            if (status.contains(clave)) {
                cadenaReemplazada = cadenaReemplazada.replace(clave, " <tdf> ");
            }
        }
        return cadenaReemplazada;
    }

    private Integer contarClaves(List<Tweet> tweets) {
        int contador = 0;
        for (Tweet tweet : tweets) {
            String[] stringSplit = tweet.getStatus().split("\\s+");
            for (int i = 0; i < stringSplit.length; i++) {
                String palabra = stringSplit[i];
                if (palabra.equals("<bsas>") || palabra.equals("<cal>") || palabra.equals("<cat>") || palabra.equals("<salt>") || palabra.equals("<tdf>")) {
                    contador++;
                }
            }
        }
        System.out.println("Palabras clave encontradas: " + contador);
        return contador;
    }
}

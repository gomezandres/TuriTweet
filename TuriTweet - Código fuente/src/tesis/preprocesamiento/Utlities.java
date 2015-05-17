package tesis.preprocesamiento;

//Provee solo funciones utilies como eliminar las stopWords
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utlities {

    public static ArrayList<String> removeStopWordsFrom(String arrayTerms, List<String> stopWords) {
        ArrayList<String> aux = new ArrayList<>();
        //Limpieza de signos de puntuacion
//        String array[] = arrayTerms.split(" ");
        String array[] = arrayTerms.split("\\s+");
        aux.addAll(Arrays.asList(array));
        aux.removeAll(stopWords);
        return aux;
    }

    public static String fixUrl(String url) {
        url = url.replace("_", ".");
        url = url.replace("-", "/");
        return url;
    }

    public void limpiarSignosPuntuacion() {
    }
}

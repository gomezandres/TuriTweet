/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.preprocesamiento;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tesis.util.DBConexion;
import tesis.util.Tweet;
import tesis.ventanas.PreProcesamientoGUI;

/**
 *
 * @author tebs
 */
public class ControladoraPreProcesamiento {

    private List<String> stopWords;
    private DBConexion conexion;

    public ControladoraPreProcesamiento(DBConexion conexion) {
        stopWords = new ArrayList<>();
        this.conexion = conexion;
    }
    //Preprocesamiento para clasificar
    public List<Tweet> procesar(String filtradoClase, int codigoProcesar, List<Tweet> tweets, PreProcesamientoGUI ventana) {
        // System.out.println("PREPARANDO DATOS...");
        try {
//            CorreccionHashTags correccionHastags = new CorreccionHashTags();
            switch (codigoProcesar) {
                case 1://                 
                {
                    tweets = this.conexion.tweetsEtiquetados(filtradoClase);
                    LoweerCase loweerCase = new LoweerCase();
                    loweerCase.lowerCasing(tweets);
                    break;
                }
                case 2:
                    EliminacionURLs eliminacionURLs = new EliminacionURLs();
                    ventana.getContUrl().setText("("+eliminacionURLs.eliminarURLs(tweets).toString()+")");
                    break;
                case 3:
                    EliminacionUserMentions eliminacionUserMentions = new EliminacionUserMentions();
                    ventana.getContMencion().setText("("+eliminacionUserMentions.EliminarMenciones(tweets)+")");
                    break;
                case 4:
                    EliminarAcentos eliminarAcentos = new EliminarAcentos();
                    eliminarAcentos.eliminarAcentos(tweets);
                    break;
                case 5:
                    EtiquetarEmoticones etiquetarEmoticones = new EtiquetarEmoticones();
                    ventana.getContEmo().setText("("+etiquetarEmoticones.etiquetarEmoticones(tweets)+")");
                    break;
                case 6:
                    EliminarCaracteresEspeciales eliminarCaracteresEspeciales = new EliminarCaracteresEspeciales();
                    ventana.getContCarEsp().setText("("+eliminarCaracteresEspeciales.EliminarCarateresEspeciales(tweets)+")");
                    break;
                case 7:
                    EliminacionStopWords eliminacionStopWords = new EliminacionStopWords();
                    this.stopWords = conexion.traerStopWords();
                    eliminacionStopWords.eliminarStopWords(tweets, this.stopWords);
                    break;
                case 8:
                    EtiquetadoPalabrasClaves etiquetadoPalabrasClaves = new EtiquetadoPalabrasClaves();
                    ventana.getContPalCla().setText("("+etiquetadoPalabrasClaves.etiquetar(tweets)+")");
                    break;
                case 9:
                    CorreccionLenguajeCasual correccionCasual = new CorreccionLenguajeCasual(this.conexion);
                    ventana.getContCasual().setText("("+correccionCasual.corregirPalabrasCasuales(tweets)+")");
                    break;
                case 10:
                    Tokenizador tokenizador = new Tokenizador();
                    tokenizador.tokenizar(tweets);//tokenizado y stemmer
                    break;
                case 11:
                    EliminacionPalabrasNoPertinentes epnp = new EliminacionPalabrasNoPertinentes(this.conexion);
                    ventana.getContPocoFrec().setText("("+epnp.eliminarPalabrasRaras(tweets)+")");
                    eliminarEspacios(tweets);
                    break;
            }

        } catch (SQLException ex) {
            System.out.println("ERROR procesar: " + ex.getMessage());
        }
        return tweets;
    }
    //Preprocesamiento para entrenar
    public List<Tweet> procesar(String filtradoClase, List<Tweet> tweets) {
        // System.out.println("PREPARANDO DATOS...");
        try {
//            CorreccionHashTags correccionHastags = new CorreccionHashTags();
            tweets = this.conexion.tweetsEtiquetados(filtradoClase);
            LoweerCase loweerCase = new LoweerCase();
            loweerCase.lowerCasing(tweets);

            EliminacionURLs eliminacionURLs = new EliminacionURLs();
            eliminacionURLs.eliminarURLs(tweets);

            EliminacionUserMentions eliminacionUserMentions = new EliminacionUserMentions();
            eliminacionUserMentions.EliminarMenciones(tweets);

            EliminarAcentos eliminarAcentos = new EliminarAcentos();
            eliminarAcentos.eliminarAcentos(tweets);

            EtiquetarEmoticones etiquetarEmoticones = new EtiquetarEmoticones();
            etiquetarEmoticones.etiquetarEmoticones(tweets);

            EliminarCaracteresEspeciales eliminarCaracteresEspeciales = new EliminarCaracteresEspeciales();
            eliminarCaracteresEspeciales.EliminarCarateresEspeciales(tweets);

            EliminacionStopWords eliminacionStopWords = new EliminacionStopWords();
            this.stopWords = conexion.traerStopWords();
            eliminacionStopWords.eliminarStopWords(tweets, this.stopWords);

            EtiquetadoPalabrasClaves etiquetadoPalabrasClaves = new EtiquetadoPalabrasClaves();
            etiquetadoPalabrasClaves.etiquetar(tweets);

            CorreccionLenguajeCasual correccionCasual = new CorreccionLenguajeCasual(this.conexion);
            correccionCasual.corregirPalabrasCasuales(tweets);

            Tokenizador tokenizador = new Tokenizador();
            tokenizador.tokenizar(tweets);//tokenizado y stemmer

            EliminacionPalabrasNoPertinentes epnp = new EliminacionPalabrasNoPertinentes(this.conexion);
            epnp.eliminarPalabrasRaras(tweets);
            eliminarEspacios(tweets);
        } catch (SQLException ex) {
            System.out.println("ERROR procesar: " + ex.getMessage());
        }
        return tweets;
    }

    private void eliminarEspacios(List<Tweet> tweets) {
        for (Tweet tweet : tweets) {
            String retorno = tweet.getStatus().replaceAll(" +", " ").trim();
            tweet.setStatus(retorno);
        }
    }

    @Deprecated
    public static boolean compararResultados(String bayes, String manual) {
        boolean retorno = false;
        if (bayes.equals("<ng>") && manual.equals("6")) {
            retorno = true;
        } else {
            if (bayes.equals("<bsas>") && manual.equals("1")) {
                retorno = true;
            } else {
                if (bayes.equals("<cal>") && manual.equals("2")) {
                    retorno = true;
                } else {
                    if (bayes.equals("<cat>") && manual.equals("3")) {
                        retorno = true;
                    } else {
                        if (bayes.equals("<salt>") && manual.equals("4")) {
                            retorno = true;
                        } else {
                            if (bayes.equals("<tdf>") && manual.equals("5")) {
                                retorno = true;
                            }
                        }
                    }
                }
            }
        }
        return retorno;
    }

    private void imprimir(ArrayList<Tweet> tweets) {
        for (Tweet tweet : tweets) {
            System.out.print(tweet.getId() + " - ");
            System.out.print(tweet.getStatus() + " - ");
            System.out.println(tweet.getClase());
        }
    }
}
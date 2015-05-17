package tesis.clasificacion.nb;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javax.swing.SwingUtilities;
import tesis.clasificacion.manual.Recolector;
import tesis.preprocesamiento.ControladoraPreProcesamiento;
import tesis.util.DBConexion;
import tesis.util.Tweet;
import tesis.util.TweetEtiquetado;
import tesis.ventanas.ClasificacionGUI;
import tesis.ventanas.PreProcesamientoGUI;

public class ControladoraClasificadorNB {

    private DBConexion conexion;
    private Recolector recolector;
    private ControladoraPreProcesamiento controladoraPreProcesamiento;
    private List<Tweet> tweetsEntrenar;
    private List<Tweet> tweetsClasificar;
    private final Classifier<String, String> bayesClase = new BayesClassifier<>();
    private final Classifier<String, String> bayesPolaridad = new BayesClassifier<>();
    private final Classifier<String, String> bayesClaseSinNinguno = new BayesClassifier<>();
    private Integer clasificados = 0, bsas = 0, cal = 0, cat = 0, salt = 0, tdf = 0, ng = 0, pos = 0, neg = 0, neut = 0;

    public ControladoraClasificadorNB() {
        /*
         * Create a new classifier instance. The context features are Strings and the context will
         * be classified with a String according to the feature set of the context. Las
         * caracter�sticas de la instancia son Strings y la misma se clasificar� con una cadena de
         * caracter�sticas
         */
        this.conexion = new DBConexion();
        this.controladoraPreProcesamiento = new ControladoraPreProcesamiento(conexion);
        this.recolector = new Recolector(conexion);
        bayesClase.setMemoryCapacity(900000000);
        bayesPolaridad.setMemoryCapacity(900000000);
        bayesClaseSinNinguno.setMemoryCapacity(900000000);
//        System.out.println("ENTRENAMIENTO");
//        tweetsEntrenar = controladoraPreProcesamiento.procesar("clase <> '' AND clase <> '7' order by random()", 0);

    }

    public DBConexion getConexion() {
        return conexion;
    }

    public Recolector getRecolector() {
        return recolector;
    }

    public List<Tweet> getTweetsEntrenar() {
        return tweetsEntrenar;
    }

    public void setTweetsEntrenar(List<Tweet> tweetsEntrenar) {
        this.tweetsEntrenar = tweetsEntrenar;
    }

    public List<Tweet> getTweetsClasificar() {
        return tweetsClasificar;
    }

    public void setTweetsClasificar(List<Tweet> tweetsClasificar) {
        this.tweetsClasificar = tweetsClasificar;
    }

    public void entrenarClase() {
        System.out.println("ENTRENANDO DESTINOS TURISTICOS...");
        for (Tweet tweet : this.tweetsEntrenar) {
            tweet.setStatus(tweet.getStatus().trim());
            String[] stringSplit = tweet.getStatus().split("\\s+");
            //System.out.println(tweet.getStatus() + "-" + tweet.getClase());
            switch (tweet.getClase()) {
                case "<bsas>":
                    bayesClase.learn("<bsas>", Arrays.asList(stringSplit));
                    break;
                case "<cal>":
                    bayesClase.learn("<cal>", Arrays.asList(stringSplit));
                    break;
                case "<cat>":
                    bayesClase.learn("<cat>", Arrays.asList(stringSplit));
                    break;
                case "<salt>":
                    bayesClase.learn("<salt>", Arrays.asList(stringSplit));
                    break;
                case "<tdf>":
                    bayesClase.learn("<tdf>", Arrays.asList(stringSplit));
                    break;
                case "<ng>":
                    bayesClase.learn("<ng>", Arrays.asList(stringSplit));
                    break;
                default: {
                    System.out.println("Tweet perdidos: " + tweet.getStatus() + " " + tweet.getClase());
                }
            }
        }
        System.out.println("FIN DEL ENTRENAMIENTO");
    }

    public void entrenarClaseSinNinguno() {
        System.out.println("ENTRENANDO DESTINOS TURISTICOS...");
        for (Tweet tweet : this.tweetsEntrenar) {
            tweet.setStatus(tweet.getStatus().trim());
            String[] stringSplit = tweet.getStatus().split("\\s+");
            //System.out.println(tweet.getStatus() + "-" + tweet.getClase());
            switch (tweet.getClase()) {
                case "<bsas>":
                    bayesClaseSinNinguno.learn("<bsas>", Arrays.asList(stringSplit));
                    break;
                case "<cal>":
                    bayesClaseSinNinguno.learn("<cal>", Arrays.asList(stringSplit));
                    break;
                case "<cat>":
                    bayesClaseSinNinguno.learn("<cat>", Arrays.asList(stringSplit));
                    break;
                case "<salt>":
                    bayesClaseSinNinguno.learn("<salt>", Arrays.asList(stringSplit));
                    break;
                case "<tdf>":
                    bayesClaseSinNinguno.learn("<tdf>", Arrays.asList(stringSplit));
                    break;
                default: {
                    // System.out.println("Tweet perdidos: " + tweet.getStatus() + " " + tweet.getClase());
                }
            }
        }
        System.out.println("FIN DEL ENTRENAMIENTO");
    }

    public void entrenarPolaridad() {
//        System.out.println("ENTRENANDO SENTIMIENTOS...");
        for (Tweet tweet : this.tweetsEntrenar) {
            tweet.setStatus(tweet.getStatus().trim());
            String[] stringSplit = tweet.getStatus().split("\\s+");
            switch (tweet.getPolaridad()) {
                case "1":
                    bayesPolaridad.learn("1", Arrays.asList(stringSplit));
                    break;
                case "2":
                    bayesPolaridad.learn("2", Arrays.asList(stringSplit));
                    break;
                case "3":
                    bayesPolaridad.learn("3", Arrays.asList(stringSplit));
                    break;
                default: {
                    System.out.println("Tweet perdidos: " + tweet.getStatus() + " " + tweet.getClase());
                }
            }
        }
    }

    public void clasificarDestinos(TweetEtiquetado twE, ClasificacionGUI ventana) {
        String formateado;
        twE.getTweet().setStatus(twE.getTweet().getStatus().trim());
        String[] stringSplit = twE.getTweet().getStatus().split("\\s+");
        Classification<String, String> clasificacion = bayesClase.classify(Arrays.asList(stringSplit), twE.getTweet());
        String claseGanadora = clasificacion.getCategory();
        boolean indefinido = (twE.getTweet().getClase().equals("")) ? true : false;
        boolean acierto = (claseGanadora.equals(twE.getTweet().getClase())) ? true : false;
        twE.setClase(claseGanadora);
        twE.setClaseAcierto(acierto);
        twE.setClaseIndefinido(indefinido);
        conexion.cargarTweetClasificado(twE);
        clasificados++;
        //Cargo la informacion a la ventana
        formateado = this.formatear(twE);
        ventana.getListModel().addElement(formateado);
//        ventana.getListModel().addElement(twE.getTweet().getUsername() + " - " + twE.getTweet().getStatusOriginal() + " - " + twE.getClase() + " - " + twE.getPolaridad());
        ventana.getjLabel2().setText(clasificados.toString());
        ventana.getjList1().setModel(ventana.getListModel());
        ventana.getjList1().repaint();
        ventana.updateUI();
    }

    public void clasificarDestinosSinNinguno(TweetEtiquetado twE, ClasificacionGUI ventana) {
        String formateado;
        twE.getTweet().setStatus(twE.getTweet().getStatus().trim());
        String[] stringSplit = twE.getTweet().getStatus().split("\\s+");
        Classification<String, String> clasificacion = bayesClaseSinNinguno.classify(Arrays.asList(stringSplit), twE.getTweet());
        String claseGanadora = clasificacion.getCategory();
        boolean indefinido = (twE.getTweet().getClase().equals("")) ? true : false;
        boolean acierto = (claseGanadora.equals(twE.getTweet().getClase())) ? true : false;
        twE.setClase(claseGanadora);
        twE.setClaseAcierto(acierto);
        twE.setClaseIndefinido(indefinido);
        conexion.cargarTweetClasificado(twE);
        clasificados++;
        //Cargo la informacion a la ventana
        formateado = this.formatear(twE);
        ventana.getListModel().addElement(formateado);
//        ventana.getListModel().addElement(twE.getTweet().getUsername() + " - " + twE.getTweet().getStatusOriginal() + " - " + twE.getClase() + " - " + twE.getPolaridad());
        ventana.getjLabel2().setText(clasificados.toString());
        ventana.getjList1().setModel(ventana.getListModel());
        ventana.getjList1().repaint();
        ventana.updateUI();
    }

    public void clasificarPolaridad(ClasificacionGUI ventana) {
        //conexion.borrarClasificados();
        TweetEtiquetado twE = new TweetEtiquetado();
        System.out.println("CLASIFICANDO...");
        for (Tweet tweet : this.tweetsClasificar) {
            tweet.setStatus(tweet.getStatus().trim());
            String[] stringSplit = tweet.getStatus().split("\\s+");
            Classification<String, String> clasificacion = bayesPolaridad.classify(Arrays.asList(stringSplit), tweet);
            String polaridadGanadora = clasificacion.getCategory();
            boolean polaridadIndefinido = (tweet.getPolaridad().equals("")) ? true : false;
            boolean polaridadAcierto = (polaridadGanadora.equals(tweet.getPolaridad())) ? true : false;
            twE.setTweet(tweet);
            twE.setPolaridad(polaridadGanadora);
            twE.setPolaridadAcierto(polaridadAcierto);
            twE.setPolaridadIndefinido(polaridadIndefinido);
            if (polaridadGanadora.equals("1") | polaridadGanadora.equals("2")) {
                this.clasificarDestinosSinNinguno(twE, ventana);
            } else {
                this.clasificarDestinos(twE, ventana);
            }
            this.contar(twE);
        }
        System.out.println("FIN DE LA CLASIFICACION");
    }

    @Deprecated
    private void cerrarConexion() {
        try {
            this.conexion.cerrarConexion();
        } catch (Exception e) {
            System.out.println("Error cerrar conexion" + e.getMessage());
        }
    }
    public List<Tweet> procesar(String filtradoClase, int codigoProcesar, PreProcesamientoGUI ventana) {
        if (codigoProcesar == 0) {
            return controladoraPreProcesamiento.procesar(filtradoClase, ventana.getListaTweetEntrenar());
        } else {
            return controladoraPreProcesamiento.procesar(filtradoClase, codigoProcesar, ventana.getListaTweetClasificar(), ventana);
        }

    }

    public void clasificar(ClasificacionGUI ventana) {
        System.out.println("CLASIFICACION");
        this.conexion.borrarClasificados();
        ventana.getListModel().addElement("ENTRENANDO DESTINOS...");
        this.entrenarClase();
        this.entrenarClaseSinNinguno();
        ventana.getListModel().addElement("ENTRENANDO SENTIMIENTOS...");
        this.entrenarPolaridad();
        ventana.getListModel().addElement("CLASIFICANDO...");
        this.clasificarPolaridad(ventana);
        ventana.getBsasCla().setText(this.bsas.toString());
        ventana.getCalCla().setText(this.cal.toString());
        ventana.getCatCla().setText(this.cat.toString());
        ventana.getSaltCla().setText(this.salt.toString());
        ventana.getTdfCla().setText(this.tdf.toString());
        ventana.getPosCla().setText(this.pos.toString());
        ventana.getNegCla().setText(this.neg.toString());
        ventana.getNeutCla().setText(this.neut.toString());
        ventana.getNgCla().setText(this.ng.toString());
    }

    private String formatear(TweetEtiquetado twE) {
        StringBuilder formateado = new StringBuilder();
        String clase = "", polaridad = "";
        switch (twE.getClase()) {
            case "<bsas>":
                clase = "BSAS";
                break;
            case "<cal>":
                clase = "CAL";
                break;
            case "<cat>":
                clase = "CAT";
                break;
            case "<salt>":
                clase = "SALT";
                break;
            case "<tdf>":
                clase = "TDF";
                break;
            case "<ng>":
                clase = "NG";
                break;
            default:
            //throw new AssertionError();
        }
        switch (twE.getPolaridad()) {
            case "3":
                polaridad = "NEUTRO";
                break;
            case "1":
                polaridad = "POSITIVO";
                break;
            case "2":
               polaridad = "NEGATIVO";
                break;
            default:
                break;
        }
        formateado.append(clase);
        formateado.append(" | ").append(polaridad);
        formateado.append(" | ").append(twE.getTweet().getLocaldate());
        formateado.append(" | ").append("@").append(twE.getTweet().getUsername());
        formateado.append(": ").append(twE.getTweet().getStatusOriginal());
        return formateado.toString();
    }

    private void contar(TweetEtiquetado twE) {
        switch (twE.getClase()) {
            case "<bsas>":
                this.bsas++;
                break;
            case "<cal>":
                this.cal++;
                break;
            case "<cat>":
                this.cat++;
                break;
            case "<salt>":
                this.salt++;
                break;
            case "<tdf>":
                this.tdf++;
                break;
            case "<ng>":
                this.ng++;
                break;
            default:
                break;
        }
        switch (twE.getPolaridad()) {
            case "1":
                this.pos++;
                break;
            case "2":
                this.neg++;
                break;
            case "3":
                this.neut++;
                break;
            default:
                break;
        }
    }
}

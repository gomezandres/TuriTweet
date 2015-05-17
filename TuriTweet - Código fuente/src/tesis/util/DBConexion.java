/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gomez
 */
public class DBConexion {

    private Connection con;

    public DBConexion() {
        this.abrirConexion();

    }

    /**
     * @param args the command line arguments
     */
//    public float obtenerPeso(String caracteristica, String categoria) {
//        float peso = 1.0f;
//        try {
//            String consulta = "select p.peso from tdf t inner join pesos p on t.id = p.palabras_id inner join clases c on c.id = p.clases_id where c.nombre = '" + categoria + "' and t.palabra = '" + caracteristica + "'";
//            //System.out.println("select p.peso from tdf t inner join pesos p on t.id = p.palabras_id inner join clases c on c.id = p.clases_id where c.nombre = '" + categoria + "' and t.palabra = '" + caracteristica + "'");
//            ResultSet rs = con.createStatement().executeQuery(consulta);
//            if (rs.next()) {
//                peso = rs.getFloat(1);
//                //System.out.println(peso);
//            }
//        } catch (SQLException ex) {
//            System.out.println("ERROR obtener peso: " + ex.getMessage());
//        }
//        return peso;
//    }
    public float obtenerPeso(String caracteristica) {
        float peso = 0.125f;
        try {
//            String consulta = "SELECT peso FROM palabras_completo WHERE clave = true AND lema LIKE '" + caracteristica + "'";
            String consulta = "SELECT peso FROM palabras_clave WHERE lema LIKE '" + caracteristica + "'";
            ResultSet rs = con.createStatement().executeQuery(consulta);
            if (rs.next()) {
                peso = rs.getFloat(1);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(caracteristica);
            System.out.println("ERROR obtener peso: " + ex.getMessage());
            System.out.println("SELECT peso FROM palabras_completo WHERE clave = true AND lema LIKE '" + caracteristica + "'");
        }
        return peso;
    }

//    public void abrirConexion() {
//        try {
//            Class.forName("org.postgresql.Driver");
//            String url = "jdbc:postgresql://localhost:5432/tweets";
//            String user = "postgres";
//            String pass = "admin";
//            if (DBConexion.con == null) {
//                DBConexion.con = DriverManager.getConnection(url, user, pass);
//                this.borrarClasificados();
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            System.out.println("ERROR abrir conexion: " + e.getMessage());
//        }
//    }
    private void abrirConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/tweets";
            String user = "postgres";
            String pass = "admin";
            this.con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(DBConexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getCon() {
        return con;
    }

    public void cerrarConexion() throws SQLException {
        this.con.close();
    }

    public void cargarTweetClasificado(TweetEtiquetado twE) {
        try {
            String consulta = "INSERT INTO tweets_clasificados VALUES(" + twE.getTweet().getId() + ",'" + twE.getClase() + "'," + twE.isClaseAcierto() + "," + twE.isClaseIndefinido() + ",'" + twE.getPolaridad() + "'," + twE.isPolaridadAcierto() + "," + twE.isPolaridadIndefinido() + ",'" + twE.getTweet().getClase() + "')";
            con.createStatement().executeUpdate(consulta);
        } catch (Exception ex) {
            System.out.println("ERROR AL CARGAR EN TABLA tweets_clasificados");
            System.out.println(ex.getMessage());
            System.out.print(twE.getTweet().getId() + " - ");
        }
    }

    public void borrarClasificados() {
        try {
            con.createStatement().executeUpdate("delete from tweets_clasificados");
        } catch (SQLException ex) {
            System.out.println("ERROR borrar clasificados: " + ex.getMessage());
        }
    }

    public List<String> leerPalabrasClaves() {
        List<String> palabrasClave = new ArrayList<>();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT lema FROM palabras_completo WHERE clave = true");
            while (rs.next()) {
                palabrasClave.add(rs.getString(1));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("ERROR leer palabras claves: " + ex.getMessage());
        }

        return palabrasClave;
    }

    public List<String> leerPalabrasRaras() {
        List<String> palabrasRaras = new ArrayList<>();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT palabra FROM palabras_completo WHERE frecuencia < 5");
            while (rs.next()) {
                palabrasRaras.add(rs.getString(1));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("ERROR leer palabras claves: " + ex.getMessage());
        }
        return palabrasRaras;
    }

    public List<Tweet> tweetsEtiquetados(String filtroClases) throws SQLException {
        List<Tweet> tweetsAux = new ArrayList<>();
        try {
            String consulta = "SELECT id, status, clase, user_mentions, polaridad, urls, media, username, local_date FROM tweets WHERE estado = true ";
            if (filtroClases != null) {
                consulta = consulta + " AND " + filtroClases;
            }
            System.out.println(consulta);
            ResultSet rs = con.createStatement().executeQuery(consulta);
            while (rs.next()) {
                Tweet tweetAux = new Tweet();
                tweetAux.setId(Long.parseLong(rs.getString(1)));
                tweetAux.setStatus(rs.getString(2));
                tweetAux.setStatusOriginal(rs.getString(2));
                tweetAux.setClase(rs.getString(3));
                tweetAux.setUser_mentions(rs.getString(4));
                tweetAux.setPolaridad(rs.getString(5));
                if (!rs.getString(6).equals("")) {
                    tweetAux.setUrl(true);
                }
                if (!rs.getString(7).equals("")) {
                    tweetAux.setMedia(true);
                }
                tweetAux.setUsername(rs.getString(8));
                tweetAux.setLocaldate(rs.getString(9));
                tweetsAux.add(tweetAux);
            }
            rs.close();
        } catch (SQLException | NumberFormatException e) {
            System.out.println("ERROR tweets clasificados: " + e.getMessage());
        }
        return tweetsAux;
    }

    public List<String> traerStopWords() {
        List<String> strings = new ArrayList<>();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT palabra FROM stopwords WHERE estado = true ORDER BY palabra");
            while (rs.next()) {
                strings.add(rs.getString(1));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("ERROR traer stopWords: " + e.getMessage());
        }
        return strings;
    }

    @Deprecated
    public List<Tweet> consultaTweets() {
        List<Tweet> tweets = new ArrayList<>();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT status, clase, user_mentions FROM tweets WHERE estado = true ");
            while (rs.next()) {
                Tweet tweetAux = new Tweet();
                tweetAux.setStatus(rs.getString(1));
                tweetAux.setClase(rs.getString(2));
                tweetAux.setUser_mentions(rs.getString(3));
                tweets.add(tweetAux);
            }
            rs.close();
        } catch (Exception ex) {
            System.out.println("ERROR consulta tweets: " + ex.getMessage());
        }
        return tweets;
    }

    @Deprecated
    public ArrayList<String> consultaCaracteresEspeciales() {
        ArrayList<String> strings = new ArrayList<>();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT caracter FROM caracteres_especiales WHERE estado = true");
            while (rs.next()) {
                strings.add(rs.getString(1));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("ERROR consulta caracteres clases: " + e.getMessage());
        }
        return strings;
    }

    //Carga las palabras limpias con sus lemas en una tabla 
    @Deprecated
    public void cargarLemas(String palabra, String lema) {
        try {
            String consulta = "INSERT INTO palabras_lemas (palabra, lema) VALUES('" + palabra + "', '" + lema + "')";
            con.createStatement().executeUpdate(consulta);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<PalabraSMS> leerDiccionarioSms() {
        List<PalabraSMS> listaPalabras = new ArrayList<>();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT casual, correcta FROM diccionario_sms");
            while (rs.next()) {
                listaPalabras.add(new PalabraSMS(rs.getString(1), rs.getString(2)));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("ERROR leer palabras claves: " + ex.getMessage());
        }
        return listaPalabras;
    }
}

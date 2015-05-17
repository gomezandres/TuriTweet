/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.clasificacion.manual;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import tesis.util.DBConexion;
import tesis.util.Tweet;

/**
 *
 * @author tebs
 */
public class Recolector {

    private DBConexion conexion;
    private Connection con;
    private Tweet tweet = new Tweet();

    public Recolector(DBConexion conexion) {
        this.conexion = conexion;
        con = conexion.getCon();
    }

    public Tweet leerTweets(String nombreTabla) throws Exception {
        try {
            String consultaLeer = "SELECT id, status, utc_date, local_date, location, timezone, username FROM " + nombreTabla + " WHERE clase LIKE '' AND estado = true ORDER BY RANDOM()";
            ResultSet result = con.createStatement().executeQuery(consultaLeer);
            result.next();
            tweet.setId(result.getLong(1));
            tweet.setStatus(result.getString(2).replaceAll("\\'", ""));
            tweet.setUtcdate(result.getString(3));
            tweet.setLocaldate(result.getString(4));
            tweet.setLocation(result.getString(5));
            tweet.setTimezone(result.getString(6));
            tweet.setUsername(result.getString(7));
        } catch (SQLException e) {
            throw new Exception("El tweet " + tweet + " no se pudo cargar");
        }
        return tweet;
    }

    public void etiquetarTweets(String nombreTabla, Tweet tw, boolean[] etiquetas, boolean[] polaridades, String nroClasificacion) throws Exception {
        String clase = "";
        String polaridad = "3";
        try {
            clase = this.armarEtiqueta(etiquetas);
            polaridad = this.armarPolaridad(polaridades);
            String consultaEtiquetar = "UPDATE " + nombreTabla + " set status = '" + tw.getStatus() + "', clase = '" + clase + "', polaridad = " + polaridad + ", nroclasificacion = " + nroClasificacion + " WHERE id = " + tw.getId();
            System.out.println("UPDATE " + nombreTabla + " set status = '" + tw.getStatus() + "', clase = '" + clase + "', polaridad = " + polaridad + ", nroclasificacion = " + nroClasificacion + " WHERE id = " + tw.getId());
            con.createStatement().executeUpdate(consultaEtiquetar);
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }

    private String armarEtiqueta(boolean[] etiquetas) {
        String etiqueta = "";
        if (etiquetas[0]) {
            etiqueta = "<bsas>";
        } else {
            if (etiquetas[1]) {
                etiqueta = "<cal>";
            } else {
                if (etiquetas[2]) {
                    etiqueta = "<cat>";
                } else {
                    if (etiquetas[3]) {
                        etiqueta = "<salt>";
                    } else {
                        if (etiquetas[4]) {
                            etiqueta = "<tdf>";
                        } else {
                            if (etiquetas[5]) {
                                etiqueta = "<ng>";
                            } else {
                                if (etiquetas[6]) {
                                    etiqueta = "7";
                                }
                            }
                        }
                    }
                }
            }
        }
        return etiqueta;
    }

    private String armarPolaridad(boolean[] polaridades) {
        String polaridad = "3";
        if (polaridades[0]) {
            polaridad = "1";
        }
        if (polaridades[1]) {
            polaridad = "2";
        }
        if (polaridades[2]) {
            polaridad = "3";
        }
        return polaridad;
    }

    public void eliminarTweet(String nombreTabla, String id) throws Exception {
        try {
            String consultaLeer = "UPDATE " + nombreTabla + " set estado = false WHERE id = " + id;
            con.createStatement().executeUpdate(consultaLeer);
        } catch (SQLException e) {
            throw new Exception("El tweet " + tweet + " no se pudo eliminar");
        }
    }

    public String[] contarEtiquetados() throws Exception {
        String[] totales = new String[8];
        try {
            String consultaContar = "select(select count(*)  from tweets where clase = '<bsas>')bsas,(select count(*)  from tweets where clase = '<cal>')cal,(select count(*)  from tweets where clase = '<cat>')cat,(select count(*)  from tweets where clase = '<salt>')salt,(select count(*)  from tweets where clase = '<tdf>')tdf,(select count(*) from tweets where clase = '<ng>')ng,(select count(*) from tweets where clase <> '' and clase <> '7')tot,(select count(*) from tweets where estado = true)totGral;";
            ResultSet rs = con.createStatement().executeQuery(consultaContar);
            rs.next();
            totales[0] = rs.getString(1);
            totales[1] = rs.getString(2);
            totales[2] = rs.getString(3);
            totales[3] = rs.getString(4);
            totales[4] = rs.getString(5);
            totales[5] = rs.getString(6);
            totales[6] = rs.getString(7);
            totales[7] = rs.getString(8);
            rs.close();
        } catch (SQLException e) {
            throw new Exception("Error al contar las etiquetas" + e.getMessage());
        }
        return totales;
    }

    public String contarTotal() throws SQLException {
        String total = "";
        try {
            String contar = "select count(*) from tweets where estado = true";
            ResultSet rs = con.createStatement().executeQuery(contar);
            rs.next();
            total = rs.getString(1);
            rs.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return total;
    }

    public String[] contarValidados() throws Exception {
        String[] totales = new String[8];
        try {
            String consultaContar = "select"
                    + "(select count(*)  from tweets_validados where clase_ganadora = '<bsas>')bsas,"
                    + "(select count(*)  from tweets_validados where clase_ganadora = '<cal>')cal,"
                    + "(select count(*)  from tweets_validados where clase_ganadora = '<cat>')cat,"
                    + "(select count(*)  from tweets_validados where clase_ganadora = '<salt>')salt,"
                    + "(select count(*)  from tweets_validados where clase_ganadora = '<tdf>')tdf,"
                    + "(select count(*) from tweets_validados where clase_ganadora = '<ng>')ng,"
                    + "(select count(*) from tweets_validados where clase_ganadora <> '')tot;";
            ResultSet rs = con.createStatement().executeQuery(consultaContar);
            rs.next();
            totales[0] = rs.getString(1);
            totales[1] = rs.getString(2);
            totales[2] = rs.getString(3);
            totales[3] = rs.getString(4);
            totales[4] = rs.getString(5);
            totales[5] = rs.getString(6);
            totales[6] = rs.getString(7);
            //totales[7] = rs.getString(8);
            rs.close();
        } catch (SQLException e) {
            throw new Exception("Error al contar las etiquetas" + e.getMessage());
        }
        return totales;
    }

    public void validarTweets(String nombreTabla, Tweet tw, boolean aciertoClase, boolean aciertoPolaridad) throws Exception {
        try {
            System.out.println("INSERT INTO tweets_validados values (" + tw.getId() + ", '" + tw.getClase() + "', " + aciertoClase + ", '" + tw.getPolaridad() + "', " + aciertoPolaridad + ")");
            String consultaValidar = "INSERT INTO tweets_validados values (" + tw.getId() + ", '" + tw.getClase() + "', " + aciertoClase + ", '" + tw.getPolaridad() + "', " + aciertoPolaridad + ")";
            String consultaActualizar = "UPDATE tweets_clasificados SET validado = true WHERE id = " + tw.getId();
            con.createStatement().executeUpdate(consultaValidar);
            con.createStatement().executeUpdate(consultaActualizar);
        } catch (SQLException e) {
            throw new Exception("Error al clasificar" + e.getMessage());

        }
    }

    public Tweet leerTweetsIndefinidos(String nombreTabla) throws Exception {
        try {
            //String consultaLeer = "SELECT tw.id, tw.status, tw.utc_date, tw.local_date, tw.location, tw.timezone, tw.username, tc.clase,  tc.polaridad FROM tweets tw, tweets_clasificados tc WHERE tw.clase LIKE '' AND tc.indefinido = true AND tw.estado = true AND tw.id = tc.id AND tc.validado = false ORDER BY RANDOM()";
            String consultaLeer = "SELECT tw.id, tw.status, tw.utc_date, tw.local_date, tw.location, tw.timezone, tw.username, tc.clase,  tc.polaridad FROM tweets tw, tweets_clasificados tc, tweets_validados tv WHERE tw.id = tc.id AND tw.id = tv.id AND tw.clase LIKE '' AND tc.indefinido = true AND tw.estado = true AND tw.id = tc.id AND tc.acierto = false and tv.acierto_clase = false ORDER BY RANDOM()"; //con filtro de validados erroneos
            ResultSet result = con.createStatement().executeQuery(consultaLeer);
            result.next();
            tweet.setId(result.getLong(1));
            tweet.setStatus(result.getString(2).replaceAll("\\'", ""));
            tweet.setUtcdate(result.getString(3));
            tweet.setLocaldate(result.getString(4));
            tweet.setLocation(result.getString(5));
            tweet.setTimezone(result.getString(6));
            tweet.setUsername(result.getString(7));
            tweet.setClase(result.getString(8));
            tweet.setPolaridad(this.descripcionPolaridad(result.getString(9)));
            result.close();
        } catch (SQLException e) {
            throw new Exception("El tweet " + tweet + " no se pudo cargar: " + e.getMessage());
        }
        return tweet;
    }

    public void corregirClase(String claseCorrecta, Long id) throws Exception {
        try {
            //String consultaLeer = "SELECT tw.id, tw.status, tw.utc_date, tw.local_date, tw.location, tw.timezone, tw.username, tc.clase,  tc.polaridad FROM tweets tw, tweets_clasificados tc WHERE tw.clase LIKE '' AND tc.indefinido = true AND tw.estado = true AND tw.id = tc.id AND tc.validado = false ORDER BY RANDOM()";
            String consultaCorregir = "UPDATE tweets_validados SET clase_correcta = '" + claseCorrecta + "' WHERE id = " + id; //con filtro de validados erroneos
            System.out.println("UPDATE tweets_validados SET clase_correcta = '" + claseCorrecta + "' WHERE id = " + id);
            con.createStatement().executeUpdate(consultaCorregir);
        } catch (SQLException e) {
            throw new Exception("El tweet " + tweet + " no se pudo corregir: " + e.getMessage());
        }
    }

    private String descripcionPolaridad(String string) {
        String polaridad = "neutral";
        if (string.equals("1")) {
            polaridad = "positivo";
        }
        if (string.equals("2")) {
            polaridad = "negativo";
        }
        return polaridad;
    }
}

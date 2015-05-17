/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author unicef
 */
public class StatusToPosgres {

    private Long idTweet = 1L;

    public void cargar(Connection con, ArrayList<Tweet> tweets) {
        for (Tweet tweet : tweets) {
            this.cargarTweet(con, tweet.getId(), tweet.getStatus());
        }
    }

    public void cargarTweet(Connection con, Long id, String status) {
        try {
            PreparedStatement pst = null;
            String stm = "INSERT INTO historico_tweets (idTweet,id,status) VALUES(" + idTweet + "," + id + ",'" + status + "')";
            pst = con.prepareStatement(stm);
            pst.executeUpdate();
            idTweet++;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

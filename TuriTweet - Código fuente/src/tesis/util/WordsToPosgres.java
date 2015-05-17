/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author unicef
 */
public class WordsToPosgres {

    private Long id = 0L;

    public void cargarPalabra(Connection con, String status) {
        try {
//            String textoDividido[] = status.split(" ");
            String textoDividido[] = status.split("\\s+");
            List<String> wordList = Arrays.asList(textoDividido);
            PreparedStatement pst = null;
            for (String cadena : wordList) {
                this.id++;
                System.out.println(id);
                String stm = "INSERT INTO palabras (id,palabra) VALUES(" + id + ",'" + cadena + "')";
                pst = con.prepareStatement(stm);
                pst.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}

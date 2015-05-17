/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.init;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import tesis.ventanas.AdministracionGUI;

/**
 *
 * @author federico
 */
public class ControladoraPrincipal {

    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            AdministracionGUI administracionGUI = new AdministracionGUI();
            administracionGUI.pack();
            administracionGUI.setExtendedState(JFrame.MAXIMIZED_BOTH);
            administracionGUI.setVisible(true);
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(ControladoraPrincipal.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}

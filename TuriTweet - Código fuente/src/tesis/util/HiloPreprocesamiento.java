/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.util;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import tesis.ventanas.PreProcesamientoGUI;

/**
 *
 * @author tebs
 */
public class HiloPreprocesamiento extends Thread {

    private int codigoProcesar;
    private JLabel label;
    private PreProcesamientoGUI frame;
    

    /**
     *
     * @param codigoProcesar // codigo de identificacion de cada heuristica
     * @param label //bandera de estado imagen heiuristica
     * @param frame //ventana que llama al hilo
     */
    public HiloPreprocesamiento(int codigoProcesar, JLabel label, PreProcesamientoGUI frame) {
        this.codigoProcesar = codigoProcesar;
        this.label = label;
        this.frame = frame;   
    }

    @Override
    public void run() {
        try {
            this.frame.setListaTweetClasificar(frame.getControladora().procesar("clase = '' order by random()", codigoProcesar,
                    this.frame));                    
            this.label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tesis/ventanas/activo.png")));
            this.frame.getjPanel1().repaint();
            this.frame.getjPanel1().revalidate();
            this.frame.getjPanel1().updateUI();
        } catch (Exception e) {
        }
    }
}

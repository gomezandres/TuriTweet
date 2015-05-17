/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author unicef
 */
public class Archivo {

    private File outFile;
    private BufferedWriter writer;

    public void abrirArchivo() {
        try {
            this.outFile = new File("C:\\ResultadosClasificador.csv");
//            this.outFile = new File("/home/unicef/ResultadosClasificador.csv");
            this.writer = new BufferedWriter(new FileWriter(outFile));
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void escribirArchivo(String cadena) {
        try {
            writer.write(cadena);
            writer.newLine(); // Esto es un salto de linea
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public void cerrarArchivo() {
        try {
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

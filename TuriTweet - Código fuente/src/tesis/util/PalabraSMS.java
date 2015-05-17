/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.util;

/**
 *
 * @author Tebs
 */
public class PalabraSMS {
    private String palabraMala;
    private String palabraBuena;

    public PalabraSMS(String palabraMala, String palabraBuena) {
        this.palabraMala = palabraMala;
        this.palabraBuena = palabraBuena;
    }

    public String getPalabraBuena() {
        return palabraBuena;
    }

    public void setPalabraBuena(String palabraBuena) {
        this.palabraBuena = palabraBuena;
    }

    public String getPalabraMala() {
        return palabraMala;
    }

    public void setPalabraMala(String palabraMala) {
        this.palabraMala = palabraMala;
    }
    
    
}

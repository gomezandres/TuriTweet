/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EtiquetadoManual.java
 *
 * Created on 08-abr-2014, 19:11:22
 */
package tesis.ventanas;

import java.util.logging.Level;
import java.util.logging.Logger;
import tesis.clasificacion.manual.Recolector;
import tesis.clasificacion.nb.ControladoraClasificadorNB;
import tesis.util.Tweet;

/**
 *
 * @author tebs
 */
public class ValidacionManualGUI extends javax.swing.JInternalFrame {

    private Tweet tweet;
    private ControladoraClasificadorNB controladora;
    private Integer contar = 0;
    private boolean aciertoClase = false;
    private boolean aciertoPolaridad = false;
    private String totalesEtiquetas[];
    private String bd = "tweets";

    /**
     * Creates new form EtiquetadoManual
     */
    public ValidacionManualGUI(ControladoraClasificadorNB controladora) {
        try {
            initComponents();
            this.controladora = controladora;
            this.iniciar();
            totalesEtiquetas = controladora.getRecolector().contarValidados();
            this.contadorBsAs.setText(totalesEtiquetas[0]);
            this.contadorCal.setText(totalesEtiquetas[1]);
            this.contadorCat.setText(totalesEtiquetas[2]);
            this.contadorSalt.setText(totalesEtiquetas[3]);
            this.contadorTdf.setText(totalesEtiquetas[4]);
            this.contadorNg.setText(totalesEtiquetas[5]);
            //this.contadorTot.setText(totalesEtiquetas[6]);
            //this.contadorTotGral.setText(totalesEtiquetas[7]);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code.
     * The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        clasePanel = new javax.swing.JPanel();
        claseGanadora = new javax.swing.JTextField();
        validacionClase = new javax.swing.JComboBox();
        polaridadPanel = new javax.swing.JPanel();
        polaridadGanadora = new javax.swing.JTextField();
        validacionPolaridad = new javax.swing.JComboBox();
        validar = new javax.swing.JButton();
        saltar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        idTweet = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tweetsArea = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        fechaUtc = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        fechaLocal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        location = new javax.swing.JTextField();
        timezone = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        valClasificados = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        contadorBsAs = new javax.swing.JLabel();
        contadorCal = new javax.swing.JLabel();
        valEliminados = new javax.swing.JLabel();
        contadorSalt = new javax.swing.JLabel();
        contadorCat = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        contadorTdf = new javax.swing.JLabel();
        contadorNg = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        corregirClase = new javax.swing.JToggleButton();
        claseCorregida = new javax.swing.JComboBox();

        setForeground(java.awt.Color.white);
        setTitle(".:. Clasificador Tweets .:.");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/tesis/ventanas/Twitter-Shipping-Box-32.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Panel de Validación"));

        clasePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Clase"));
        clasePanel.setEnabled(false);

        validacionClase.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CORRECTO", "INCORRECTO" }));

        javax.swing.GroupLayout clasePanelLayout = new javax.swing.GroupLayout(clasePanel);
        clasePanel.setLayout(clasePanelLayout);
        clasePanelLayout.setHorizontalGroup(
            clasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clasePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(claseGanadora, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(validacionClase, 0, 129, Short.MAX_VALUE)
                .addContainerGap())
        );
        clasePanelLayout.setVerticalGroup(
            clasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clasePanelLayout.createSequentialGroup()
                .addGroup(clasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(claseGanadora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validacionClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        polaridadPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Polaridad"));
        polaridadPanel.setEnabled(false);

        validacionPolaridad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CORRECTO", "INCORRECTO" }));

        javax.swing.GroupLayout polaridadPanelLayout = new javax.swing.GroupLayout(polaridadPanel);
        polaridadPanel.setLayout(polaridadPanelLayout);
        polaridadPanelLayout.setHorizontalGroup(
            polaridadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(polaridadPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(polaridadGanadora, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(validacionPolaridad, 0, 123, Short.MAX_VALUE)
                .addContainerGap())
        );
        polaridadPanelLayout.setVerticalGroup(
            polaridadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(polaridadPanelLayout.createSequentialGroup()
                .addGroup(polaridadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(polaridadGanadora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validacionPolaridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        validar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tesis/ventanas/Check-32.png"))); // NOI18N
        validar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validarActionPerformed(evt);
            }
        });

        saltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tesis/ventanas/Undo-32.png"))); // NOI18N
        saltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saltarActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tesis/ventanas/Close-32.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setText("ID:");

        idTweet.setEditable(false);

        jLabel8.setText("@");

        username.setEditable(false);
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });

        tweetsArea.setColumns(120);
        tweetsArea.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        tweetsArea.setForeground(new java.awt.Color(0, 0, 153));
        tweetsArea.setRows(1);
        tweetsArea.setAutoscrolls(false);
        tweetsArea.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tweetsAreaAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(tweetsArea);

        jLabel9.setText("Fecha UTC:");

        fechaUtc.setEditable(false);

        jLabel10.setText("Fecha Loc:");

        fechaLocal.setEditable(false);

        jLabel11.setText("Locación:");

        location.setEditable(false);

        timezone.setEditable(false);

        jLabel12.setText("Timezone:");

        jLabel13.setText("Bs. As.:");

        jLabel15.setText("Salta:");

        jLabel14.setText("Calafate:");

        valClasificados.setText("0");

        jLabel5.setText("Validados");

        contadorBsAs.setText("xxxx");

        contadorCal.setText("xxxx");

        valEliminados.setText("0");

        contadorSalt.setText("xxxx");

        contadorCat.setText("xxxx");

        jLabel18.setText("T. del Fuego:");

        jLabel2.setText("Ninguno:");

        contadorTdf.setText("xxxx");

        contadorNg.setText("xxxx");

        jLabel1.setText("Cataratas:");

        jLabel7.setText("Clasificados:");

        corregirClase.setText("CORREGIR");
        corregirClase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                corregirClaseActionPerformed(evt);
            }
        });

        claseCorregida.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<bsas>", "<cal>", "<cat>", "<salt>", "<tdf>", "<ng>" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(idTweet, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(689, 689, 689))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(fechaUtc)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaLocal)
                        .addGap(788, 788, 788))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valClasificados, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valEliminados, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(location, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(jLabel12)
                                .addGap(12, 12, 12)
                                .addComponent(timezone, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(clasePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(polaridadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(claseCorregida, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(corregirClase)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(validar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(saltar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(74, 74, 74))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contadorBsAs, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contadorCal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(contadorCat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contadorSalt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addGap(6, 6, 6)
                .addComponent(contadorTdf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contadorNg, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(clasePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(polaridadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(validar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(saltar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(idTweet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(corregirClase, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(claseCorregida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(fechaUtc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(fechaLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timezone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(valClasificados)
                        .addComponent(jLabel7)
                        .addComponent(valEliminados))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(contadorCat)
                        .addComponent(jLabel15)
                        .addComponent(contadorSalt)
                        .addComponent(jLabel18)
                        .addComponent(contadorTdf)
                        .addComponent(jLabel13)
                        .addComponent(contadorBsAs)
                        .addComponent(jLabel14)
                        .addComponent(contadorCal)
                        .addComponent(jLabel2)
                        .addComponent(contadorNg)))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tweetsAreaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tweetsAreaAncestorAdded
    }//GEN-LAST:event_tweetsAreaAncestorAdded

    private void validarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validarActionPerformed
        try {
            try {
                this.tweet.setStatus(this.tweetsArea.getText());
                this.idTweet.setText(this.tweet.getId().toString());
                if (this.validacionClase.getModel().getSelectedItem().toString().equals("CORRECTO")) {
                    this.aciertoClase = true;
                } else {
                    this.aciertoClase = false;
                }
                if (this.validacionPolaridad.getModel().getSelectedItem().toString().equals("CORRECTO")) {
                    this.aciertoPolaridad = true;
                } else {
                    this.aciertoPolaridad = false;
                }
                controladora.getRecolector().validarTweets(this.bd, this.tweet, this.aciertoClase, this.aciertoPolaridad);
                totalesEtiquetas = controladora.getRecolector().contarValidados();
                this.contadorBsAs.setText(totalesEtiquetas[0]);
                this.contadorCal.setText(totalesEtiquetas[1]);
                this.contadorCat.setText(totalesEtiquetas[2]);
                this.contadorSalt.setText(totalesEtiquetas[3]);
                this.contadorTdf.setText(totalesEtiquetas[4]);
                this.contadorNg.setText(totalesEtiquetas[5]);
                //this.contadorTot.setText(totalesEtiquetas[6]);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            contar++;
            this.tweet = controladora.getRecolector().leerTweetsIndefinidos(this.bd);
            this.idTweet.setText(tweet.getId().toString());
            this.valClasificados.setText(contar.toString());
            this.polaridadPanel.setEnabled(true);
            this.username.setText(tweet.getUsername());
            this.fechaLocal.setText(tweet.getLocaldate());
            this.fechaUtc.setText(tweet.getUtcdate());
            this.location.setText(tweet.getLocation());
            this.timezone.setText(tweet.getTimezone());
            this.tweetsArea.setText(tweet.getStatus());
            this.claseGanadora.setText(tweet.getClase());
            this.polaridadGanadora.setText(tweet.getPolaridad());
        } catch (Exception ex) {
            Logger.getLogger(ValidacionManualGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_validarActionPerformed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void saltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saltarActionPerformed
        try {
            this.tweet = controladora.getRecolector().leerTweetsIndefinidos(this.bd);
            this.tweetsArea.setText(tweet.getStatus());
            this.idTweet.setText(this.tweet.getId().toString());
            this.username.setText(tweet.getUsername());
            this.fechaLocal.setText(tweet.getLocaldate());
            this.fechaUtc.setText(tweet.getUtcdate());
            this.location.setText(tweet.getLocation());
            this.timezone.setText(tweet.getTimezone());
            this.claseGanadora.setText(tweet.getClase());
            this.polaridadGanadora.setText(tweet.getPolaridad());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_saltarActionPerformed

    private void corregirClaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_corregirClaseActionPerformed
        try {
            controladora.getRecolector().corregirClase(this.claseCorregida.getModel().getSelectedItem().toString(), this.tweet.getId());
            this.tweet = controladora.getRecolector().leerTweetsIndefinidos(this.bd);
            this.idTweet.setText(tweet.getId().toString());
            this.valClasificados.setText(contar.toString());
            this.polaridadPanel.setEnabled(true);
            this.username.setText(tweet.getUsername());
            this.fechaLocal.setText(tweet.getLocaldate());
            this.fechaUtc.setText(tweet.getUtcdate());
            this.location.setText(tweet.getLocation());
            this.timezone.setText(tweet.getTimezone());
            this.tweetsArea.setText(tweet.getStatus());
            this.claseGanadora.setText(tweet.getClase());
            this.polaridadGanadora.setText(tweet.getPolaridad());
        } catch (Exception ex) {
            Logger.getLogger(ValidacionManualGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_corregirClaseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox claseCorregida;
    public javax.swing.JTextField claseGanadora;
    private javax.swing.JPanel clasePanel;
    private javax.swing.JLabel contadorBsAs;
    private javax.swing.JLabel contadorCal;
    private javax.swing.JLabel contadorCat;
    private javax.swing.JLabel contadorNg;
    private javax.swing.JLabel contadorSalt;
    private javax.swing.JLabel contadorTdf;
    private javax.swing.JToggleButton corregirClase;
    private javax.swing.JTextField fechaLocal;
    private javax.swing.JTextField fechaUtc;
    private javax.swing.JTextField idTweet;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField location;
    public javax.swing.JTextField polaridadGanadora;
    private javax.swing.JPanel polaridadPanel;
    private javax.swing.JButton saltar;
    private javax.swing.JTextField timezone;
    private javax.swing.JTextArea tweetsArea;
    private javax.swing.JTextField username;
    private javax.swing.JLabel valClasificados;
    private javax.swing.JLabel valEliminados;
    private javax.swing.JComboBox validacionClase;
    private javax.swing.JComboBox validacionPolaridad;
    private javax.swing.JButton validar;
    // End of variables declaration//GEN-END:variables

    private void iniciar() {
        try {
            this.claseCorregida.setVisible(false);
            this.corregirClase.setVisible(false);
            this.clasePanel.setEnabled(true);
            this.polaridadPanel.setEnabled(true);
            this.tweet = controladora.getRecolector().leerTweetsIndefinidos(this.bd);
            this.tweetsArea.setText(tweet.getStatus());
            this.idTweet.setText(this.tweet.getId().toString());
            this.username.setText(tweet.getUsername());
            this.fechaLocal.setText(tweet.getLocaldate());
            this.fechaUtc.setText(tweet.getUtcdate());
            this.location.setText(tweet.getLocation());
            this.timezone.setText(tweet.getTimezone());
            this.claseGanadora.setText(tweet.getClase());
            this.polaridadGanadora.setText(tweet.getPolaridad());
        } catch (Exception ex) {
            Logger.getLogger(ValidacionManualGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
 
import Controlador.ControladorChat;
import Sockets.Cliente;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Taty
 */
public class Chat extends javax.swing.JFrame {

    private ControladorChat controladorC;

    public Chat(Cliente client) {
        initComponents();
        controladorC = new ControladorChat(client, this);
        client.setControladorC(controladorC);
        controladorC.runCliente();
        this.setLocationRelativeTo(null);
    }

    public void recibirMensahe(String mensaje) {
        historialTx.setText(historialTx.getText() + "\n" + mensaje);
    }
 
    public void enviarMensaje() throws Exception {
        if (!enviarTx.getText().equals("")) {
            //  return enviarTx.getText();
            controladorC.enviarMensaje(enviarTx.getText());
            enviarTx.setText("");
        } else {
            throw new Exception("Debe escribir algo");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        historialTx = new javax.swing.JTextArea();
        enviarTx = new javax.swing.JTextField();
        enviarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        historialTx.setEditable(false);
        historialTx.setBackground(java.awt.SystemColor.controlHighlight);
        historialTx.setColumns(20);
        historialTx.setRows(5);
        jScrollPane1.setViewportView(historialTx);

        enviarTx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarTxActionPerformed(evt);
            }
        });

        enviarBtn.setBackground(new java.awt.Color(0, 153, 153));
        enviarBtn.setText("Enviar");
        enviarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(enviarTx, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(enviarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enviarTx, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enviarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enviarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarBtnActionPerformed
        try {
            enviarMensaje();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_enviarBtnActionPerformed

    private void enviarTxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarTxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enviarTxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton enviarBtn;
    private javax.swing.JTextField enviarTx;
    private javax.swing.JTextArea historialTx;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

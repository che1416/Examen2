/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Sockets.Cliente;
import Vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author tati
 */
public class ControladorVentanaPrincipal implements ActionListener {

    private VentanaPrincipal princ;
    Cliente client;

    public Cliente getClient() {
        return client;
    }

    public ControladorVentanaPrincipal(VentanaPrincipal princ) {
        this.princ = princ;
    }

    public void crearConexion(int i) throws Exception {
        String ip = princ.getIP();
        String nombre = princ.getNombre();
        client = new Cliente(nombre, ip,i);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
//            crearConexion();
            switch (ae.getActionCommand()) {
                case "difusionBtn":
                    crearConexion(0);
                    break;
                case "privadoBtn":
                    crearConexion(1);
                    break;
                case "grupalBtn":
                    crearConexion(1);
                    break;
                default:
                    throw new Exception("No existe el boton");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}

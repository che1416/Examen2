/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hilos;

import Controlador.ControladorChat;
import Sockets.Cliente;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tati
 */
public class RecibirMsjCliente extends Thread {

    private DataInputStream input;
    private Cliente client;
    private Socket connexion;
    private ControladorChat controladorC;
    private boolean desconectado = false;

    public RecibirMsjCliente(DataInputStream input, Cliente client, Socket connexion, ControladorChat controlC) {
        this.input = input;
        this.client = client;
        this.connexion = connexion;
        this.controladorC = controlC;
    }

    @Override
    public void run() {
        try {
            procesarConexion();
        } catch (IOException ex) {
            Logger.getLogger(RecibirMsjCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void recibirMensahe(String msj) {
        controladorC.recibirMensahe(msj);
    }

    private void procesarConexion() throws IOException {
        while (!desconectado) {
            String text = input.readUTF();
            recibirMensahe(text);
        }
    }

    public void cerrarConexion() {
        desconectado = true;
        client.closeConnection();

    }

}

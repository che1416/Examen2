/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sockets;

import Controlador.ControladorChat;
import Hilos.RecibirMsjCliente;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author tati
 */
public class Cliente {
    private DataOutputStream output;
    private DataInputStream input;
    private Socket client;
    private  String host = "127.0.0.1";//localhost
    private final int PORT = 12345;
    private String nombre;
    private ControladorChat controladorC;
    private RecibirMsjCliente hilo;

    public Cliente(String nombre, String ip) {
        this.nombre = nombre;
        this.host=ip;
    }

    public void setControladorC(ControladorChat controladorC) {
        this.controladorC = controladorC;
    }

  

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    

    public void runClient() {
        try {
            connectToServer();
            getStreams();
            hilo=new RecibirMsjCliente(input, this, client, controladorC);
            hilo.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void connectToServer() throws IOException {
        System.out.println("Esperando conexion\n");
        client = new Socket(host, PORT);
        System.out.println("Conectado a : " + client.getInetAddress().getHostName());
    }

    private void getStreams() throws IOException {
        output = new DataOutputStream(client.getOutputStream());
        output.flush();
        input = new DataInputStream(client.getInputStream());
    }

    public void closeConnection() {
        System.out.println("\nCerrando conexion");
        try {
            output.close();
            input.close();
            client.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void enviarMensaje(String mensaje) throws IOException{
        output.writeUTF(mensaje); 
    }
}

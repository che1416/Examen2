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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author tati
 */
public class Cliente {

    private DataOutputStream output;
    private DataInputStream input;
    private ObjectOutputStream outputObj;
    private ObjectInputStream inputObj;
    private Socket client;
    private String host = "127.0.0.1";//localhost
    private final int PORT = 12345;
    private String nombre;
    private ControladorChat controladorC;
    private RecibirMsjCliente hilo;
    private int opcionChat;
    
    public Cliente(String nombre, String ip,int opChat) {
        this.nombre = nombre;
        this.host = ip;
        this.opcionChat=opChat;
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
            hilo = new RecibirMsjCliente(input, this, client, controladorC);
            hilo.start();
            enviarMensaje(nombre);
            enviarOpcionChat();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void connectToServer() throws IOException {
        System.out.println("Esperando conexion\n");
        client = new Socket(host, PORT);
        System.out.println(getNombre() + " " + "se ha conectado a : " + client.getInetAddress().getHostName());
    }

    private void getStreams() throws IOException {
        output = new DataOutputStream(client.getOutputStream());
        output.flush();
        input = new DataInputStream(client.getInputStream());
         inputObj = new ObjectInputStream(client.getInputStream());
        outputObj = new ObjectOutputStream(client.getOutputStream());
        outputObj.flush();
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

    public void enviarMensaje(String mensaje) throws IOException {
        output.writeUTF(nombre + ": " + mensaje);
    }
    public void enviarOpcionChat() throws IOException{
      output.writeInt(opcionChat);
    }
    
    public void recibirNombre() throws IOException, ClassNotFoundException{
       ArrayList<String> array=(ArrayList<String>) inputObj.readObject();
    }
}

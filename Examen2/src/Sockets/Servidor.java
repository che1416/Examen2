/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sockets;

import Modelo.ArrayComunicacionSockets;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author tati
 */
public class Servidor {

    private ServerSocket server;
    private int port = 12345;
    private int cont = -1;

    private ArrayComunicacionSockets arrayCom = ArrayComunicacionSockets.getInstance();

    public void run() {
        try {
            server = new ServerSocket(port);
            while (true) {
                waitForConnection();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void waitForConnection() throws IOException {
        System.out.println("Esperando conexion...\n");
        Socket sock = server.accept();
        Comunicacion com = new Comunicacion(sock);
        arrayCom.add(com);
        com.runComunucacion();
        //com.start();
        System.out.println("Conexion recibida por: " + com.getAddress().getHostName());
    }

    private void waitForConnectionPrivate() throws IOException {
        System.out.println("Esperando conexion...\n");
        Socket sock = server.accept();
        Comunicacion com = new Comunicacion(sock);
        //arrayCom.add(com);
        com.runComunucacion();
        System.out.println("Conexion recibida por: " + com.getAddress().getHostName());
    }

    private void closeConnection() {
        System.out.println("\nTerminando conexion");
        try {
            for (int i = 0; i < arrayCom.size(); i++) {
                if (arrayCom.get(i) != null) {
                    arrayCom.get(i).cerrarConexion();
                }
            }

            server.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Servidor server = new Servidor();
        server.run();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hilos;

import Modelo.ArrayComunicacionSockets;
import Sockets.Comunicacion;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tati
 */
public class RecibirMsjServidor extends Thread{
    private ArrayComunicacionSockets arrayC = ArrayComunicacionSockets.getInstance();
    private Comunicacion miComunicacion;
    private String nombre;
    private int opcionChat = -1;
    private DataInputStream input;
    private ArrayList<String> chatGrupal;
    
    public RecibirMsjServidor(DataInputStream input,Comunicacion comu) {
        this.input = input;
        this.miComunicacion=comu;
    }
  
    
  public void recibirMensaje() throws IOException{
     String msj= input.readUTF();
     arrayC.enviarMensaje(msj, miComunicacion);
  }
  
  public void recibirNombre() throws IOException {
  String nombre= input.readUTF();
      setNombre(nombre);
      miComunicacion.setNombre(nombre);
  }
    public void run(){
        try {
            recibirNombre();
            recibirOpcionDeChat();
            while(true){
                    recibirMensaje();
            }
        } catch (IOException ex) {
            Logger.getLogger(RecibirMsjServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void recibirOpcionDeChat() throws IOException {
        opcionChat = input.readInt();
        miComunicacion.setOpcionChat(opcionChat);
    }
     
    private void accionOpcion() {
        if(opcionChat == 0) {
            chatGrupal = null;
            mi
        } else {
            if(opcionChat == 1) {
                chatGrupal
            }
            
        }
    }
    
    
}

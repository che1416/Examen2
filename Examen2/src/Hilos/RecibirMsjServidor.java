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
import java.lang.reflect.Array;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tati
 */
public class RecibirMsjServidor extends Thread{
    private ArrayComunicacionSockets arrayC= ArrayComunicacionSockets.getInstance();
    private Comunicacion miComunicacion;
    
    private DataInputStream input;
    
    public RecibirMsjServidor(DataInputStream input,Comunicacion comu) {
        this.input = input;
        this.miComunicacion=comu;
    }
  
    
  public void recibirMensaje() throws IOException{
     String msj= input.readUTF();
     arrayC.enviarMensaje(msj, miComunicacion);
  }
  
    public void run(){
        while(true){
            try {
                recibirMensaje();
            } catch (IOException ex) {
                Logger.getLogger(RecibirMsjServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

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
    private String nombre;
    
    private DataInputStream input;
    
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
    
    
}

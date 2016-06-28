/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Hilos.Lock;
import Sockets.Comunicacion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tati
 */
public class ArrayComunicacionSockets {
private Lock lock=Lock.getInstance();
    private ArrayList<Comunicacion> array;
   private static ArrayComunicacionSockets instancia=new ArrayComunicacionSockets();
    
    private ArrayComunicacionSockets(){
        array=new ArrayList<>();
    } 
    
    public static ArrayComunicacionSockets getInstance(){
        return instancia;
    }
    
    public void add(Comunicacion com){
        if(com!=null){
            array.add(com);
        }
    }
    
    public Comunicacion get(int i){
        return array.get(i);
    }
    
    public int size(){
       return array.size();
    }
    public void enviarMensaje(String msj, Comunicacion comu) throws IOException{
    try {
        lock.lock();
        for (int i = 0; i < array.size(); i++) {
            if(msj!=null && comu!=array.get(i)){
                array.get(i).enviarMensaje(msj);
            }
        }
        lock.unlock();
    } catch (InterruptedException ex) {
        Logger.getLogger(ArrayComunicacionSockets.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}

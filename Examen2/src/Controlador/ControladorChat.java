/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Sockets.Cliente;
import Vista.Chat;

/**
 *
 * @author tati
 */
public class ControladorChat {
    private Cliente client;
    private Chat chat;

    public ControladorChat(Cliente client, Chat chat) {
        this.client = client;
        this.chat = chat;
    }
     public void recibirMensahe(String mensaje){
         chat.recibirMensahe(mensaje);
    }
    
    public void enviarMensaje(String msj) throws Exception{
      client.enviarMensaje(msj);
    }
    
    public void runCliente(){
        client.runClient();
    }
    
    public String getNombreControlador(){
        return client.getNombre();
    }
}

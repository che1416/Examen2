/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Sockets.Cliente;
import Vista.Chat;
import Vista.ListaGrupal;
import java.util.ArrayList;

/**
 *
 * @author tati
 */
public class ControladorChat {

    private Cliente client;
    private Chat chat;
    private ListaGrupal listaG;

    public ControladorChat(Cliente client, Chat chat) {
        this.client = client;
        this.chat = chat;
    }

    public void recibirMensahe(String mensaje) {
        chat.recibirMensahe(mensaje);
    }

    public void enviarMensaje(String msj) throws Exception {
        client.enviarMensaje(msj);
    }

    public void runCliente() {
        client.runClient();
    }
    
    public void recibirLista(ArrayList<String> lista) {
        listaG.llenarLista(lista);
    }
    
    public void setListaGrupa(ListaGrupal lista) {
        this.listaG = lista;
    }
    
    public ArrayList<Integer> getListaSeleccionados() {
      return listaG.getListaSeleccionados();
    }
    
    public void iniciarVentanaChat() {
        chat.setVisible(true);
    }

}

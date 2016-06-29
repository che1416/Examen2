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
 * @author Taty
 */
public class ControladorLista {
    private ListaGrupal lista;
    private Cliente client;
    private ArrayList<String> nombresClientes;

    public ListaGrupal getLista() {
        return lista;
    }

    public void setLista(ListaGrupal lista) {
        this.lista = lista;
    }

    public Cliente getClient() {
        return client;
    }

    public void setClient(Cliente client) {
        this.client = client;
    }

    public ArrayList<String> getNombresClientes() {
        return nombresClientes;
    }

    public void setNombresClientes(ArrayList<String> nombresClientes) {
        this.nombresClientes = nombresClientes;
    }

    public ControladorLista(ListaGrupal lista, Cliente client) {
        this.lista = lista;
        this.client = client;
        Chat chat = new Chat(client);
    }
    
    
    
    
}

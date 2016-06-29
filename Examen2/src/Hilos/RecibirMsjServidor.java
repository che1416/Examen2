package Hilos;

import Modelo.ArrayComunicacionSockets;
import Sockets.Comunicacion;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tati
 */
public class RecibirMsjServidor extends Thread {

    private ArrayComunicacionSockets arrayC = ArrayComunicacionSockets.getInstance();
    private Comunicacion miComunicacion;
    private String nombre;
    private int opcionChat = -1;
    private DataInputStream input;
    private ArrayList<String> chatGrupal;
    private ObjectInputStream inputObj;
    private ArrayList<Integer> arrayN;

    public RecibirMsjServidor(DataInputStream input, ObjectInputStream inputObj, Comunicacion comu) {
        this.input = input;
        this.miComunicacion = comu;
        this.inputObj = inputObj;
    }

    public void recibirMensaje() throws IOException, InterruptedException {
        String msj = input.readUTF();
        if (opcionChat == 0) {
            arrayC.enviarMensaje(msj, miComunicacion);
        } else {
            arrayC.enviarMensajeGrupal(msj, arrayN);
        }
    }

    public void recibirNombre() throws IOException {
        String nombre2 = input.readUTF();
        setNombre(nombre2);
        miComunicacion.setNombre(nombre2);
    }

    @Override
    public void run() {
        try {
            recibirNombre();
            recibirOpcionDeChat();
            accionOpcion();
            miComunicacion.enviarListaNombres();
            recibirArrayNombres();
            while (true) {
                recibirMensaje();
            }
        } catch (IOException | ClassNotFoundException | InterruptedException ex) {
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
        if (opcionChat == 0) {
            chatGrupal = null;
            miComunicacion.nullearChatGrupal();
        } else if (opcionChat == 1) {
            chatGrupal = new ArrayList<>();
            miComunicacion.inicializarArrayChatGrupal();
            setArrayGrupal();
        }
    }

    public void setArrayGrupal() {
        chatGrupal = miComunicacion.getChatGrupal();
    }

    public void recibirArrayNombres() throws IOException, ClassNotFoundException {
        arrayN = (ArrayList<Integer>) inputObj.readObject();
        miComunicacion.setArrayN(arrayN);
    }
}

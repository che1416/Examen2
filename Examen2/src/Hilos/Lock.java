/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hilos;

/**
 *
 * @author tati
 */
public class Lock {

    private boolean isLocked = false;
    private static Lock instance=new Lock();
   
    private Lock(){
        
    }
    public static Lock getInstance(){
        return instance;
    }
    public synchronized void lock()
            throws InterruptedException{
        while(isLocked){
            wait();
        }
        isLocked = true;
    }
    
    public synchronized void unlock(){
        isLocked = false;
        notify();
    }
    
}//Fin de Lock


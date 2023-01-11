package Hilos;

import Recurso.PuestoAtencion;

public class Guardia implements Runnable{
    private PuestoAtencion puestoAtencion;
    
    public Guardia(PuestoAtencion puestoAtencion){
        this.puestoAtencion  = puestoAtencion;
    }

    public void run(){
        try {
            while(true) {
                puestoAtencion.atenderPasajero();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

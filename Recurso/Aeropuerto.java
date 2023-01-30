package Recurso;
import java.util.Random;

public class Aeropuerto {
    private final PuestoAtencion[] puestosAtencion;
    private Reloj reloj;
    private int apertura, cierre;
    
    public Aeropuerto (Reloj reloj, int apertura, int cierre, PuestoAtencion[] puestosAtencion){
        this.puestosAtencion = puestosAtencion;
        this.reloj = reloj;
        this.apertura = apertura;
        this.cierre = cierre;
    }

    // ======== Pasajeros =========
    public boolean ingresarAeropuerto(){
        // Si intenta ingresar entre la hora de apertura y hasta una hora antes del cierre
        if(reloj.tiempoDesdeApertura() >= 0 && reloj.tiempoParaCierre() >= 1){
            return true;
        }
        return false;
    }

    public PuestoAtencion puestoInformes(){
        // Retorna un puesto de atencion al pasajero
        return puestosAtencion[new Random().nextInt(puestosAtencion.length)];
    }
    
    
}
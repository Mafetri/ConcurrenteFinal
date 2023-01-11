package Recurso;
import java.util.Random;

public class Aeropuerto {
    private final PuestoAtencion[] puestosAtencion;
    private boolean abierto;
    
    public Aeropuerto (PuestoAtencion[] puestosAtencion){
        this.puestosAtencion = puestosAtencion;
        this.abierto = false;
    }
    
     // ======== Reloj =========
    public void abrir(){
        this.abierto = true;
    }
    public void cerrar(){
        this.abierto = false;
    }

    // ======== Pasajeros =========
    public boolean ingresarAeropuerto(){
        // Si intenta ingresar entre la hora de apertura y hasta una hora antes del cierre
        if(abierto){
            return true;
        }
        return false;
    }
    public PuestoAtencion puestoInformes(){
        // Retorna un puesto de atencion al pasajero
        return puestosAtencion[new Random().nextInt(puestosAtencion.length)];
    }
}
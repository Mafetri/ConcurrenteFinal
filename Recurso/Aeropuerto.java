package Recurso;

public class Aeropuerto {
    private final PuestoAtencion[] puestosAtencion;
    private int horaActual;
    private int horaApertura;
    private int horaCierre;
    
    public Aeropuerto (PuestoAtencion[] puestosAtencion, int hora, int horaApertura, int horaFin){
        this.puestosAtencion = puestosAtencion;
        this.horaActual = hora;
        this.horaApertura = horaApertura;
        this.horaCierre = horaFin;
    }
    
    // ======== Pasajeros =========
    public boolean ingresarAeropuerto(){
        // Si intenta ingresar entre la hora de apertura y hasta una hora antes del cierre
        if(horaActual >= horaActual && horaActual < horaCierre){
            return true;
        }
        return false;
    }
    public PuestoAtencion puestoInformes(){
        // Retorna un puesto de atencion al pasajero
        return puestosAtencion[(int)Math.random() % puestosAtencion.length];
    }
}
package Hilos;
import Recurso.*;

public class Pasajero implements Runnable{
    private Boleto boleto;
    private Aeropuerto aeropuerto;

    public Pasajero (Aeropuerto aeropuerto){
        this.aeropuerto = aeropuerto;
    }
    public void run() {
        try {
            if(aeropuerto.ingresarAeropuerto()){
                System.out.println(Thread.currentThread().getName() + " ha ingresaod al aeropuerto.");
                PuestoAtencion puesto = aeropuerto.puestoInformes();
                puesto.hacerFila();
                System.out.println(Thread.currentThread().getName() + " está siendo atendido.");
                boleto = puesto.obtenerBoleto();
                System.out.println(Thread.currentThread().getName() + " tiene su boleto. (T:"+boleto.getTerminal()+", G:"+boleto.getPuerta()+")");
            } else {
                System.out.println(Thread.currentThread().getName() + " no ha podido ingresar al aeropuerto ✖️.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

package Hilos;
import Recurso.*;
import java.util.Random;

public class Pasajero implements Runnable{
    private Boleto boleto;
    private Aeropuerto aeropuerto;

    public Pasajero (Aeropuerto aeropuerto){
        this.aeropuerto = aeropuerto;
    }
    public void run() {
        try {
            
            Thread.sleep(new Random().nextInt(10000));
            if(aeropuerto.ingresarAeropuerto()){
                System.out.println(Thread.currentThread().getName() + " ha ingresado al aeropuerto.");
                PuestoAtencion puesto = aeropuerto.puestoInformes();
                System.out.println(Thread.currentThread().getName() + " se la ha asignado el puesto " + puesto.getNombre());
                puesto.hacerFila();
                System.out.println(Thread.currentThread().getName() + " está siendo atendido.");
                Thread.sleep(5000);
                boleto = puesto.obtenerBoleto();
                System.out.println("====> " + Thread.currentThread().getName() + " tiene su boleto. (T:"+boleto.getTerminal()+", G:"+boleto.getPuerta()+")");
            } else {
                System.out.println(Thread.currentThread().getName() + " no ha podido ingresar al aeropuerto.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

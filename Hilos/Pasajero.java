package Hilos;
import Recurso.*;
import java.util.Random;

public class Pasajero implements Runnable{
    private Boleto boleto;
    private Aeropuerto aeropuerto;
    private Tren tren;

    public Pasajero (Aeropuerto aeropuerto, Tren tren){
        this.aeropuerto = aeropuerto;
        this.tren = tren;
    }
    public void run() {
        try {
            
            Thread.sleep(new Random().nextInt(10000));
            if(aeropuerto.ingresarAeropuerto()){
                // System.out.println(Thread.currentThread().getName() + " ha ingresado al aeropuerto.");
                PuestoAtencion puesto = aeropuerto.puestoInformes();
                // System.out.println(Thread.currentThread().getName() + " se la ha asignado el puesto " + puesto.getNombre());
                puesto.hacerFila();
                // System.out.println(Thread.currentThread().getName() + " está siendo atendido.");
                Thread.sleep(5000);
                boleto = puesto.obtenerBoleto();
                System.out.println("====> " + Thread.currentThread().getName() + " tiene su boleto. (T:"+boleto.getTerminal()+", G:"+boleto.getPuerta()+")");

                // System.out.println(Thread.currentThread().getName() + " está esperando al tren.");
                tren.subirseAlTren();
                // System.out.println(Thread.currentThread().getName() + " ha subido al tren y espera a la proxima parada.");
                int numTerminal = tren.esperarATerminal(boleto.getTerminal());
                Thread.sleep(new Random().nextInt(2000) + 500);
                tren.bajarDelTren(numTerminal);
                System.out.println(Thread.currentThread().getName() + " ha bajado del tren en la treminal " + boleto.getTerminal() + " y se dirige a la puerta " + boleto.getPuerta());
            } else {
                System.out.println(Thread.currentThread().getName() + " no ha podido ingresar al aeropuerto.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

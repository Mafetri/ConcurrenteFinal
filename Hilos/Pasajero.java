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
            System.out.println(Thread.currentThread().getName() + " intenta ingresal al aeropuerto.");
            if(aeropuerto.ingresarAeropuerto()){
                // Solicita indicaciones a su puesto de atención
                PuestoAtencion puesto = aeropuerto.puestoInformes();
                System.out.println(Thread.currentThread().getName() + " se la ha asignado el puesto " + puesto.getNombre());
                
                // Se dirige al puesto de atención y si la cola esta llena espera en el hall
                System.out.println(Thread.currentThread().getName() + " está en el hall.");
                puesto.irAlPuesto();

                // Hace fila y espera que el guardia lo atienda
                System.out.println(Thread.currentThread().getName() + " está haciendo la fila.");
                puesto.hacerFila();

                // Lo atienden
                System.out.println(Thread.currentThread().getName() + " está siendo atendido.");
                Thread.sleep(5000);
                boleto = puesto.obtenerBoleto();
                System.out.println("====> " + Thread.currentThread().getName() + " tiene su boleto. (T:"+boleto.getTerminal()+", G:"+boleto.getPuerta()+")");

                // Obtiene cual es la parada en la cual se debe bajar
                int parada = tren.getParada(boleto.getTerminal());

                System.out.println(Thread.currentThread().getName() + " está esperando al tren.");
                tren.subirseAlTren();

                System.out.println(Thread.currentThread().getName() + " ha subido al tren y espera a llegar a su parada.");
                tren.esperarATerminal(parada);

                // Tarda en bajarse
                Thread.sleep(new Random().nextInt(2000) + 500);
            
                // Se baja del tren
                Terminal terminal = tren.bajarDelTren(parada);
                System.out.println(Thread.currentThread().getName() + " ha bajado del tren en la treminal " + boleto.getTerminal() + " y se dirige a la puerta " + boleto.getPuerta());

                // Entra a la sala de embarque
                if(new Random().nextBoolean()){
                    FreeShop freeShop = terminal.getFreeShop();
                    System.out.println(Thread.currentThread().getName() + " intenta ingresar al freeshop.");
                    freeShop.entrar();
                    // Ve productos
                    Thread.sleep(1000);
                    
                    // Compra
                    int caja = -1;
                    if(new Random().nextBoolean()) {
                        caja = freeShop.pagar();
                        System.out.println(Thread.currentThread().getName() + " esta comprando en la caja " + caja + " de la terminal " + terminal.getNombre());
                        Thread.sleep(1000);
                    }

                    // Sale del FreeShop
                    freeShop.salir(caja);
                }

                System.out.println(Thread.currentThread().getName() + " terminó.");
            } else {
                System.out.println(Thread.currentThread().getName() + " no ha podido ingresar al aeropuerto.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

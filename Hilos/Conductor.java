package Hilos;
import Recurso.Tren;

public class Conductor implements Runnable{
    private Tren tren;

    public Conductor(Tren tren){
        this.tren = tren;
    }

    public void run(){
        try {
            while(true){
                // Espera a que el tren este lleno
                System.out.println("El conductor espera a que se llene el tren");
                tren.conducirTren();

                // Recorre cada parada hasta volver al inicio
                do{
                    System.out.println("---> El tren viaja a la proxima parada.");
                    Thread.sleep(2000);
                } while(tren.proximaParada());
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}

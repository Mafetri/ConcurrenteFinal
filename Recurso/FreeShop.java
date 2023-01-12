package Recurso;

public class FreeShop {
    private final int capacidad;
    private int ocupacion;

    private boolean cajaLibre1;
    private boolean cajaLibre2;

    public FreeShop(int capacidadFreeShop){
        this.capacidad = capacidadFreeShop;
        this.ocupacion = 0;
        cajaLibre1 = true;
        cajaLibre2 = true;
    }

    // ======= Pasajero ========
    public synchronized void entrar() throws Exception{
        // Si esta lleno espera a que haya un lugar
        while(ocupacion >= capacidad){
            wait();
        }
    }
    public synchronized int pagar() throws Exception {
        while(!cajaLibre1 && !cajaLibre2){
            wait();
        }

        if(cajaLibre1){
            cajaLibre1 = false;
            return 1;
        } else {
            cajaLibre2 = false;
            return 2;
        }
    }
    public synchronized void salir(int caja) throws Exception{
        // Libera la caja
        if(caja == 1){
            cajaLibre1 = true;
        } else if (caja == 2) {
            cajaLibre2 = true;
        }

        // Libera un lugar en el FreeShop
        ocupacion--;

        // Notifica que dejó de usar la caja y que dejó de usar el freeshop
        notifyAll();
    }
}

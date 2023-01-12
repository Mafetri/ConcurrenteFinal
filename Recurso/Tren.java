package Recurso;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.*;
public class Tren {
    private Lock mutex = new ReentrantLock();
    private Parada[] paradas;
    private Condition conductor = mutex.newCondition(), trenEstacion = mutex.newCondition();
    private int lugaresDisponibles;
    private int lugaresMax;
    private int paradaActual;

    public Tren(Terminal[] terminales, int lugaresMax){
        paradas = new Parada[terminales.length];
        for(int i = 0; i < paradas.length; i++){
            paradas[i] = new Parada(terminales[i], mutex.newCondition());
        }
        this.lugaresMax = lugaresMax;
        lugaresDisponibles = 0;
        paradaActual = -1;
    }

    // ======== Pasajero ========
    // Obtiene el numero de parada de su terminal
    public int getParada(String terminal) {
        // Busca la condition de su terminal
        boolean encontrado = false;
        int index = 0;
        while (!encontrado){
            if(paradas[index].getTerminal().getNombre().equals(terminal)){
                encontrado = true;
            } else {
                index++;
            }
        }
        return index;
    }

    // Se sube al tren o espera el proximo viaje
    public void subirseAlTren() throws Exception{
        mutex.lock();
        try {
            // Si no hay mas lugares, espera a que el tren vuelva
            while (lugaresDisponibles - 1 < 0){
                trenEstacion.await();
            }

            lugaresDisponibles--;
            
            // Si no hay lugares disponibles en el tren, y es el ultimo pasajero, avisa al conductor
            if(lugaresDisponibles <= 0){
                conductor.signal();
            }
        } finally {
            mutex.unlock();
        }
    }

    // Espera a que el tren llegue a laterminal
    public void esperarATerminal(int parada) throws Exception{
        mutex.lock();
        try{
            paradas[parada].agregarPasajero();
            // Espera al que el tren llegue a la terminal deseada
            paradas[parada].getParada().await();
        } finally {
            mutex.unlock();
        }
    }

    // Se baja del tren
    public Terminal bajarDelTren(int parada) throws Exception {
        mutex.lock();
        try {
            // Se baja del tren
            paradas[parada].quitarPasajero();
                
            if(paradas[paradaActual].getPasajeros() <= 0){
                conductor.signal();
            }

            return paradas[parada].getTerminal();
        } finally {
            mutex.unlock();
        }
    }

    // ======== Conductor ========
    public void conducirTren() throws Exception {
        mutex.lock();
        try {
            // Avisa que volvio a la estacion principal
            lugaresDisponibles = lugaresMax;
            trenEstacion.signalAll();

            // Espera a que el tren este lleno
            while (lugaresDisponibles > 0){
                conductor.await();
            }
        } finally {
            mutex.unlock();
        }
    }

    public boolean proximaParada() throws Exception {
        boolean sigueConduciendo = true;
        mutex.lock();
        paradaActual++;
        try {
            // Si no es la ultima parada
            if(paradaActual < paradas.length){
                System.out.println("-> El tren se detiene en la terminal: " + paradas[paradaActual].getTerminal().getNombre());
                
                // Despierta a los pasajeros de la parada actual
                paradas[paradaActual].getParada().signalAll();

                // Espera a que los pasajeros se bajen
                while(paradas[paradaActual].getPasajeros() > 0){
                    conductor.await();
                }
            } else {
                paradaActual = -1;
                lugaresDisponibles = lugaresMax;
                System.out.println("-> El tren se detiene en el ingreso.");

                sigueConduciendo = false;
            }
            
            return sigueConduciendo;
        } finally {
            mutex.unlock();
        }
    }
}

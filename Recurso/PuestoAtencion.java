package Recurso;
import java.util.concurrent.*;
import java.util.Random;

public class PuestoAtencion {
    private String nombre;
    private Semaphore cola;
    private Semaphore puesto;
    private Semaphore guardia;
    private final Terminal[] TERMINALES;

    public PuestoAtencion(String nombre, int capacidadMaxima, Terminal[] terminales){
        this.nombre = nombre;
        this.TERMINALES = terminales;
        puesto = new Semaphore(0, true);
        guardia = new Semaphore(1);
        cola = new Semaphore(capacidadMaxima);
    }

    // ======== Pasajero ========
    public void hacerFila () throws Exception{
        // System.out.println(Thread.currentThread().getName() + " está en el hall.");

        // Espera en el Hall a que haya lugar en la fila
        cola.acquire();
        // System.out.println(Thread.currentThread().getName() + " está haciendo la fila.");

        // Hace la cola y espera a que un guardia le permita pasar
        puesto.acquire();
    }

    public Boleto obtenerBoleto() throws Exception{
        // Obtiene su boleto
        int indexTerminal = new Random().nextInt(3);
        String terminal = TERMINALES[indexTerminal].getNombre();

        int[] puertasDisponibles = TERMINALES[indexTerminal].getPuertas();
        int puerta = puertasDisponibles[new Random().nextInt(puertasDisponibles.length-1)];
        Boleto boleto = new Boleto(terminal, puerta);

        // Libera un lugar en la cola y le avisa al guardia
        cola.release();
        guardia.release();

        return boleto;
    }

    public String getNombre() {
        return this.nombre;
    }

    // ======== Guardia ========
    public void atenderPasajero () throws Exception{
        // Duerme hasta que alguien termine de ser atendido
        guardia.acquire();

        // Le permite en orden ser atendido al primero de la cola
        puesto.release();
    }
}

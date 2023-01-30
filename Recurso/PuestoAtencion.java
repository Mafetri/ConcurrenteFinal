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
    public void irAlPuesto () throws Exception{
        // Espera en el Hall a que haya lugar en la fila
        cola.acquire();
    }

    public void hacerFila() throws Exception {
        puesto.acquire();
    }

    public Boleto obtenerBoleto() throws Exception{
        // Obtiene su boleto
        int indexTerminal = new Random().nextInt(3);
        String terminal = TERMINALES[indexTerminal].getNombre();

        int[] puertasDisponibles = TERMINALES[indexTerminal].getPuertas();
        int puerta = puertasDisponibles[new Random().nextInt(puertasDisponibles.length-1)];
        // Asigna una hora de embarque que se relaciona con la puerta, para que la gente que se le asigne
        // dicha puerta tenga la misma hora de embarque
        int horaEmbarque = (puerta + 34) % 24;
        Boleto boleto = new Boleto(terminal, puerta, horaEmbarque);

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

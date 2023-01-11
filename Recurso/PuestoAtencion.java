package Recurso;
import java.util.concurrent.*;

public class PuestoAtencion {
    private Semaphore cola;
    private Semaphore puesto;
    private Semaphore guardia;
    private final Terminal[] TERMINALES;

    public PuestoAtencion(int capacidadMaxima, Terminal[] terminales){
        this.TERMINALES = terminales;
        puesto = new Semaphore(0, true);
        guardia = new Semaphore(1);
        cola = new Semaphore(capacidadMaxima);
    }

    // ======== Pasajero ========
    public void hacerFila () throws Exception{
        System.out.println(Thread.currentThread().getName() + " está en el hall.");

        // Espera en el Hall a que haya lugar en la fila
        cola.acquire();
        System.out.println(Thread.currentThread().getName() + " está haciendo la fila.");

        // Hace la cola y espera a que un guardia le permita pasar
        puesto.acquire();
    }

    public Boleto obtenerBoleto() throws Exception{
        // Obtiene su boleto
        int indexTerminal = (int)Math.random() % TERMINALES.length;
        String terminal = TERMINALES[indexTerminal].getNombre();
        int puerta = (int)Math.random() % TERMINALES[indexTerminal].getPuertas().length;
        Boleto boleto = new Boleto(terminal, puerta);

        // Libera un lugar en la cola y le avisa al guardia
        cola.release();
        guardia.release();

        return boleto;
    }

    // ======== Guardia ========
    public void atenderPasajero () throws Exception{
        // Duerme hasta que alguien termine de ser atendido
        guardia.acquire();

        // Le permite en orden ser atendido al primero de la cola
        puesto.release();
    }
}

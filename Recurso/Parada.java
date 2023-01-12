package Recurso;
import java.util.concurrent.locks.*;;
public class Parada {
    private Terminal terminal;
    private Condition parada;
    private int pasajeros;

    public Parada(Terminal terminal, Condition parada){
        this.terminal = terminal;
        this.parada = parada;
        pasajeros = 0;
    }

    public Terminal getTerminal(){
        return this.terminal;
    }

    public Condition getParada(){
        return this.parada;
    }

    public int getPasajeros(){
        return this.pasajeros;
    }

    public void agregarPasajero(){
        this.pasajeros++;
    }

    public void quitarPasajero(){
        this.pasajeros--;
    }
}

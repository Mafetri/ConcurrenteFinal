package Recurso;
import java.util.concurrent.locks.*;;
public class Parada {
    private String nombreTerminal;
    private Condition parada;
    private int pasajeros;

    public Parada(String nombreTerminal, Condition parada){
        this.nombreTerminal = nombreTerminal;
        this.parada = parada;
        pasajeros = 0;
    }

    public String getNombreTerminal(){
        return this.nombreTerminal;
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

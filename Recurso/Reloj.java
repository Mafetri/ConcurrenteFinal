package Recurso;

public class Reloj {
    private int horaActual;
    private int horaApertura;
    private int horaCierre;

    public Reloj (int horaInicio, int horaApertura, int horaCierre) {
        this.horaActual = horaInicio;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    public synchronized void avanzarHora () {
        // Avanza una hora el reloj
        this.horaActual = (this.horaActual + 1) % 24;
        // Suena la alarma a los pasajeros para que se despierten los dormidos
        notifyAll();
    }

    public int getHora(){
        return this.horaActual;
    }

    public int tiempoDesdeApertura() {
        return this.horaActual - this.horaApertura;
    }

    public int tiempoParaCierre() {
        return this.horaCierre - this.horaActual;
    }

    public synchronized void esperarHora(int hora) throws Exception {
        while(this.horaActual != hora){
            wait();
        }
    }
}

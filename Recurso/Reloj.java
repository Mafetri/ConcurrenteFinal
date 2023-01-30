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

    public void avanzarHora () {
        this.horaActual = (this.horaActual + 1) % 24;
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
}

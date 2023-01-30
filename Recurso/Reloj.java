package Recurso;

public class Reloj {
    private int horaActual;

    public Reloj (int horaInicio) {
        this.horaActual = horaInicio;
    }

    public void avanzarHora () {
        this.horaActual = (this.horaActual + 1 % 24);
    }

    public int getHora(){
        return this.horaActual;
    }
}

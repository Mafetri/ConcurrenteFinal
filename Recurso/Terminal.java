package Recurso;

public class Terminal {
    private String nombre;
    private int[] puertas;

    public Terminal(String nombre, int[] puertas){
        this.nombre = nombre;
        this.puertas = puertas;
    }

    public String getNombre(){ 
        return this.nombre;
    }

    public int[] getPuertas(){
        return this.puertas;
    }
}

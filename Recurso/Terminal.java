package Recurso;

public class Terminal {
    private String nombre;
    private int[] puertas;
    private FreeShop freeShop;

    public Terminal(String nombre, int[] puertas, FreeShop freeShop){
        this.nombre = nombre;
        this.puertas = puertas;
        this.freeShop = freeShop;
    }

    public String getNombre(){ 
        return this.nombre;
    }

    public int[] getPuertas(){
        return this.puertas;
    }

    public FreeShop getFreeShop(){
        return this.freeShop;
    }
}

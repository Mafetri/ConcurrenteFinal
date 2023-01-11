package Hilos;

import Recurso.Aeropuerto;

public class Reloj implements Runnable{
    private final int minutosHabilesDiarios;
    private int minutosRestantes;
    private Aeropuerto aeropuerto;
    private int unMinuto;

    public Reloj(int horaApertura, int horaCierre, Aeropuerto aeropuerto, int unMinuto){
        this.minutosHabilesDiarios = horaCierre * 60 - horaApertura * 60;
        this.minutosRestantes = minutosHabilesDiarios;
        this.aeropuerto = aeropuerto;
        this.unMinuto = unMinuto;
    }

    public void run(){
        try {
            if(minutosRestantes > 0){
                System.out.println("El aeropuerto ha abierto.");
                aeropuerto.abrir();
            }
            while(true) {
                Thread.sleep(unMinuto);
                minutosRestantes--;
                if(minutosRestantes < 0){
                    System.out.println("El aeropuerto ha cerrado.");
                    aeropuerto.cerrar();
                    minutosRestantes = minutosHabilesDiarios;
                    System.out.println("Volvera a abrir en: " + (24 * 60 - minutosHabilesDiarios) * unMinuto + " milisegundos.");
                    Thread.sleep((24 * 60 - minutosHabilesDiarios) * unMinuto);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

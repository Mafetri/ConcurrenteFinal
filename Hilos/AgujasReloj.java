package Hilos;

import Recurso.Reloj;

public class AgujasReloj implements Runnable{
    private int unaHora;
    private Reloj reloj;

    public AgujasReloj (Reloj reloj, int unaHora){
        this.reloj = reloj;
        this.unaHora = unaHora;
    }

    public void run(){
        try {
            System.out.println("Hora actual: " + reloj.getHora());

            while(true) {
                Thread.sleep(unaHora);
                reloj.avanzarHora();
                System.out.println("Hora actual: " + reloj.getHora());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

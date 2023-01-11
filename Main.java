import Recurso.*;
import Hilos.*;

public class Main {
    public static void main(String[] args) {
        int horaApertura = 6;
        int horaCierre = 22;
        int numPuestosAtencion = 3;
        int capacidadMaximaFila = 10;
        int numTerminales = 3;
        int numPuertas = 20;
        int unMinuto = 10; // Un minuto equivale a 10 ms

        // Terminales
        Terminal[] terminales = new Terminal[numTerminales];
        int puertaNum = 1;
        System.out.println("Terminales Disponibles:");
        for( int i = 0; i < terminales.length; i++){
            int[] puertas = new int[numPuertas/3];
            String nombreTerminal = Character.toString((char)(i + 65));
            System.out.print("\n-> " + nombreTerminal + ", con puertas: ");

            for( int j = 0; j < puertas.length; j++){
                puertas[j] = puertaNum;
                puertaNum++;
                System.out.print(puertas[j] + ", ");
            }
            terminales[i] = new Terminal(nombreTerminal, puertas);
        }

        // Puestos de Atencion
        PuestoAtencion[] puestosAtencion = new PuestoAtencion[numPuestosAtencion];
        for( int i = 0; i < puestosAtencion.length; i++){
            puestosAtencion[i] = new PuestoAtencion(Character.toString((char)(i + 65)), capacidadMaximaFila, terminales);
        }

        Aeropuerto aeropuerto = new Aeropuerto(puestosAtencion);

        try {
            System.out.println("\nEl aeropuerto se abre en 2 segundos");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println(e);
        }

        // Hilos
        // Reloj
        Thread reloj = new Thread(new Reloj(horaApertura, horaCierre, aeropuerto, unMinuto), "Reloj");
        reloj.start();

        // Guardias
        Thread[] guardias = new Thread[numPuestosAtencion];
        for( int i = 0; i < guardias.length; i++){
            guardias[i] = new Thread(new Guardia(puestosAtencion[i]), "Guardia " + i);
            guardias[i].start();
        }

        // Pasajeros
        Thread[] pasajeros = new Thread[50];
        for( int i = 0; i < pasajeros.length; i++){
            pasajeros[i] = new Thread(new Pasajero(aeropuerto), "Pasajero " + i);
            pasajeros[i].start();
        }
    }
}

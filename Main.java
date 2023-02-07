import Recurso.*;
import Hilos.*;

public class Main {
    public static void main(String[] args) {
        int horaInicio = 6; // Hora de inicio del reloj
        int horaApertura = 6;
        int horaCierre = 22;
        int numPuestosAtencion = 3;
        int capacidadMaximaFila = 10;
        int numTerminales = 5;
        int numPuertas = 20;
        int unaHora = 10000; // Valor de una hora en ms
        int capacidadTren = 10;
        int esperaMaximaConductor = 2; // Valor en horas
        int numPasajeros = 15;
        int capacidadFreeShop = 3;

        // Terminales
        Terminal[] terminales = new Terminal[numTerminales];
        int puertaNum = 1;
        System.out.println("Terminales Disponibles:");
        for( int i = 0; i < terminales.length; i++){
            int[] puertas = new int[numPuertas/terminales.length];
            String nombreTerminal = Character.toString((char)(i + 65));
            System.out.print("\n-> " + nombreTerminal + ", con puertas: ");

            for( int j = 0; j < puertas.length; j++){
                puertas[j] = puertaNum;
                puertaNum++;
                System.out.print(puertas[j] + ", ");
            }
            terminales[i] = new Terminal(nombreTerminal, puertas, new FreeShop(capacidadFreeShop));
        }

        // Reloj
        Reloj reloj = new Reloj(horaInicio, horaApertura, horaCierre);

        // Puestos de Atencion
        PuestoAtencion[] puestosAtencion = new PuestoAtencion[numPuestosAtencion];
        for( int i = 0; i < puestosAtencion.length; i++){
            puestosAtencion[i] = new PuestoAtencion(Character.toString((char)(i + 65)), capacidadMaximaFila, terminales);
        }

        // Aeropuerto
        Aeropuerto aeropuerto = new Aeropuerto(reloj, puestosAtencion);

        // Tren
        Tren tren = new Tren(terminales, capacidadTren, esperaMaximaConductor * unaHora);

        // Hilos
        // Agujas Reloj
        Thread agujas = new Thread(new AgujasReloj (reloj, unaHora), "Reloj");
        agujas.start();

        // Guardias
        Thread[] guardias = new Thread[numPuestosAtencion];
        for( int i = 0; i < guardias.length; i++){
            guardias[i] = new Thread(new Guardia(puestosAtencion[i]), "Guardia " + i);
            guardias[i].start();
        }

        // Pasajeros
        Thread[] pasajeros = new Thread[numPasajeros];
        for( int i = 0; i < pasajeros.length; i++){
            pasajeros[i] = new Thread(new Pasajero(aeropuerto, tren, reloj), "Pasajero " + i);
            pasajeros[i].start();
        }

        // Conductor
        Thread conductor = new Thread(new Conductor(tren), "Conductor");
        conductor.start();
    }
}

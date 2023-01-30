package Recurso;

public class Boleto {
    private String terminal;
    private int puerta;
    private int horaEmbarque;

    public Boleto(String terminal, int puerta, int horaEmbarque){
        this.terminal = terminal;
        this.puerta = puerta;
        this.horaEmbarque = horaEmbarque;
    }

    public String getTerminal(){
        return this.terminal;
    }
    
    public int getPuerta(){
        return this.puerta;
    }

    public int getHoraEmbarque () {
        return this.horaEmbarque;
    }
}

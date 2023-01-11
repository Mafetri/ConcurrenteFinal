public class Boleto {
    private String terminal;
    private int puerta;

    public Boleto(String terminal, int puerta){
        this.terminal = terminal;
        this.puerta = puerta;
    }

    public String getTerminal(){
        return this.terminal;
    }
    public int getPuerta(){
        return this.puerta;
    }
}

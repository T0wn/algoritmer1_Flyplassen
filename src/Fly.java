
public class Fly {
    private static int antallFly = 0;
    private int flyNr;

    public Fly() {
        this.flyNr = Fly.antallFly + 1;
        Fly.antallFly++;
    }

    public int getFlyNr() {
        return flyNr;
    }

    @Override
    public String toString() {
        return "Fly nr." + this.flyNr;
    }

    public static int getAntallFly() {
        return antallFly;
    }
}

import java.util.LinkedList;
import java.util.Queue;

public class Flyplass {
    private Queue<Fly> landeKoo = new LinkedList<>();
    private Queue<Fly> avgangKoo = new LinkedList<>();
    private int queueSize;

    private int antallFlyLandet = 0;
    private int antallFlyTattAv = 0;
    private int antallFlyAvvist = 0;
    private int ledigeTidsenheter = 0;

    private int antallTidsenheter = 0;
    // legger til antall enheter i køene for hver tidsenhet i Fly.updateInfo()
    private int sumLandetQueueSize = 0;
    private int sumAvgangQueueSize = 0;

    public Flyplass(int queueSize) {
        this.queueSize = queueSize;
    }

    public void flyTarAv() {
        this.antallFlyTattAv++;
        Fly nesteFly = avgangKoo.poll();
        int flyIKoo = landeKoo.size() + avgangKoo.size();
        System.out.println(nesteFly + " tatt av. " + flyIKoo + " fly i kø." );
    }

    public void flyLander() {
        this.antallFlyLandet++;
        Fly nesteFly = landeKoo.poll();
        int flyIKoo = landeKoo.size() + avgangKoo.size();
        System.out.println(nesteFly + " landet. " + flyIKoo + " fly i kø");
    }

    public void updateInfo() {
        this.antallTidsenheter++;
        this.sumLandetQueueSize += this.landeKoo.size();
        this.sumAvgangQueueSize += this.avgangKoo.size();
    }

    public boolean isLandingEmpty() {
        return landeKoo.isEmpty();
    }

    public boolean isAvgangEmpty() {
        return avgangKoo.isEmpty();
    }

    public void ledig() {
        this.ledigeTidsenheter++;
        System.out.println("Flyplassen er tom.");
    }

    public void queueFlyLanding(Fly fly) {
        if (landeKoo.size() == queueSize) {
            this.antallFlyAvvist++;
            System.out.println(fly + " referert til annen flyplass. Landekø full.");
        }
        else {
            landeKoo.add(fly);
            System.out.println(fly + " klar for landing.");
        }
    }

    public void queueFlyAvgang(Fly fly) {
        if (avgangKoo.size() == queueSize) {
            this.antallFlyAvvist++;
            System.out.println("Avgangskø full. " + fly + " fikk ikke plass" );
        }
        else {
            avgangKoo.add(fly);
            System.out.println(fly + " klar for avgang.");
        }
    }

    public double getAvgWaitLanding() {
        return Double.valueOf(sumLandetQueueSize) / Double.valueOf(antallTidsenheter);
    }

    public double getAvgWaitAvgang() {
        return Double.valueOf(sumAvgangQueueSize) / Double.valueOf(antallTidsenheter);
    }

    public int getLandingQueueSize() {
        return this.landeKoo.size();
    }

    public int getAvgangQueueSize() {
        return this.avgangKoo.size();
    }

    public int getAntallFlyLandet() {
        return antallFlyLandet;
    }

    public int getAntallFlyTattAv() {
        return antallFlyTattAv;
    }

    public int getAntallFlyAvvist() {
        return antallFlyAvvist;
    }

    public int getLedigeTidsenheter() {
        return ledigeTidsenheter;
    }
}
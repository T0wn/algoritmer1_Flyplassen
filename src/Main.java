import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Tar parametere for simuleringen
        System.out.print("Antall tidssteg                   : ");
        Scanner scanner = new Scanner(System.in);
        int tidsteg = scanner.nextInt();
        System.out.print("Gjennomsnittfrekvens for landinger: ");
        double landingFreq = scanner.nextDouble();
        System.out.print("Gjennomsnittfrekvens for avganger : ");
        double avgangFreq = scanner.nextDouble();

        int queueSize = 10;
        Flyplass flyplass = new Flyplass(queueSize);

        // Alt som skjer for hver tidsenhet
        for (int i = 1; i <= tidsteg; i++) {

            System.out.println("\n------Tidsenhet " + i + "------");

            int flyForLanding = getPoissonRandom(landingFreq);
            for (int j = 0; j < flyForLanding; j++) {
                flyplass.queueFlyLanding(new Fly());
            }

            int flyForAvgang = getPoissonRandom(avgangFreq);
            for (int k = 0; k < flyForAvgang; k++) {
                flyplass.queueFlyAvgang(new Fly());
            }

            // Et fly lander eller tar av, eller så er flyplassen tom.
            if (flyplass.isLandingEmpty()) {
                if (flyplass.isAvgangEmpty()) {
                    flyplass.ledig();
                }
                else {
                    flyplass.flyTarAv();
                }
            }
            else {
                flyplass.flyLander();
            }

            flyplass.updateInfo();
        }

        // resultatUtskrift
        System.out.println("\n------Resultat utskrift------"
                + "\nSimuleringen ferdig etter      : " + tidsteg + " tidsenheter."
                + "\nTotalt antall fly behandlet    : " + Fly.getAntallFly()
                + "\nAntall Fly landet              : " + flyplass.getAntallFlyLandet()
                + "\nAntall fly tatt av             : " + flyplass.getAntallFlyTattAv()
                + "\nAntall fly avvist              : " + flyplass.getAntallFlyAvvist()
                + "\nAntall fly klare for landing   : " + flyplass.getLandingQueueSize()
                + "\nAntall fly klare for avgang    : " + flyplass.getAvgangQueueSize()
                + "\nAntall ledige tidsenheter      : " + flyplass.getLedigeTidsenheter()
                + "\nGj.snitt. ventetid, landing    : " + flyplass.getAvgWaitLanding()
                + "\nGj.snitt. ventetid, avgang     : " + flyplass.getAvgWaitAvgang()
                );

    }

    private static int getPoissonRandom(double mean)
    {
        Random r = new Random();
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do
        {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }

}

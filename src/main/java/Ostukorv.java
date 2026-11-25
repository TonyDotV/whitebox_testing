import java.util.ArrayList;
import java.util.List;

/**
 * Veebipoe ostukorv.
 *
 * NÕUDED (spetsifikatsioon):
 *  1. Korvi saab lisada ainult positiivse hinnaga toote.
 *     Muul juhul visatakse IllegalArgumentException.
 *  2. Sooduskood "SOODUS10" annab 10% allahindlust,
 *     kuid ainult siis, kui korvi summa on vähemalt 20 €.
 *  3. Tarne on tasuta, kui korvi summa on vähemalt 50 €
 *     või klient on püsiklient. Muidu maksab tarne 4,90 €.
 *  4. Tühja korvi korral tarnet ei arvestata (0 €).
 *  5. Makstav summa = korvi summa − allahindlus + tarne.
 */
public class Ostukorv {

    private final List<Double> hinnad = new ArrayList<>();
    private final boolean pysiklient;

    public Ostukorv(boolean pysiklient) {
        this.pysiklient = pysiklient;
    }

    public void lisaToode(double hind) {
        if (hind <= 0) {
            throw new IllegalArgumentException("Hind peab olema positiivne");
        }
        hinnad.add(hind);
    }

    public int toodeteArv() {
        return hinnad.size();
    }

    public double summa() {
        double kokku = 0;
        for (double hind : hinnad) {
            kokku += hind;
        }
        return kokku;
    }

    public double allahindlus(String kood) {
        if (kood == null) {
            return 0;
        }
        if (kood.equals("SOODUS10") && summa() >= 20) {
            return summa() * 0.10;
        }
        return 0;
    }

    public double tarneHind() {
        if (hinnad.isEmpty()) {
            return 0;
        }
        if (summa() > 50 || pysiklient) {
            return 0;
        }
        return 4.90;
    }

    public double maksta(String kood) {
        return summa() - allahindlus(kood) + tarneHind();
    }
}

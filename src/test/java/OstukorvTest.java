import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class OstukorvTest {
    @Test
    void tavaklientVäikeseSummaga_maksabTarne() {
        Ostukorv k = new Ostukorv(false);
        k.lisaToode(10);
        assertEquals(4.90, k.tarneHind(), k.toodeteArv());
        assertEquals(4.90*0.10, k.allahindlus("SOODUS10") ,k.maksta("SOODUS10"));

        k.lisaToode(0);
        assertEquals(0, k.tarneHind());
    }

    @Test
    void allahindluseLisamine() {
        Ostukorv ka = new Ostukorv(false);
        ka.lisaToode(60);
        assertEquals(0, ka.allahindlus(null), ka.tarneHind());

        ka.lisaToode(20);
        assertEquals(10, ka.allahindlus("SOODUS10"), ka.maksta("SOODUS10"));
        ka.lisaToode(10);
        assertEquals(9, ka.allahindlus("SOODUS10"));
        ka.lisaToode(10);
        assertEquals(0, ka.allahindlus(null));
    }

    @Test
    void pysiklientSuureSummaga_maksabTarne() {
        Ostukorv kp = new Ostukorv(true);
        kp.lisaToode(60);
        assertEquals(0, kp.tarneHind());

        kp.lisaToode(40);
        assertEquals(0, kp.tarneHind());
    }

    @Test
    void tyhiHinnadList() {
        Ostukorv kt = new Ostukorv(false);
        assertEquals(4.9, kt.tarneHind());
    }
}
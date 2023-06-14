import java.util.Set;

public class Main {

    public static void main(String[] argv) throws Exception {
        Carrier c1 = new Carrier("Carrier1", Set.of("P", "C"));
        c1.start();
        Carrier c2 = new Carrier("Carrier2", Set.of("P", "S"));
        c2.start();
    }
}

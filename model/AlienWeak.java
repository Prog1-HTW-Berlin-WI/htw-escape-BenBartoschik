package model;
import java.io.Serializable;

/**
 * Repräsentiert einen schwachen Alien im Spiel.
 * Ein schwaches Alien soll die leiteste "herausforderung" für den Spieler darstellen.
 */

public class AlienWeak extends Alien implements Serializable {

    private static final long serialVersionUID = 1729389822767173584L;

    public AlienWeak() {
        super("Manfred", 25, false, "Zzz... I'm Manfred, the Alien! U can run, but u can't hide!");
    }

    @Override
    public int doDamage() {
        return 5;
    }
}

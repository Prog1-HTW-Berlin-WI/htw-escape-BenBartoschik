package model;

import java.io.Serializable;

/**
 * Repräsentiert einen schwachen Alien im Spiel.
 * Ein schwaches Alien soll die leiteste "herausforderung" für den Spieler
 * darstellen.
 */

public class AlienWeak extends Alien implements Serializable {

    private static final long serialVersionUID = 1729389822767173584L;

    /**
     * Erstellt ein schwaches Alien.
     */

    public AlienWeak() {
        super("Manfred", 5, false, "Zzz... I'm Manfred, the Alien! U can run, but u can't hide!");
    }

    /**
     * Verursacht geringen Schaden.
     *
     * @return Schaden
     */

    @Override
    public int doDamage() {
        return 1;
    }
}

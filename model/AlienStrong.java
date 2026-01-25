package model;

import java.io.Serializable;

/**
 * Repräsentiert einen starken Alien im Spiel.
 * Ein starker Alien soll die größte "herausforderung" für den Spieler
 * darstellen.
 */

public class AlienStrong extends Alien implements Serializable {

    private static final long serialVersionUID = 1729389822767173584L;

    /**
     * Erstellt ein starkes Alien.
     */
    public AlienStrong() {
        super("Gerald", 10, false, "Grrrrr... I'm Gerald, the Alien! And I will eat u alive!");
    }

    /**
     * Verursacht Schaden beim Angriff.
     *
     * @return Schaden
     */

    @Override
    public int doDamage() {
        return 2;
    }
}

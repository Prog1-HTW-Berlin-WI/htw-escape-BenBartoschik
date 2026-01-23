package model;

import java.io.Serializable;

/**
 * Repräsentiert einen starken Alien im Spiel.
 * Ein starker Alien soll die größte "herausforderung" für den Spieler darstellen.
 */

public class AlienStrong extends Alien implements Serializable {

    private static final long serialVersionUID = 1729389822767173584L;

    public AlienStrong() {
        super("Gerald", 50, false, "Grrrrr... I'm Gerald, the Alien! And I will eat u alive!");
    }

    @Override
    public int doDamage() {
        return 30;
    }
}


package model;

/**
 * Repräsentiert einen starken Alien im Spiel.
 * Ein starker Alien soll die größte "herausforderung" für den Spieler darstellen.
 */

public class AlienStrong extends Alien {

    public AlienStrong() {
        super("Gerald", 50, false, "Grrrrr... I'm Gerald, the Alien! And I will eat u alive!");
    }

    @Override
    public int doDamage() {
        return 30;
    }
}

